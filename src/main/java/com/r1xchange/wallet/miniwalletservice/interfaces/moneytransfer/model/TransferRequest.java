package com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.model;

import lombok.Data;
import lombok.NonNull;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NonNull
    private String fromUserId;
    @NonNull
    private String toUserId;
    @NonNull
    private BigDecimal amount;
}
