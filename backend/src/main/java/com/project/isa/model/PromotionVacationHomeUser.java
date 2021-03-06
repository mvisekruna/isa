package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PROMOTION_VACATIONHOMEUSER")
public class PromotionVacationHomeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private VacationHome vacationHome;

    @ManyToOne
    private User promotionUser;

    @ManyToOne
    private PromotionVacationHome promotionVacationHome;

    private boolean isSubscribed;
}
