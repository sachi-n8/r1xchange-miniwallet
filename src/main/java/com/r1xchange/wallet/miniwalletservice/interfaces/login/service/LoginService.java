package com.r1xchange.wallet.miniwalletservice.interfaces.login.service;

import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserResponse;

public interface LoginService {

    public UserResponse login(UserRequest userRequest);

}
