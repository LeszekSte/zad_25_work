package pl.ls.zad_25_work.comment;


import org.springframework.stereotype.Service;
import pl.ls.zad_25_work.Customer;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    void save(Comment comment){
        commentRepository.save(comment);
    }

    public void deleteById  (Long id){
        commentRepository.deleteById(id);
    }

    public Long findCustomerIdForCommentId (Long commendId){
        Optional<Comment> optionalComment = commentRepository.findById(commendId);
        Comment comment = optionalComment.orElse(null);
        Customer customer = comment.getCustomer();
        return customer.getId();
    }



}
