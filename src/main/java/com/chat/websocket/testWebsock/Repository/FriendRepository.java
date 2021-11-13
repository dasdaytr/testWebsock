package com.chat.websocket.testWebsock.Repository;

import com.chat.websocket.testWebsock.Model.friends;
import com.chat.websocket.testWebsock.Test.Friends;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.repository.CrudRepository;




public interface FriendRepository extends CrudRepository<friends,Integer> {
   friends findByMyidAndFriendId(int myId,int friendId);
}
