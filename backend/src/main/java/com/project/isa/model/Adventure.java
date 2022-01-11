package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADVENTURE")
public class Adventure {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adventure_name")
    private String adventureName;

    @Column(name="adventure_address")
    private String adventureAddress;

    @Column(name="adventure_description")
    private String adventureDescription;

    @Column(name="adventure_price")
    private String adventurePrice;

    @Column(name="adventure_review")
    private String adventureReview;

    @ManyToOne(fetch = FetchType.EAGER)
    private User adventureTutor;



}
