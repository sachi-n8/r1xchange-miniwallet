package com.r1xchange.wallet.miniwalletservice.interfaces.auth;

import com.r1xchange.wallet.miniwalletservice.common.constants.Endpoints;
import com.r1xchange.wallet.miniwalletservice.common.constants.StatusAndMessage;
import com.r1xchange.wallet.miniwalletservice.common.model.User;
import com.r1xchange.wallet.miniwalletservice.interfaces.auth.service.AuthSerivce;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.WALLET)
public class AuthController {

    @Autowired
    private AuthSerivce authSerivce;

    @PostMapping(Endpoints.AUTH)
    public UserResponse auth() {
        User user = new User();
        UserResponse response = new UserResponse();
        String token = authSerivce.authenticate(user);
        response.setJwtToken(token);
        response.setStatusCode(StatusAndMessage.SUCCESS);
        response.setStatusMessage(StatusAndMessage.JWT_TOKEN);
        return response;
    }
}