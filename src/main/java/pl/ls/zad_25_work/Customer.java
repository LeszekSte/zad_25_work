package pl.ls.zad_25_work;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ls.zad_25_work.comment.Comment;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    private Long sales;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @OrderBy("addedTime")
    public List<Comment> comments;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Controller
    public static class CustomerController {

        CustomerRepository customerRepository;

        public CustomerController(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        @GetMapping("/")
        public String start(Model model){
            List<Customer> customerList = customerRepository.findAll();
            model.addAttribute("klienci", customerList);
            return "customers";
        }

        @GetMapping("/cust/{id}")
        public String customerInfo(@PathVariable Long id, Model model){
            Optional<Customer> customerOptional = customerRepository.findById(id);

            if (customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                model.addAttribute("klient" , customer);
                return "customer";
            }else{
                return "redirect:/";
            }
        }

        @GetMapping("/addcustomer")
        public String addNewCustomer(Model model){
            model.addAttribute("klient", new Customer());
           CustomerType [] customerTypes = CustomerType.values();
           return "addCustomerForm";
        }

        @PostMapping("/addcustomer")
        public String addCustomer(Customer customer){
            customerRepository.save(customer);
            return "redirect:/";
        }

        @GetMapping("/deletecustomer")
        public String deleteCustomer(Model model){
            List<Customer> customerList = customerRepository.findAll();
            model.addAttribute("klienci", customerList);
        return "deleteCustomers";


        }
        @Transactional
        @GetMapping("/delete/{id}")
        public String deleteCustomer(@PathVariable Long id){
            Optional<Customer> customerOptional = customerRepository.findById(id);

            if (customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                customerRepository.delete(customer);
                return "redirect:/";
            }else{
                return "redirect:/";
            }
        }




    }
}
