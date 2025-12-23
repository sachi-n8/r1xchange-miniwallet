package com.r1xchange.wallet.miniwalletservice.common.exception;

import com.r1xchange.wallet.miniwalletservice.common.enums.ResponseStatusAndMessage;

public class WalletException extends RuntimeException {

    private final String statusCode;   // your custom status code

    public WalletException(ResponseStatusAndMessage response) {
        super(response.getMessage());
        this.statusCode = response.getStatusCode();
    }

    public String getStatusCode() {
        return statusCode;
    }
}
