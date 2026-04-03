package com.iggy.realtimechat.controller;

import com.iggy.realtimechat.dto.MessageDTO;
import com.iggy.realtimechat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController{
    private final ChatService chatService;
    @GetMapping("/{roomName}")
    public List<MessageDTO> getMessages(@PathVariable String roomName) {
         return chatService.getRoomMessage(roomName);
    }
}
