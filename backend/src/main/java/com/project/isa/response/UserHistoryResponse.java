package com.project.isa.response;

import lombok.Data;

import java.util.List;

@Data
public class UserHistoryResponse {

    private List<BoatReservationResponse> boatReservations;

    private List<VacationHomeReservationResponse> vacationHomeReservations;

    private List<AdventureReservationResponse> adventureReservations;
}
