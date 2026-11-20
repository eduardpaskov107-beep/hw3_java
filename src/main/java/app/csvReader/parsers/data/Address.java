package app.csvReader.parsers.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
  private String city;
  private String street;
  private int house;
  private int floor;
}
