package com.iggy.realtimechat.service;

import com.iggy.realtimechat.dto.MessageDTO;
import com.iggy.realtimechat.dto.SendMessageRequest;
import com.iggy.realtimechat.entity.ChatRoom;
import com.iggy.realtimechat.entity.Message;
import com.iggy.realtimechat.entity.User;
import com.iggy.realtimechat.repository.ChatRoomRepository;
import com.iggy.realtimechat.repository.MessageRepository;
import com.iggy.realtimechat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;


    public ChatRoom createRoom(String name){
        ChatRoom chatRoom = ChatRoom.builder()
                .name(name)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getRoomByName(String name){
      return chatRoomRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Chat room not Found"));
    }

    public MessageDTO saveMessage(SendMessageRequest request, String senderEmail){
        ChatRoom chatRoom = chatRoomRepository.findByName(request.getChatRoomName())
                .orElseThrow(() -> new RuntimeException("Chat room not Found"));
        User user = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("User not Found"));
        Message message = Message.builder()
                .content(request.getContent())
                .chatRoom(chatRoom)
                .sender(user)
                .build();

        Message savedMessage = messageRepository.save(message);
        return MessageDTO.builder()
                .id(savedMessage.getId())
                .content(savedMessage.getContent())
                .senderUsername(savedMessage.getSender().getUsername())
                .chatRoomName(savedMessage.getChatRoom().getName())
                .sentAt(savedMessage.getSentAt())
                .build();
    }

    public List<MessageDTO> getRoomMessage(String roomName){
        ChatRoom chatRoom = chatRoomRepository.findByName(roomName)
                .orElseThrow(() ->  new RuntimeException("Chat room not Found"));
        List<Message> messages = messageRepository.findByChatRoom(chatRoom);
        return messages.stream()
                .map(message -> MessageDTO.builder()
                        .id(message.getId())
                        .content(message.getContent())
                        .senderUsername(message.getSender().getUsername())
                        .chatRoomName(message.getChatRoom().getName())
                        .sentAt(message.getSentAt())
                        .build())
                .collect(Collectors.toList());
    }



}
