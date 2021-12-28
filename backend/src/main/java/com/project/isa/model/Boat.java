package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "BOAT")
public class Boat {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User boatOwner;

    @Column(name = "boat_name")
    private String boatName;

    @Column(name = "boat_location")
    private String boatLocation;

}
