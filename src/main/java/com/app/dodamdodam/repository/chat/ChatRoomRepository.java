package com.app.dodamdodam.repository.chat;

import com.app.dodamdodam.entity.chat.ChatRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends CrudRepository<ChatRoom, Long> {

    public List<ChatRoom> findAllByUserSetContaining(Long id);

    public Optional<ChatRoom> findByRoomId(String roomId);
}