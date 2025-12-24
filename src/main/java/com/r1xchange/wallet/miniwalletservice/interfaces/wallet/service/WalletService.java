package com.r1xchange.wallet.miniwalletservice.interfaces.wallet.service;

import com.r1xchange.wallet.miniwalletservice.interfaces.wallet.model.BalanceResponse;

public interface WalletService {

    public BalanceResponse getBalance(String userID);
}
