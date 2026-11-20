package app.csvReader.userTools;

import app.csvReader.parsers.Parser;
import app.csvReader.parsers.ParserCSV;
import app.csvReader.parsers.ParserXML;

import java.util.Scanner;

public class ConsoleApp {
  private static final Scanner scanner = new Scanner(System.in);

  public void start() {
    System.out.println("Welcome to CSV Reader");

    while (true) {
      System.out.println("Please enter the file name:");
      String fileName = scanner.nextLine().trim();

      if (fileName.equals("exit")) {
        break;
      }

      if (fileName.isEmpty()) {
        System.out.println("Wrong file name");
        continue;
      }

      long startTime = System.currentTimeMillis();
      Parser parser;
      if (fileName.endsWith(".csv")) {
        parser = new ParserCSV();
      }
      else if (fileName.endsWith(".xml")) {
        parser = new ParserXML();
      }
      else {
        System.out.println("Wrong format(need .csv or .xml)");
        continue;
      }

      try {
        parser.parseFile(fileName);
        String info = parser.getInfo();
        long endTime = System.currentTimeMillis();

        System.out.println(info);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
