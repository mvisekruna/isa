package com.project.isa.request;

import lombok.Data;

@Data
public class DeleteAccountRequest {

    private Long deleteAccountId;

    private Long userId;

    private String reason;

    private boolean confirm;
}
