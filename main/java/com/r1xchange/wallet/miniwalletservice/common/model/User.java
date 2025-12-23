package com.r1xchange.wallet.miniwalletservice.common.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
