package com.r1xchange.wallet.miniwalletservice.interfaces.auth.service;
import com.r1xchange.wallet.miniwalletservice.common.model.User;

public interface AuthSerivce {
    public String authenticate(User user);
}
