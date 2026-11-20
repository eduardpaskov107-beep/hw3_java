package app.csvReader.parsers;

import app.csvReader.parsers.data.Address;

import java.io.IOException;
import java.util.*;

public abstract class Parser {
  final List<Address> data = new ArrayList<>();

  public abstract void parseFile(String fileName) throws IOException;

  public String getInfo() {
    StringBuilder sb = new StringBuilder();
    Map<Address, Integer> repeat = getRepeat();

    sb.append("Repeat:" + "\n");
    int count = 0;
    for (Map.Entry<Address, Integer> entry : repeat.entrySet()) {
      if (entry.getValue() > 1) {
        count++;
        sb.append(makeAddressToStr(entry.getKey()) +
            ", Rereats: " + entry.getValue() + "\n");
      }
    }
    if (count == 0) {
      sb.append("No repeats found\n");
    }

    Map<String, int[]> floorCount = getFloorCount();

    sb.append("Count floor:" + "\n");
    for (Map.Entry<String, int[]> entry : floorCount.entrySet()) {
      sb.append("City: " + entry.getKey() + "\n");
      for (int fl = 0; fl < 5; fl++) {
        if (entry.getValue()[fl] > 0) {
          sb.append((fl + 1) + " floor: " + entry.getValue()[fl] + "\n");
        }
      }
    }

    return sb.toString();
  }

  private Map<Address, Integer> getRepeat() {
    Map<Address, Integer> countRepeat = new HashMap<>();

    for (Address row : data) {
      if (countRepeat.containsKey(row)) {
        countRepeat.put(row, countRepeat.get(row) + 1);
      }
      else {
        countRepeat.put(row, 1);
      }
    }

    return countRepeat;
  }

  private Map<String, int[]> getFloorCount() {
    Map<String, int[]> floorCount = new HashMap<>();

    for (Address row : data) {
      if (!floorCount.containsKey(row.getCity())) {
        floorCount.put(row.getCity(), new int[5]);
      }
      int[] floors = floorCount.get(row.getCity());
      floors[row.getFloor() - 1]++;
    }

    return floorCount;
  }

  private String makeAddressToStr(Address row) {
    return row.getCity() + ", " + row.getStreet() + ", "
        + row.getHouse() + ", " + row.getFloor();
  }
}
