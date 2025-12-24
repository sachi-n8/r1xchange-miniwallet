package com.r1xchange.wallet.miniwalletservice.interfaces.login.model;

import lombok.Data;

@Data
public class UserRequest {

    private String FirstName;

    private String LastName;

    private String Email;

    private String Password;

}
