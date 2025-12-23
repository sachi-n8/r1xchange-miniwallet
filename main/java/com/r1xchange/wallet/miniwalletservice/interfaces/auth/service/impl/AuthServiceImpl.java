package com.r1xchange.wallet.miniwalletservice.interfaces.auth.service.impl;

import com.r1xchange.wallet.miniwalletservice.common.model.User;
import com.r1xchange.wallet.miniwalletservice.common.repo.UserRepository;
import com.r1xchange.wallet.miniwalletservice.common.util.JwtUtil;
import com.r1xchange.wallet.miniwalletservice.interfaces.auth.service.AuthSerivce;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AuthServiceImpl implements AuthSerivce {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String authenticate(User user) {

        User userDetail = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtUtil.generateToken(userDetail);
    }
}
