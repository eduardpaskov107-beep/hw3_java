package app.csvReader.parsers;

import app.csvReader.parsers.data.Address;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParserXML extends Parser {

  @Override
  public void parseFile(String fileName) throws IOException {
    try {
      //делаем фабрику строителей и потом создаем строителя который
      //создает из xml файла дерево(примерно похоже на работу с JSON)
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(fileName));

      NodeList itemList = document.getElementsByTagName("item");

      for (int i = 0; i < itemList.getLength(); i++) {
        Node node = itemList.item(i);
        Element element = (Element) node;

        try {
          String city = element.getAttribute("city");
          String street = element.getAttribute("street");
          int house = Integer.parseInt(element.getAttribute("house"));
          int floor = Integer.parseInt(element.getAttribute("floor"));

          if (house < 1 || floor < 1 || floor > 5) {
            throw new NumberFormatException("Invalid number: house=" +
                house + ", floor=" + floor);
          }
          data.add(new Address(city, street, house, floor));
        } catch (NumberFormatException e) {
          System.err.println("Skip bad XML row №" + i + ": " + e.getMessage());
        }
      }
    } catch (ParserConfigurationException | SAXException e) {
      throw new IOException("Failed to parse XML: " + fileName, e);
    }
  }
}