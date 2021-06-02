package one.digitalinnovation.beerstock.service;

import lombok.*;
import one.digitalinnovation.beerstock.dto.*;
import one.digitalinnovation.beerstock.entity.*;
import one.digitalinnovation.beerstock.exception.Beer.*;
import one.digitalinnovation.beerstock.exception.Brand.*;
import one.digitalinnovation.beerstock.mapper.*;
import one.digitalinnovation.beerstock.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper = BrandMapper.INSTANCE;

    public BrandDTO createBrand(BrandDTO brandDto) throws BrandAlreadyRegisteredException{
        verifyIfNameIsAlreadyRegistered(brandDto.getName());
        verifyIfCnpjIsAlreadyRegistered(brandDto.getCnpj());
        Brand brand = brandMapper.toModel(brandDto);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.toDTO(savedBrand);
    }

    private void verifyIfCnpjIsAlreadyRegistered(String cnpj) throws BrandAlreadyRegisteredException {
        if (brandRepository.findByCnpj(cnpj).isPresent()) {
            throw new BrandAlreadyRegisteredException("cnpj", cnpj);
        }
    }

    private void verifyIfNameIsAlreadyRegistered(String name) throws BrandAlreadyRegisteredException {
        if (brandRepository.findByName(name).isPresent()){
            throw new BrandAlreadyRegisteredException("name", name);
        }
    }

    public BrandDTO findByName(String name) throws BrandNotFoundException {
        Brand brand = brandRepository.findByName(name)
                .orElseThrow(() -> new BrandNotFoundException("name", name));
        return brandMapper.toDTO(brand);
    }

    public BrandDTO findByCnpj(String cnpj) throws BrandNotFoundException {
        Brand brand = brandRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new BrandNotFoundException("cnpj", cnpj));
        return brandMapper.toDTO(brand);
    }

    public List<BrandDTO> listAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(brandMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws BrandNotFoundException {
        verifyIfExists(id);
        brandRepository.deleteById(id);
    }

    private Brand verifyIfExists(Long id) throws BrandNotFoundException {
        return brandRepository.findById(id).orElseThrow(() -> new BrandNotFoundException(id));
    }

    public Brand findById(Long idBrand) throws BrandNotFoundException {
        return brandRepository.findById(idBrand).orElseThrow(() -> new BrandNotFoundException(idBrand));
    }
}
