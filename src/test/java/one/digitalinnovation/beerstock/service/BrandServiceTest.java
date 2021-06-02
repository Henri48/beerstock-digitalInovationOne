package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.*;
import one.digitalinnovation.beerstock.dto.*;
import one.digitalinnovation.beerstock.entity.*;
import one.digitalinnovation.beerstock.exception.Beer.*;
import one.digitalinnovation.beerstock.exception.Brand.*;
import one.digitalinnovation.beerstock.mapper.*;
import one.digitalinnovation.beerstock.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BrandRepository brandRepository;

    private BrandMapper brandMapper = BrandMapper.INSTANCE;

    @InjectMocks
    private BrandService brandService;

    @Test
    void whenBrandInformedThenItShouldBeCreated() throws BrandAlreadyRegisteredException {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedSavedBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByName(expectedBrandDTO.getName())).thenReturn(Optional.empty());
        when(brandRepository.findByCnpj(expectedBrandDTO.getCnpj())).thenReturn(Optional.empty());
        when(brandRepository.save(expectedSavedBrand)).thenReturn(expectedSavedBrand);

        //then
        BrandDTO createdBrandDTO = brandService.createBrand(expectedBrandDTO);

        assertThat(createdBrandDTO.getId(), is(equalTo(expectedBrandDTO.getId())));
        assertThat(createdBrandDTO.getName(), is(equalTo(expectedBrandDTO.getName())));
    }

    @Test
    void whenAlreadyRegisteredBrandNameInformedThenAnExceptionShouldBeThrown() {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand duplicatedBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByName(expectedBrandDTO.getName())).thenReturn(Optional.of(duplicatedBrand));

        // then
        assertThrows(BrandAlreadyRegisteredException.class, () -> brandService.createBrand(expectedBrandDTO));
    }

    @Test
    void whenAlreadyRegisteredBrandCnpjInformedThenAnExceptionShouldBeThrown() {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand duplicatedBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByCnpj(expectedBrandDTO.getCnpj())).thenReturn(Optional.of(duplicatedBrand));

        // then
        assertThrows(BrandAlreadyRegisteredException.class, () -> brandService.createBrand(expectedBrandDTO));
    }

    @Test
    void whenValidBrandNameIsGivenThenReturnABrand() throws BrandNotFoundException {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedFoundBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByName(expectedFoundBrand.getName())).thenReturn(Optional.of(expectedFoundBrand));

        // then
        BrandDTO foundBrandDTO = brandService.findByName(expectedBrandDTO.getName());

        assertThat(foundBrandDTO, is(equalTo(expectedBrandDTO)));
    }

    @Test
    void whenValidBrandCnpjIsGivenThenReturnABrand() throws BrandNotFoundException {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedFoundBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByCnpj(expectedFoundBrand.getCnpj())).thenReturn(Optional.of(expectedFoundBrand));

        // then
        BrandDTO foundBrandDTO = brandService.findByCnpj(expectedBrandDTO.getCnpj());

        assertThat(foundBrandDTO, is(equalTo(expectedBrandDTO)));
    }

    @Test
    void whenValidBrandNameIsGivenThenThrowAnException() {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedFoundBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByName(expectedFoundBrand.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(BrandNotFoundException.class, () -> brandService.findByName(expectedFoundBrand.getName()));
    }

    @Test
    void whenValidBrandCnpjIsGivenThenThrowAnException() {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedFoundBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findByCnpj(expectedFoundBrand.getCnpj())).thenReturn(Optional.empty());

        // then
        assertThrows(BrandNotFoundException.class, () -> brandService.findByCnpj(expectedFoundBrand.getCnpj()));
    }

    @Test
    void whenListBeerIsCalledThenReturnAListOfBrands() {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedFoundBrand = brandMapper.toModel(expectedBrandDTO);

        //when
        when(brandRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBrand));

        //then
        List<BrandDTO> foundListBeersDTO = brandService.listAll();

        assertThat(foundListBeersDTO, is(not(empty())));
        assertThat(foundListBeersDTO.get(0), is(equalTo(brandMapper.toDTO(expectedFoundBrand))));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenABeerShouldBeDeleted() throws BrandNotFoundException {
        // given
        BrandDTO expectedBrandDTO = BrandDTOBuilder.builder().build().toBrandDTO();
        Brand expectedDeletedBrand = brandMapper.toModel(expectedBrandDTO);

        // when
        when(brandRepository.findById(expectedDeletedBrand.getId())).thenReturn(Optional.of(expectedDeletedBrand));
        doNothing().when(brandRepository).deleteById(expectedDeletedBrand.getId());

        // then
        brandService.deleteById(expectedBrandDTO.getId());

        verify(brandRepository, times(1)).findById(expectedDeletedBrand.getId());
        verify(brandRepository, times(1)).deleteById(expectedDeletedBrand.getId());
    }
}