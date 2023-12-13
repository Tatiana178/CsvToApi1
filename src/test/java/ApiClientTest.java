import org.CsvDemo.ApiClient;
import org.CsvDemo.Customer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.mockito.Mockito.*;

public class ApiClientTest {

    @Test
    void testSendToApi() throws IOException {
        ApiClient apiClient = new ApiClient();
        String apiUrl = "https://localhost:8080/customers";
        Customer customer = new Customer("ref", "name", "address1", "address2", "town", "county", "country", "postcode");

        // Mock HttpURLConnection and OutputStream
        HttpURLConnection connection = mock(HttpURLConnection.class);
        OutputStream outputStream = mock(OutputStream.class);
        when(connection.getOutputStream()).thenReturn(outputStream);
        doNothing().when(outputStream).write(any(byte[].class), anyInt(), anyInt());

        // Mock the URL connection
        URL url = new URL(apiUrl);
        when(url.openConnection()).thenReturn(connection);

        apiClient.sendToApi(customer, apiUrl);

        // Add more assertions based on your specific requirements
        verify(connection, times(1)).setRequestMethod("POST");
        verify(connection, times(1)).setRequestProperty("Content-Type", "application/json");
        verify(connection, times(1)).setDoOutput(true);
    }
}

