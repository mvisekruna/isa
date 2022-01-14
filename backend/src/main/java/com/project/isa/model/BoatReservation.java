package com.project.isa.model;

import com.project.isa.enumeration.ReservationStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "BOAT_RESERVATION")
public class BoatReservation {

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
    private Boat boat;

    @Column
    private double price;

    @ManyToOne
    private User user;

    @Column
    private ReservationStatus status;


}
