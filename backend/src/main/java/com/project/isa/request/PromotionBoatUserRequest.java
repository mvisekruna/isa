package com.project.isa.request;

import lombok.Data;

@Data
public class PromotionBoatUserRequest {

    private Long boatId;

    private Long promotionUserId;

    private boolean isSubscribed;
}
