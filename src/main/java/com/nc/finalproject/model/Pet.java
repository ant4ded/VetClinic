package com.nc.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Pet for  {@link User}
 *
 * @author WildDed
 * @version 1.0
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "pets")
public class Pet {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User owner;

    @NonNull
    @Column(name = "pet_name", length = 45)
    private String name;

    @NonNull
    @Column(name = "pet_type", length = 45)
    private String petType;

    @OneToMany(mappedBy = "pet")
    private List<VetServiceOfUser> petServices = new ArrayList<>();
}
