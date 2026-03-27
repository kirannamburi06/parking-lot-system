package Repository;

import Entity.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepo {

    Map<Long, Customer> customers = new HashMap<>();

    public void add(Customer customer){
        customers.put(customer.getId(), customer);
    }

    public List<Customer> getCustomers(){
        return new ArrayList<>(customers.values());
    }

    public Customer findById(Long id){
        return customers.getOrDefault(id, null);
    }

}
