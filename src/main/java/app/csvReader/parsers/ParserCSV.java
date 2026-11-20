package app.csvReader.parsers;

import app.csvReader.parsers.data.Address;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ParserCSV extends Parser {
  @Override
  public void parseFile(String fileName)
      throws IOException {

    // элементы из try() сами закрываются когда блок закончит свое выполнение
    // эти 2 элемента занмают дескрипторы ОС которые если накопятся
    // то может упасть в ошибку. по сути эта запись замена блока finally
    try (FileReader fl = new FileReader(fileName);
         BufferedReader bf = new BufferedReader(fl)) {

      //это нужно чтобы убрать шапку
      String line = bf.readLine();

      while ((line = bf.readLine()) != null) {
        String[] row = line.split(";");

        if (row.length != 4) {
          System.err.println("Skip wrong line: "
              + Arrays.toString(row));
          continue;
        }

        deleteQuotes(row);
        try {
          String city = row[0];
          String street = row[1];
          int house = Integer.parseInt(row[2]);
          int floor = Integer.parseInt(row[3]);

          if (house < 1 || floor < 1 || floor > 5) {
            System.err.println("Invalid number: " + "Floor: " + floor
                + ", House: " + house);
            continue;
          }
          data.add(new Address(city, street, house, floor));
        } catch (NumberFormatException e) {
          System.err.println("Skip wrong line: " + e.getMessage());
        }
      }
    }
  }

  private void deleteQuotes(String[] row) {
    for (int i = 0; i < row.length; i++) {
      row[i] = row[i].replace("\"", "");
    }
  }
}
