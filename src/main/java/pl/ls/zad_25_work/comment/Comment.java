package pl.ls.zad_25_work.comment;

import org.springframework.format.annotation.DateTimeFormat;
import pl.ls.zad_25_work.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String content;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private LocalDateTime  addedTime;
   @ManyToOne
   private Customer customer;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }



    public void setAddedTime(LocalDateTime addedTime) {
        this.addedTime = addedTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
