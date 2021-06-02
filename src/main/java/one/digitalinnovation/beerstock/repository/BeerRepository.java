package one.digitalinnovation.beerstock.repository;

import one.digitalinnovation.beerstock.entity.Beer;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    @Query("SELECT beer " +
            "FROM Beer beer " +
            "JOIN beer.brand brand " +
            "WHERE " +
            " :idBrand IS NULL OR brand.id = :idBrand")
    List<Beer> findAll(@Param("idBrand") Long idBrand);

    Optional<Beer> findByName(String name);
}
