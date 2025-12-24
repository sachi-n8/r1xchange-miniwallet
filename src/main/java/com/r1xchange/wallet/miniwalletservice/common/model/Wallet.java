package com.r1xchange.wallet.miniwalletservice.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document(collection = "wallet")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    private String userId;

    private BigDecimal balance;

}
