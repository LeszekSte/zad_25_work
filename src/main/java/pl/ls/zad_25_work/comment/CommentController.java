package pl.ls.zad_25_work.comment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import pl.ls.zad_25_work.Customer;
import pl.ls.zad_25_work.CustomerRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class CommentController {

    private CommentService commentService;
    private CustomerRepository customerRepository;

    public CommentController(CommentService commentService, CustomerRepository customerRepository) {
        this.commentService = commentService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/addcomment")
    public String addComment(@RequestPart Long customerId, @RequestParam String content) {
        Comment comment = new Comment();
        comment.setAddedTime(LocalDateTime.now());
        comment.setContent(content);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.orElse(null);
        comment.setCustomer(customer);
        commentService.save(comment);

        return "redirect:/customer/" + customerId;
    }

}
