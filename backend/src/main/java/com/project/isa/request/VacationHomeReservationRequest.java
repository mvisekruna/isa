package com.project.isa.request;

import lombok.Data;

@Data
public class VacationHomeReservationRequest {

    private String startDate;

    private String endDate;

    private Long vacationHomeId;


}
