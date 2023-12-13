package org.CsvDemo;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {
    String apiUrl = "https://localhost:8080/customers";
    public void sendToApi(Customer customer, String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            // Convert customer to JSON and write to the API
            String json = convertToJson(customer);
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        connection.disconnect();
    }

    private String convertToJson(Customer customer) {

        return new Gson().toJson(customer);
    }
}

