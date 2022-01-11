package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "VACATION_HOME")
public class VacationHome {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User vacationHomeOwner;

    @Column(name = "vacation_home_name")
    private String vacationHomeName;

    @Column(name = "vacation_home_location")
    private String vacationHomeLocation;

    @Column(name = "vacation_home_price")
    private String vacationHomePrice;

    @Column(name = "vacation_home_review")
    private String vacationHomeReview;

}
