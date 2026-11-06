package app.csvReader.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Parser {
  List<String[]> data = new ArrayList<>();

  public abstract void parseFile(String fileName);

  private Map<String, Integer> getRepeat() {
    Map<String, Integer> countRepeat = new HashMap<>();

    for (String[] row : data) {
      if (countRepeat.containsKey(row[0])) {
        countRepeat.put(row[0], countRepeat.get(row[0]) + 1);
      }
      else {
        countRepeat.put(row[0], 1);
      }
    }

    return countRepeat;
  }

  private int[] getFloorCount() {
    int[] floorCount = new int[5];

    //пока оставим так, чтобы не падал с плохими данными
    for (String[] row : data) {
      if (!isNumber(row[3])) {
        continue;
      }

      if (Integer.parseInt(row[3]) <= 0
          || Integer.parseInt(row[3]) > 5) {
        continue;
      }
      floorCount[Integer.parseInt(row[3]) - 1]++;
    }

    return floorCount;
  }

  public String getInfo() {
    StringBuilder sb = new StringBuilder();
    Map<String, Integer> repeat = getRepeat();

    for (Map.Entry<String, Integer> entry : repeat.entrySet()) {
      if (entry.getValue() > 1) {
        sb.append(entry.getKey() + " " + entry.getValue() + "\n");
      }
    }

    int[] floorCount = getFloorCount();

    for (int i = 0; i < floorCount.length; i++) {
      sb.append("Этаж " + (i + 1) + ": " + floorCount[i] + "\n");
    }

    return sb.toString();
  }

  //пока костыльный метод)
  public static boolean isNumber(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
