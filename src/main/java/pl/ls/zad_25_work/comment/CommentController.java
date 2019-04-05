package pl.ls.zad_25_work.comment;

import org.springframework.web.bind.annotation.PostMapping;

public class CommentController {

    private CommentService commentService;
    private CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/addcomment")
    public String addComment (){

        return "";
    }

}
