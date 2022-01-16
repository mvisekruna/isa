package com.project.isa.response;

import com.project.isa.model.AdventureReservation;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

@Data
public class AdventureReservationResponse {

    private Long id;

    private String startDate;

    private String endDate;

    private String adventureName;

    private String adventureAddress;

    private int numberOfPeople;

    private double price;

    private int duration;

    private String status;

    private boolean canCancel;


    public AdventureReservationResponse(AdventureReservation adventureReservation) {
        this.id = adventureReservation.getId();
        this.startDate = adventureReservation.getStartDate().toString();
        this.endDate = adventureReservation.getEndDate().toString();
        this.adventureName = adventureReservation.getAdventure().getAdventureName();
        this.adventureAddress = adventureReservation.getAdventure().getAdventureAddress();
        this.numberOfPeople =adventureReservation.getNumberOfPeople();
        this.price = adventureReservation.getPrice();
        long difference_In_Days
                = TimeUnit
                .MILLISECONDS
                .toDays(adventureReservation.getEndDate().getTime() - adventureReservation.getStartDate().getTime())
                % 365;
        this.duration = (int) difference_In_Days;
        this.status = adventureReservation.getStatus().name();

        Instant dbInstant = adventureReservation.getStartDate().toInstant();

        Instant plus3Days = ZonedDateTime.now().plusDays(3).toInstant();


        if(plus3Days.isBefore(dbInstant) ) {
            this.canCancel = true;
        }
        else {
            this.canCancel = false;
        }
    }

}
