package com.project.isa.model;

import com.project.isa.enumeration.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "VACATION_HOME_RESERVATION")
public class VacationHomeReservation {

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
    private VacationHome vacationHome;

    @Column
    private int numberOfPeople;

    @Column
    private double price;

    @ManyToOne
    private User user;

    @Column
    private ReservationStatus status;
}
