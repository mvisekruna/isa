package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROMOTION_BOATUSER")
public class PromotionBoatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Boat boat;

    @ManyToOne
    private User promotionUser;

    private boolean isSubscribed;
}
