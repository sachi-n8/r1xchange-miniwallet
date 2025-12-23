package com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.service.impl;

import com.r1xchange.wallet.miniwalletservice.common.enums.ResponseStatusAndMessage;
import com.r1xchange.wallet.miniwalletservice.common.exception.WalletException;
import com.r1xchange.wallet.miniwalletservice.common.model.Wallet;
import com.r1xchange.wallet.miniwalletservice.common.repo.WalletRepository;
import com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.model.TransferRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.service.TransferService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@NoArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    @Transactional
    public void transfer(TransferRequest transferRequest) {

        String fromUser = transferRequest.getFromUserId();
        String toUser = transferRequest.getToUserId();
        BigDecimal amount = transferRequest.getAmount();

        log.info("Initiating transfer | From: {} To: {} Amount: {}",
                fromUser, toUser, amount);

        Wallet sender = walletRepository.findByUserId(fromUser);
        Wallet receiver = walletRepository.findByUserId(toUser);

        if (sender == null) {
            log.warn("Sender wallet not found for userId: {}", fromUser);
            throw new WalletException(ResponseStatusAndMessage.WALLET_NOT_FOUND);
        }

        if (receiver == null) {
            log.warn("Receiver wallet not found for userId: {}", toUser);
            throw new WalletException(ResponseStatusAndMessage.WALLET_NOT_FOUND);
        }

        log.info("Sender balance: {} | Receiver balance: {}",
                sender.getBalance(), receiver.getBalance());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warn("Invalid transfer amount: {} | UserId: {}", amount, fromUser);
            throw new WalletException(ResponseStatusAndMessage.INVALID_AMOUNT);
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            log.warn("Insufficient balance for userId: {} | Required: {} Available: {}",
                    fromUser, amount, sender.getBalance());
            throw new WalletException(ResponseStatusAndMessage.INSUFFICIENT_BALANCE);
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        walletRepository.save(sender);
        walletRepository.save(receiver);

        log.info("Transfer successful | From: {} To: {} Amount: {}",
                fromUser, toUser, amount);

        log.info("Updated Balances | Sender: {} Receiver: {}",
                sender.getBalance(), receiver.getBalance());
    }
}
