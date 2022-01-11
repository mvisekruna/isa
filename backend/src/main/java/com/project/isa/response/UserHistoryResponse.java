package com.project.isa.response;

import com.project.isa.dto.AdventureReservationDto;
import com.project.isa.dto.BoatReservationDto;
import com.project.isa.dto.VacationHomeReservationDto;
import lombok.Data;

import java.util.List;

@Data
public class UserHistoryResponse {

    private List<BoatReservationDto> boatReservations;

    private List<VacationHomeReservationDto> vacationHomeReservations;

    private List<AdventureReservationDto> adventureReservations;
}
