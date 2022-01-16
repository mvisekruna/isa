package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionAdventureRequest {

    private String startPromo;

    private String endPromo;

    private String description;

    private Long adventurePromotionId;
}
