package org.CsvDemo;

import org.springframework.stereotype.Component;

@Component
public class CustomerDao {

    private final CustomerDatabase database;

    public CustomerDao(CustomerDatabase database) {
        this.database = database;
    }

    public void saveCustomer(Customer customer) {
        database.saveCustomer(customer);
    }

    public Customer getCustomer(String customerRef) {
        return database.getCustomer(customerRef);
    }
}
