package com.project.isa.request;

import lombok.Data;

@Data
public class VacationHomeRequest {

    private Long vacationHomeOwner;

    private String vacationHomeName;

    private String vacationHomeLocation;
}
