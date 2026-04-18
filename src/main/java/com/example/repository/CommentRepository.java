package com.example.repository;

import com.example.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find comments by product
    List<Comment> findByProduct_Id(Long productId);

    // Find comments by type
    List<Comment> findByType(String type);

    // Find comments by product and type
    List<Comment> findByProduct_IdAndType(Long productId, String type);
}