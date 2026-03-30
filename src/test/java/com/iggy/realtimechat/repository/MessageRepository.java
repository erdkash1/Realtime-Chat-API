package com.iggy.realtimechat.repository;

import com.iggy.realtimechat.entity.ChatRoom;
import com.iggy.realtimechat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface MessageRepository extends JpaRepository<Message, Long>{
    List<Message> findByChatRoom(ChatRoom chatRoom);
}
