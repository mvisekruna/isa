package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROMOTION_ADVENTUREUSER")
public class PromotionAdventureUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Adventure adventure;

    @ManyToOne
    private User promotionUser;

    private boolean isSubscribed;

}
