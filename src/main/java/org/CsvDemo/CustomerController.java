package org.CsvDemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao)

    {
        this.customerDao = customerDao;
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @GetMapping("/{customerRef}")
    public Customer getCustomer(@PathVariable String customerRef) {
        return customerDao.getCustomer(customerRef);
    }
}