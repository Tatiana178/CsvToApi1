package org.CsvDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    String filePath = "documents/csvFile";
    public List<Customer> readCSV(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Customer customer = new Customer(
                        data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]
                );
                customers.add(customer);
            }
        }
        return customers;
    }

    public Object createBufferedReader(String csvFilePath) {
        return null;
    }
}
