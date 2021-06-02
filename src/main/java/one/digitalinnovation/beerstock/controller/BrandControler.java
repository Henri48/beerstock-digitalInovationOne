package one.digitalinnovation.beerstock.controller;

import lombok.*;
import one.digitalinnovation.beerstock.dto.*;
import one.digitalinnovation.beerstock.exception.Brand.*;
import one.digitalinnovation.beerstock.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("/api/v1/brand")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BrandControler {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandDTO createBrand(@RequestBody @Valid BrandDTO brandDto) throws BrandAlreadyRegisteredException {
        return brandService.createBrand(brandDto);
    }
}
