package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PROMOTION_VACATION_HOME")
public class PromotionVacationHome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startPromo;

    private Date endPromo;

    private String description;

    @ManyToOne
    private VacationHome vacationHomePromotion;

    @Column
    private int numberOfPromotions;
}
