package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PROMOTION_BOAT")
public class PromotionBoat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startPromo;

    private Date endPromo;

    private String description;

    @ManyToOne
    private Boat boatPromotion;
}
