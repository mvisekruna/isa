package com.project.isa.dto;

import com.project.isa.model.BoatReservation;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
public class BoatReservationDto {

    private  Long id;

    private Date startDate;

    private Date endDate;

    private String boatName;

    private String boatLocation;

    private double price;

    private int duration;

    public BoatReservationDto(BoatReservation boatReservation) {
        this.id =boatReservation.getId();
        this.startDate = boatReservation.getStartDate();
        this.endDate = boatReservation.getEndDate();
        this.price = boatReservation.getPrice();
        this.boatName = boatReservation.getBoat().getBoatName();
        this.boatLocation = boatReservation.getBoat().getBoatLocation();
        long difference_In_Days
                = TimeUnit
                .MILLISECONDS
                .toDays(boatReservation.getEndDate().getTime() - boatReservation.getStartDate().getTime())
                % 365;
        this.duration = (int) difference_In_Days ;
    }

}
