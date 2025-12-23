package com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.service;

import com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.model.TransferRequest;

public interface TransferService {

    public void transfer(TransferRequest transferRequest) ;
}
