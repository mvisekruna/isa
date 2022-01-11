package com.project.isa.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ADVENTURE_RESERVATION")
public class AdventureReservation {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @OneToOne
    private Adventure adventure;

    @Column
    private int numberOfPeople;

    @Column
    private double price;

    @ManyToOne
    private User user;
}
