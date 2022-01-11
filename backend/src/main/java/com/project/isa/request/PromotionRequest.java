package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionRequest {

    private String startPromo;

    private String endPromo;

    private String description;

    private Long adventurePromotionId;

    private Long boatPromotionId;

    private Long vacationHomePromotionId;
}
