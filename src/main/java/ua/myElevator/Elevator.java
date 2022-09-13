package ua.myElevator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Elevator {

  private int currentPassengersCount = 0;
  private String direction = "Up";
  private int currentFloor = 1;
  private int maxFloor = Generator.getMaxFloors();
  private Building building;
  private Set<Passenger> passengersInTheElevator = new HashSet<>();

  public Elevator(Building building) {
    this.building = building;
  }

  public void startElevator() {
    Set<Passenger> passengers;

    while (arePassengersDidNotReach(building.getFloors(), currentPassengersCount)) {
      Floor floor = building.getFloors().stream()
          .filter(f -> f.getNumberOfTheFloor() == currentFloor)
          .findFirst()
          .get();
      if (direction.equals("Up")) {
        passengers = handleFloor(floor, "Up");
        if (currentFloor == maxFloor) {
          moveElevatorWhenItOnTheLastFloor(passengers, floor, "Down");
          continue;
        }
        moveElevator(passengers, floor);
        currentFloor++;
      } else {
        passengers = handleFloor(floor, "Down");
        if (currentFloor == 1) {
          moveElevatorWhenItOnTheLastFloor(passengers, floor, "Up");
          continue;
        }
        moveElevator(passengers, floor);
        currentFloor--;
      }
    }
  }

  private boolean arePassengersDidNotReach(List<Floor> floors, int currentPassengersCount) {
    int result = 0;
    for (Floor floor : floors) {
      result += floor.getPassengersOnTheFloor().stream()
          .filter(p -> p.getCurrentFloor() != p.getDesiredFloor()).count();
    }
    return result > 0 || currentPassengersCount > 0;
  }

  private int calculateMaxDesiredFloor() {
    List<Integer> desiredFloorNumbers = passengersInTheElevator.stream()
        .map(Passenger::getDesiredFloor)
        .toList();
    if (desiredFloorNumbers.isEmpty()) {
      return Generator.getMaxFloors();
    } else {
      return Collections.max(desiredFloorNumbers);
    }
  }

  private void putPassengersIntoElevator(Set<Passenger> passengers, Floor floor) {
    for (Passenger passenger : passengers) {
      int elevatorCapacity = 5;
      if (currentPassengersCount < elevatorCapacity) {
        passengersInTheElevator.add(passenger);
        floor.getPassengersOnTheFloor().remove(passenger);
        currentPassengersCount++;
      }
    }
  }

  private void moveElevator(Set<Passenger> passengers, Floor floor) {
    putPassengersIntoElevator(passengers, floor);
    maxFloor = calculateMaxDesiredFloor();
    showFrame(floor);
  }

  private void moveElevatorWhenItOnTheLastFloor(Set<Passenger> passengers, Floor floor,
      String direction) {
    putPassengersIntoElevator(passengers, floor);
    maxFloor = calculateMaxDesiredFloor();
    this.direction = direction;
    showFrame(floor);
  }

  private Set<Passenger> handleFloor(Floor floor, String direction) {
    Set<Passenger> passengers = passengersInTheElevator.stream()
        .filter(p -> p.getDesiredFloor() == currentFloor)
        .peek(p -> p.setCurrentFloor(currentFloor))
        .collect(Collectors.toSet());
    passengersInTheElevator.removeAll(passengers);
    floor.getPassengersOnTheFloor().addAll(passengers);
    maxFloor = calculateMaxDesiredFloor();
    currentPassengersCount = passengersInTheElevator.size();
    Set<Passenger> passengersResult = floor.getPassengersOnTheFloor().stream()
        .filter(p -> p.getDirection().equals(direction)
            && p.getCurrentFloor() != p.getDesiredFloor())
        .collect(Collectors.toSet());
    return passengersResult;
  }

  private void showFrame(Floor floor) {
    System.out.println("Current floor: " + floor.getNumberOfTheFloor() +
        ", remaining passengers on the floor: " + floor.getPassengersOnTheFloor().stream()
        .filter(p -> p.getCurrentFloor() != p.getDesiredFloor()).count() +
        ", current elevator floor: " + currentFloor +
        ", current elevator direction: " + direction +
        ", current passengers in elevator: " + passengersInTheElevator.size());
  }
}
