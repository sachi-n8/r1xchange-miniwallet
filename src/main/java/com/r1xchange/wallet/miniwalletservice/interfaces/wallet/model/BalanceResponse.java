package com.r1xchange.wallet.miniwalletservice.interfaces.wallet.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceResponse {

    private String userId;

    private BigDecimal balance;
}
