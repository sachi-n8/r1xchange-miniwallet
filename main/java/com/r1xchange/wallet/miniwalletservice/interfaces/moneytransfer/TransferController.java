package com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer;

import com.r1xchange.wallet.miniwalletservice.common.constants.Endpoints;
import com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.model.TransferRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.moneytransfer.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.WALLET)
public class TransferController {
    @Autowired
    private TransferService transferService;

    @PostMapping(Endpoints.WALLET_TRANSFER)
    public String transfer(@RequestBody TransferRequest request) {
        transferService.transfer(request);
        return "Transfer successful";
    }
}