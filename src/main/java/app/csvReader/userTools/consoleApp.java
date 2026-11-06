package app.csvReader.userTools;

import app.csvReader.parsers.Parser;
import app.csvReader.parsers.ParserCSV;
import app.csvReader.parsers.ParserXML;

import java.util.Scanner;

public class consoleApp {
  private static Scanner scanner = new Scanner(System.in);

  public void start() {
    System.out.println("Welcome to CSV Reader");

    while (true) {
      System.out.println("Please enter the file name:");
      String fileName = scanner.nextLine();

      if (fileName.trim().equals("exit")) {
        break;
      }

      if (fileName.trim().isEmpty()) {
        System.out.println("Wrong file name");
        continue;
      }

      Parser parser = null;
      if (fileName.trim().endsWith(".csv")) {
        parser = new ParserCSV();
      } else if (fileName.trim().endsWith(".xml")) {
        parser = new ParserXML();
      }

      if (parser != null) {
        parser.parseFile(fileName);
        System.out.println(parser.getInfo());
      }
    }
  }
}
