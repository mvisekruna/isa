package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "PROMOTION")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startPromo;

    private Date endPromo;

    private String description;

    @ManyToOne
    private Adventure adventurePromotion;

    @ManyToOne
    private Boat boatPromotion;

    @ManyToOne
    private VacationHome vacationHomePromotion;

}
