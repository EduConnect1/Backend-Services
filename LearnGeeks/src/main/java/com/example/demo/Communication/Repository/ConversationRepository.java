package com.example.demo.Communication.Repository;

import com.example.demo.Communication.Model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    List<Conversation> findByParentId(Long parentId);

    List<Conversation> findByTeacherId(Long teacherId);

    Optional<Conversation> findByParentIdAndTeacherId(Long parentId, Long teacherId);
}
