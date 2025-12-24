package com.r1xchange.wallet.miniwalletservice.common.repo;

import com.r1xchange.wallet.miniwalletservice.common.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WalletRepository extends MongoRepository<Wallet,String> {

    Wallet findByUserId(String userId);
}
