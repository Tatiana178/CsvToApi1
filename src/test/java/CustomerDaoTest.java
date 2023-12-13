import org.CsvDemo.Customer;
import org.CsvDemo.CustomerDao;
import org.CsvDemo.CustomerDatabase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDaoTest {

    @Test
    void testSaveAndGetCustomer() {
        CustomerDatabase database = new CustomerDatabase();
        CustomerDao customerDao = new CustomerDao(database);

        Customer customer = new Customer("ref123", "John Doe", "123 Main St", "", "City", "State", "Country", "12345");
        customerDao.saveCustomer(customer);

        Customer retrievedCustomer = customerDao.getCustomer("ref123");
        assertEquals(customer, retrievedCustomer);
    }
}
