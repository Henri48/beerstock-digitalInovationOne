package one.digitalinnovation.beerstock.dto;

import lombok.*;
import one.digitalinnovation.beerstock.entity.*;
import org.hibernate.validator.constraints.br.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @CNPJ
    private String cnpj;

}
