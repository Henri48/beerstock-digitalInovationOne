package one.digitalinnovation.beerstock.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @OneToMany(mappedBy="brand", fetch = FetchType.LAZY)
    private Set<Beer> beers;
}
