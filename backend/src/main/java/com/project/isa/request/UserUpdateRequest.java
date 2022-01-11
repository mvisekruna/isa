package com.project.isa.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;

}
