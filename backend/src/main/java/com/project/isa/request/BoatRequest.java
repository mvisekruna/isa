package com.project.isa.request;

import lombok.Data;

@Data
public class BoatRequest {

    private Long boatOwner;

    private String boatName;

    private String boatLocation;

    private String boatDescription;

    private String boatPrice;

    private String boatReview;
}
