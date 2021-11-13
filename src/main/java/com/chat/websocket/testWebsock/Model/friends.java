package com.chat.websocket.testWebsock.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "friends")
public class friends {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_chat_room")
    private Integer idChatRoom;
    @Column(name = "my_id")
    private Integer myid;
    @Column(name = "friend_id")
    private Integer friendId;
    @Column(name = "friend_first_name")
    private String friendName;
    @Column(name = "friend_last_name")
    private String friendLastName;
}
