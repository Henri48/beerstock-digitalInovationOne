package one.digitalinnovation.beerstock.controller.docs.brand;

import io.swagger.annotations.*;
import one.digitalinnovation.beerstock.dto.*;
import one.digitalinnovation.beerstock.exception.Brand.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@Api("Manages brand stock")
public interface BrandControllerDocs {

    @ApiOperation(value = "Brand creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success brand creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    BrandDTO createBrand(@RequestBody @Valid BrandDTO brandDto) throws BrandAlreadyRegisteredException;
}
