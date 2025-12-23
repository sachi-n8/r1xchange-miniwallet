package com.r1xchange.wallet.miniwalletservice.common.repo;

import com.r1xchange.wallet.miniwalletservice.common.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email) ;

    Boolean existsUserByUserId(String userId);
}
