package com.r1xchange.wallet.miniwalletservice.interfaces.login.service.impl;

import com.r1xchange.wallet.miniwalletservice.common.enums.ResponseStatusAndMessage;
import com.r1xchange.wallet.miniwalletservice.common.constants.StatusAndMessage;
import com.r1xchange.wallet.miniwalletservice.common.exception.WalletException;
import com.r1xchange.wallet.miniwalletservice.common.model.User;
import com.r1xchange.wallet.miniwalletservice.common.model.Wallet;
import com.r1xchange.wallet.miniwalletservice.common.repo.UserRepository;
import com.r1xchange.wallet.miniwalletservice.common.repo.WalletRepository;
import com.r1xchange.wallet.miniwalletservice.interfaces.auth.service.AuthSerivce;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserRequest;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.model.UserResponse;
import com.r1xchange.wallet.miniwalletservice.interfaces.login.service.LoginService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthSerivce authSerivce;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public UserResponse login(UserRequest userRequest) {

        log.info("Login attempt for email: {}", userRequest.getEmail());

        Optional<User> userDetail = userRepository.findByEmail(userRequest.getEmail());
        User user;

        if (userDetail.isEmpty()) {
            log.info("User not found. Creating new user with email: {}", userRequest.getEmail());

            user = new User();
            user.setUserId(generateUniqueUserId());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userRepository.save(user);

            log.info("New user created with userId: {}", user.getUserId());

            Wallet wallet = new Wallet();
            wallet.setUserId(user.getUserId());
            wallet.setBalance(BigDecimal.valueOf(100));
            walletRepository.save(wallet);

            log.info("Wallet initialized for userId: {} with balance: 100", user.getUserId());

        } else {

            user = userDetail.get();
            log.info("Existing user found with userId: {}", user.getUserId());

            if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
                log.warn("Password mismatch for userId: {}", user.getUserId());
                throw new WalletException(ResponseStatusAndMessage.PASSWORD_DOES_NOT_MATCH);
            }

            log.info("Password validation successful for userId: {}", user.getUserId());
        }

        log.info("Generating JWT token for userId: {}", user.getUserId());
        String token = authSerivce.authenticate(user);

        log.info("Login successful for userId: {}", user.getUserId());

        UserResponse userResponse = new UserResponse();
        userResponse.setStatusCode(StatusAndMessage.SUCCESS);
        userResponse.setStatusMessage(StatusAndMessage.USER_LOGGED_IN);
        userResponse.setUserID(user.getUserId());
        userResponse.setJwtToken(token);

        return userResponse;
    }

    private String generateUniqueUserId() {
        String userId;
        do {
            userId = UUID.randomUUID().toString();
        } while (userRepository.existsUserByUserId(userId));

        log.debug("Generated new unique userId: {}", userId);
        return userId;
    }
}
