package com.moqaddas.banking.data;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class CsvDataProvider {
  private CsvDataProvider() {}

  @DataProvider(name = "transfers")
  public static Object[][] transfers() throws IOException {
    return readCsv("/data/transfers.csv");
  }

  @DataProvider(name = "billpay")
  public static Object[][] billpay() throws IOException {
    return readCsv("/data/billpay.csv");
  }

  private static Object[][] readCsv(String path) throws IOException {
    List<Object[]> rows = new ArrayList<>();
    try (InputStream in = CsvDataProvider.class.getResourceAsStream(path)) {
      if (in == null) {
        throw new IOException("CSV resource not found: " + path);
      }
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
        String line = reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
          if (line.isBlank()) {
            continue;
          }
          String[] tokens = line.split(",");
          for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
          }
          rows.add(tokens);
        }
      }
    }
    Object[][] data = new Object[rows.size()][];
    return rows.toArray(data);
  }
}
