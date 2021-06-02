package one.digitalinnovation.beerstock.exception.Brand;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrandNotFoundException extends Exception{

    public BrandNotFoundException(String param, String brandName) {
        super(String.format("Brand with %s %s not found in the system.", param, brandName));
    }

    public BrandNotFoundException(Long id) {
        super(String.format("Brand with id %s not found in the system.", id));
    }
}
