package ua.myElevator;

public class Main {

  public static void main(String[] args) {

    int minPeopleOnTheFloor = 0;
    int maxPeopleOnTheFloor = 10;
    int minFloorsInTheBuilding = 5;
    int maxFloorsInTheBuilding = 20;

    Building building = new Building(minPeopleOnTheFloor, maxPeopleOnTheFloor,
        minFloorsInTheBuilding,
        maxFloorsInTheBuilding);
    Elevator elevator = new Elevator(building);
    elevator.startElevator();
  }
}
