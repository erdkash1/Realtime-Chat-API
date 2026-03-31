package com.iggy.realtimechat.controller;

import com.iggy.realtimechat.dto.MessageDTO;
import com.iggy.realtimechat.dto.SendMessageRequest;
import com.iggy.realtimechat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload SendMessageRequest request, Principal principal) {
        MessageDTO savedMessage = chatService.saveMessage(request, principal.getName());
        messagingTemplate.convertAndSend(
                "/topic/" + request.getChatRoomName(), savedMessage
        );
    }

    @MessageMapping("/chat.createRoom")
    public void createRoom(@Payload String roomName) {
         chatService.createRoom(roomName);
    }
}
