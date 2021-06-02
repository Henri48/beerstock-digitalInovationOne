package one.digitalinnovation.beerstock.mapper;

import one.digitalinnovation.beerstock.dto.*;
import one.digitalinnovation.beerstock.entity.*;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toModel(BrandDTO beerDTO);

    BrandDTO toDTO(Brand brand);
}
