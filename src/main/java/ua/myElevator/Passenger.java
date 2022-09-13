package ua.myElevator;

public class Passenger {

  private int currentFloor;
  private int desiredFloor;
  private String direction;

  public Passenger(int currentFloor, int desiredFloor) {
    this.currentFloor = currentFloor;
    this.desiredFloor = desiredFloor;
    this.direction = calculateDirection();
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  public int getDesiredFloor() {
    return desiredFloor;
  }

  public void setDesiredFloor(int desiredFloor) {
    this.desiredFloor = desiredFloor;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  private String calculateDirection() {
    return (desiredFloor > currentFloor) ? "Up" : "Down";
  }
}
