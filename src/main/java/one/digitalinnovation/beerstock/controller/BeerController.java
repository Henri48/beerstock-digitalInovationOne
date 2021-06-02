package one.digitalinnovation.beerstock.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.controller.docs.beer.*;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.exception.Beer.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.Beer.BeerNotFoundException;
import one.digitalinnovation.beerstock.exception.Beer.BeerStockExceededException;
import one.digitalinnovation.beerstock.exception.Brand.*;
import one.digitalinnovation.beerstock.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController implements BeerControllerDocs {

    private final BeerService beerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO, @RequestParam Long idBrand) throws BeerAlreadyRegisteredException, BrandNotFoundException {
        return beerService.createBeer(beerDTO, idBrand);
    }

    @GetMapping("/{name}")
    public BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException {
        return beerService.findByName(name);
    }

    @GetMapping
    public List<BeerDTO> listBeers(@RequestParam(required = false) Long idBrand) {
        return beerService.listAll(idBrand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws BeerNotFoundException {
        beerService.deleteById(id);
    }

    @PatchMapping("/{id}/increment")
    public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException {
        return beerService.increment(id, quantityDTO.getQuantity());
    }
}
