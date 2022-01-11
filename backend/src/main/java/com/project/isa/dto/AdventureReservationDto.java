package com.project.isa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdventureReservationDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private String adventureName;

    private String adventureAddress;

    private int numberOfPeople;

    private double price;

    private int duration;

}
