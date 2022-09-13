package ua.myElevator;

import java.util.List;

public class Building {

  private List<Floor> floors;

  public Building(List<Floor> floors) {
    this.floors = floors;
  }

  public Building(int minPeopleOnTheFloor, int maxPeopleOnTheFloor, int minFloorsInTheBuilding,
      int maxFloorsInTheBuilding) {
    this.floors = Generator.floorsGenerator(minPeopleOnTheFloor, maxPeopleOnTheFloor,
        minFloorsInTheBuilding, maxFloorsInTheBuilding);
  }

  public List<Floor> getFloors() {
    return floors;
  }

  public void setFloors(List<Floor> floors) {
    this.floors = floors;
  }
}
