package ua.myElevator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Generator {

  private static int maxFloors;
  private static final Random random = new Random();

  public static Set<Passenger> passengersGenerator(int minPeopleOnTheFloor, int maxPeopleOnTheFloor,
      int currentFloorInTheBuilding, int maxFloorInTheBuilding) {
    Set<Passenger> passengersOnTheFloor = new HashSet<>();

    int passengerCount =
        random.nextInt(maxPeopleOnTheFloor - minPeopleOnTheFloor + 1) + minPeopleOnTheFloor;
    int desiredFloor;

    for (int i = 0; i < passengerCount; i++) {
      desiredFloor = random.nextInt(maxFloorInTheBuilding - 1) + 1;

      while (desiredFloor == currentFloorInTheBuilding) {
        desiredFloor = random.nextInt(maxFloorInTheBuilding - 1) + 1;
      }
      passengersOnTheFloor.add(new Passenger(currentFloorInTheBuilding, desiredFloor));
    }
    return passengersOnTheFloor;
  }

  public static List<Floor> floorsGenerator(int minPeopleOnTheFloor, int maxPeopleOnTheFloor,
      int minFloorsInTheBuilding, int maxFloorsInTheBuilding) {
    List<Floor> floors = new ArrayList<>();

    int floorsCount = random.nextInt(maxFloorsInTheBuilding - minFloorsInTheBuilding + 1)
        + minFloorsInTheBuilding;
    maxFloors = floorsCount;
    for (int i = 1; i <= floorsCount; i++) {
      floors.add(new Floor(i,
          passengersGenerator(minPeopleOnTheFloor, maxPeopleOnTheFloor, i, maxFloors)));
    }
    return floors;
  }

  public static int getMaxFloors() {
    return maxFloors;
  }
}
