import org.CsvDemo.CSVReader;
import org.CsvDemo.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVReaderTest {

    @Test
    void testReadCsv() throws IOException {
        CSVReader csvProcessor = new CSVReader();
        String csvFilePath = "documents/csvFile.csv";

        // Mock the BufferedReader to simulate reading CSV lines
        Mockito.when(csvProcessor.createBufferedReader(csvFilePath))
                .thenReturn(new BufferedReader(new StringReader("value1,value2,value3\nvalue4,value5,value6")));

        List<Customer> customers = csvProcessor.readCSV(csvFilePath);

        assertEquals(2, customers.size());

    }
}

