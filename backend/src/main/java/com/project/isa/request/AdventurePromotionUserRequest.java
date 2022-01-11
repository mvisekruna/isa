package com.project.isa.request;

import lombok.Data;

@Data
public class AdventurePromotionUserRequest {

    private Long adventureId;

    private Long promotionUserId;

    private boolean isSubscribed;
}
