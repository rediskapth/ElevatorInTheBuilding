package ua.myElevator;

import java.util.Set;

public class Floor {

  private int numberOfTheFloor;
  private Set<Passenger> passengersOnTheFloor;

  public Floor(int numberOfTheFloor, Set<Passenger> passengersOnTheFloor) {
    this.numberOfTheFloor = numberOfTheFloor;
    this.passengersOnTheFloor = passengersOnTheFloor;
  }

  public int getNumberOfTheFloor() {
    return numberOfTheFloor;
  }

  public void setNumberOfTheFloor(int numberOfTheFloor) {
    this.numberOfTheFloor = numberOfTheFloor;
  }

  public Set<Passenger> getPassengersOnTheFloor() {
    return passengersOnTheFloor;
  }

  public void setPassengersOnTheFloor(Set<Passenger> passengersOnTheFloor) {
    this.passengersOnTheFloor = passengersOnTheFloor;
  }
}
