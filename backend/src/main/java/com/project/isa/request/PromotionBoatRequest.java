package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionBoatRequest {

    private String startPromo;

    private String endPromo;

    private String description;

    private Long boatPromotionId;
}
