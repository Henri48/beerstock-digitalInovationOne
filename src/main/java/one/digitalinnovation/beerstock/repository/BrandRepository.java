package one.digitalinnovation.beerstock.repository;

import one.digitalinnovation.beerstock.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BrandRepository  extends JpaRepository<Brand, Long> {
    Optional<Brand> findByCnpj(String cnpj);

    Optional<Brand> findByName(String name);
}
