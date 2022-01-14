package com.project.isa.request;

import lombok.Data;

@Data
public class CancelReservationRequest {

    private Long id;
    private String email;
}
