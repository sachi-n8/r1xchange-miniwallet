package com.r1xchange.wallet.miniwalletservice.interfaces.wallet;


import com.r1xchange.wallet.miniwalletservice.common.constants.Endpoints;
import com.r1xchange.wallet.miniwalletservice.interfaces.wallet.model.BalanceResponse;
import com.r1xchange.wallet.miniwalletservice.interfaces.wallet.service.WalletService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.WALLET)
@Slf4j
public class WalletController {
    @Autowired
    private  WalletService walletService;

    @GetMapping(Endpoints.WALLET_BALANCE)
    public BalanceResponse getBalance(@PathVariable String userId) {
        log.info("GET /wallet/balance called for userId: {}", userId);
        return walletService.getBalance(userId);
    }
}