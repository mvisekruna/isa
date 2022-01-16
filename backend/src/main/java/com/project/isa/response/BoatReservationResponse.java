package com.project.isa.response;

import com.project.isa.model.BoatReservation;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

@Data
public class BoatReservationResponse {

    private  Long id;

    private String startDate;

    private String endDate;

    private String boatName;

    private String boatLocation;

    private double price;

    private int duration;

    private String status;

    private boolean canCancel;

    public BoatReservationResponse(BoatReservation boatReservation) {
        this.id = boatReservation.getId();
        this.startDate = boatReservation.getStartDate().toString();
        this.endDate = boatReservation.getEndDate().toString();
        this.price = boatReservation.getPrice();
        this.boatName = boatReservation.getBoat().getBoatName();
        this.boatLocation = boatReservation.getBoat().getBoatLocation();
        long difference_In_Days
                = TimeUnit
                .MILLISECONDS
                .toDays(boatReservation.getEndDate().getTime() - boatReservation.getStartDate().getTime())
                % 365;
        this.duration = (int) difference_In_Days ;
        this.status = boatReservation.getStatus().name();

        Instant dbInstant = boatReservation.getStartDate().toInstant();

        Instant plus3Days = ZonedDateTime.now().plusDays(3).toInstant();


        if(plus3Days.isBefore(dbInstant) ) {
            this.canCancel = true;
        }
        else {
            this.canCancel = false;
        }

    }

}
