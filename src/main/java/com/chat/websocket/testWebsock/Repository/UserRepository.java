package com.chat.websocket.testWebsock.Repository;

import com.chat.websocket.testWebsock.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional <User> findByEmail(String email);
}
