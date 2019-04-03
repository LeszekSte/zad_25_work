package pl.ls.zad_25_work;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

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
    @PostMapping("/delete/{id}")
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
