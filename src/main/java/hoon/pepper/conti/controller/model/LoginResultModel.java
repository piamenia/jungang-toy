package hoon.pepper.conti.controller.model;

import lombok.Data;

@Data
public class LoginResultModel {
    private boolean loginResult;
    private String accessToken;
    private String refreshToken;
}
