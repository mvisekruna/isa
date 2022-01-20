package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "PROMOTION_ADVENTURE")
public class PromotionAdventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startPromo;

    private Date endPromo;

    private String description;

    @ManyToOne
    private Adventure adventurePromotion;

    @Column
    private int numberOfPromotions;

}
