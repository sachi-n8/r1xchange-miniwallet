package com.r1xchange.wallet.miniwalletservice.interfaces.wallet.service.impl;

import com.r1xchange.wallet.miniwalletservice.common.enums.ResponseStatusAndMessage;
import com.r1xchange.wallet.miniwalletservice.common.exception.WalletException;
import com.r1xchange.wallet.miniwalletservice.common.model.Wallet;
import com.r1xchange.wallet.miniwalletservice.common.repo.WalletRepository;
import com.r1xchange.wallet.miniwalletservice.interfaces.wallet.model.BalanceResponse;
import com.r1xchange.wallet.miniwalletservice.interfaces.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public BalanceResponse getBalance(String userID) {

        log.info("Fetching balance for userId: {}", userID);

        Wallet wallet = walletRepository.findByUserId(userID);

        if (wallet == null) {
            log.warn("Wallet not found for userId: {}", userID);
            throw new WalletException(ResponseStatusAndMessage.WALLET_NOT_FOUND);
        }

        log.info("Balance retrieved successfully for userId: {} | Balance: {}",
                userID, wallet.getBalance());

        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setUserId(wallet.getUserId());
        balanceResponse.setBalance(wallet.getBalance());

        return balanceResponse;
    }
}

