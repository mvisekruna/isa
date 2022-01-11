package com.project.isa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VacationHomeReservationDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private double price;

    private int duration;

    private String vacationHomeName;

    private String vacationHomeLocation;
}
