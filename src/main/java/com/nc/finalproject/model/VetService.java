package com.nc.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity that vet clinic might have.
 *
 * @author WildDed
 * @version 1.0
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "vet_services")
public class VetService {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(length = 20)
    private String name;

    @NonNull
    @Column
    private Integer cost;

    @OneToMany(mappedBy = "vetService")
    private List<VetServiceOfUser> vetServiceOfUsers = new ArrayList<>();
}
