package Service;

import Entity.Customer;
import Repository.CustomerRepo;

import java.util.List;

public class CustomerService {

    private final CustomerRepo customerRepo;
    private Long id = 1L;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer addCustomer(String customerName, String email, String phone){
        Customer customer = new Customer(id++, customerName, email, phone);
        customerRepo.add(customer);
        return customer;
    }

    public List<Customer> getCustomers(){
        return customerRepo.getCustomers();
    }
}
