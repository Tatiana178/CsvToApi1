import com.fasterxml.jackson.databind.ObjectMapper;
import org.CsvDemo.Customer;
import org.CsvDemo.CustomerController;
import org.CsvDemo.CustomerDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CustomerControllerTest {
    @Test
    void testSaveCustomer() throws Exception {
        CustomerDao customerDao = Mockito.mock(CustomerDao.class);
        CustomerController customerController = new CustomerController(customerDao);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer customer = new Customer("ref123", "John Doe", "123 Main St", "", "City", "State", "Country", "12345");

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .content(asJsonString(customer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(customerDao, Mockito.times(1)).saveCustomer(any(Customer.class));
    }

    @Test
    void testGetCustomer() throws Exception {
        CustomerDao customerDao = Mockito.mock(CustomerDao.class);
        CustomerController customerController = new CustomerController(customerDao);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        Customer customer = new Customer("ref123", "John Doe", "123 Main St", "", "City", "State", "Country", "12345");
        Mockito.when(customerDao.getCustomer("ref123")).thenReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/ref123")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerRef").value("ref123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").value("John Doe"));
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
