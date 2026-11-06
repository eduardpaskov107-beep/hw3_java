package app.csvReader.parsers;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParserXML extends Parser {

  @Override
  public void parseFile(String fileName) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(new File(fileName));

      NodeList itemList = document.getElementsByTagName("item");

      for (int i = 0; i < itemList.getLength(); i++) {
        Node node = itemList.item(i);

        Element element = (Element) node;
        String[] row = new String[4];

        row[0] = element.getAttribute("city");
        row[1] = element.getAttribute("street");
        row[2] = element.getAttribute("house");
        row[3] = element.getAttribute("floor");
        data.add(row);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
