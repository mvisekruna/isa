package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionVacationHomeRequest {

    private String startPromo;

    private String endPromo;

    private String description;

    private Long vacationHomePromotionId;

    private int numberOfPromotions;
}
