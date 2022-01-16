package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionAdventureUserRequest {

    private Long adventureId;

    private Long promotionUserId;

    private boolean isSubscribed;
}
