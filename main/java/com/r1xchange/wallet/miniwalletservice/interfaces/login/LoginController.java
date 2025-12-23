package com.r1xchange.wallet.miniwalletservice.interfaces.login;

import com.r1xchange.wallet.miniwalletservice.common.constants.Endpoints;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserResponse;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.WALLET)
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(Endpoints.LOGIN)
    public UserResponse login(@RequestBody UserRequest userRequest) {
        log.info("Login API hit with email: {}", userRequest.getEmail());
        return loginService.login(userRequest);
    }
}
