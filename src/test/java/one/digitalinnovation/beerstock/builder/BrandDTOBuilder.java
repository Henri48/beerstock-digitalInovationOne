package one.digitalinnovation.beerstock.builder;

import lombok.Builder;
import one.digitalinnovation.beerstock.dto.*;

import javax.persistence.*;

@Builder
public class BrandDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Bhama";

    @Builder.Default
    private String cnpj = "06.275.256/0001-98";

    public BrandDTO toBrandDTO() {
        return new BrandDTO(id,
                name,
                cnpj);
    }
}
