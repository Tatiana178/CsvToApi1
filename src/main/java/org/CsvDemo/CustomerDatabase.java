package org.CsvDemo;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerDatabase {
    private static final Map<String, Customer> database = new HashMap<>();

    public void saveCustomer(Customer customer) {
        database.put(customer.getCustomerRef(), customer);
    }

    public Customer getCustomer(String customerRef) {
        return database.get(customerRef);
    }
}
