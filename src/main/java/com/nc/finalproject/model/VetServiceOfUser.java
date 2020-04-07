package com.nc.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Entity for {@link VetService} of each user.
 *
 * @author WildDed
 * @version 1.0
 */

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "user_services")
public class VetServiceOfUser {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_service")
    private VetService vetService;

    @NonNull
    @Column
    private String date;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User serviceOwner;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_name")
    private Pet pet;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_doctor")
    private User doctor;
}
