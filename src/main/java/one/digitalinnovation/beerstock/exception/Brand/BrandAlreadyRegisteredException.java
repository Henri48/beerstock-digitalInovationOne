package one.digitalinnovation.beerstock.exception.Brand;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BrandAlreadyRegisteredException extends Exception{

    public BrandAlreadyRegisteredException(String param, String brandName) {
        super(String.format("Brand with %s %s already registered in the system."
                , param == null ? "name" : param, brandName));
    }
}
