package com.example.service;

import com.example.model.Comment;
import com.example.model.CommentDTO;
import com.example.model.Product;
import com.example.model.Users;
import com.example.repository.CommentRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment saveCommentFromDTO(CommentDTO dto) {
        Product product = productRepository.findById(dto.getProductId()).orElse(null);
        Users user = usersRepository.findById(dto.getUserId()).orElse(null);

        Comment comment = new Comment(dto.getContent(), dto.getType(), product, user);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByProductId(Long productId) {
        return commentRepository.findByProduct_Id(productId);
    }

    public List<Comment> getCommentsByType(String type) {
        return commentRepository.findByType(type);
    }

    public List<Comment> getCommentsByProductAndType(Long productId, String type) {
        return commentRepository.findByProduct_IdAndType(productId, type);
    }
}