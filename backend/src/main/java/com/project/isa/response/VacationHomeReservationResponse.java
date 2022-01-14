package com.project.isa.response;

import com.project.isa.model.VacationHomeReservation;
import lombok.Data;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

@Data
public class VacationHomeReservationResponse {

    private Long id;

    private String startDate;

    private String endDate;

    private double price;

    private String vacationHomeName;

    private String vacationHomeLocation;

    private int duration;

    private String status;

    private boolean canCancel;


    public VacationHomeReservationResponse(VacationHomeReservation vacationHomeReservation) {
        this.id = vacationHomeReservation.getId();
        this.startDate = vacationHomeReservation.getStartDate().toString();
        this.endDate = vacationHomeReservation.getEndDate().toString();
        this.price = vacationHomeReservation.getPrice();
        this.vacationHomeName = vacationHomeReservation.getVacationHome().getVacationHomeName();
        this.vacationHomeLocation = vacationHomeReservation.getVacationHome().getVacationHomeLocation();
        long difference_In_Days
                = TimeUnit
                .MILLISECONDS
                .toDays(vacationHomeReservation.getEndDate().getTime() - vacationHomeReservation.getStartDate().getTime())
                % 365;
        this.duration = (int) difference_In_Days ;
        this.status = vacationHomeReservation.getStatus().name();

        Instant dbInstant = vacationHomeReservation.getStartDate().toInstant();

        Instant plus3Days = ZonedDateTime.now().plusDays(3).toInstant();

        if(plus3Days.isBefore(dbInstant) ) {
            this.canCancel = true;
        }
        else {
            this.canCancel = false;
        }

    }
}
