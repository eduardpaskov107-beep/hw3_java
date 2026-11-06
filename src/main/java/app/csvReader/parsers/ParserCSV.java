package app.csvReader.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParserCSV extends Parser {
  @Override
  public void parseFile(String fileName) {
    try (FileReader fl = new FileReader(fileName);
         BufferedReader bf = new BufferedReader(fl)) {

      //это нужно чтобы убрать шапку
      String line = bf.readLine();

      while ((line = bf.readLine()) != null) {
        String[] row = line.split(";");
        data.add(row);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
