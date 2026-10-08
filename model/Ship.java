
package model;

import enums.ShipType;
import enums.Status;

public class Ship {

private String shipId;
private String shipName;
private ShipType shipType;
private Status status;
private Berth berth;

public Ship(String shipId, String shipName, ShipType shipType, Status status) {
    this.shipId = shipId;
    this.shipName = shipName;
    this.shipType = shipType;
    this.status = status;
}

public String getShipId() {
    return shipId;
}

public String getShipName() {
    return shipName;
}

public ShipType getShipType() {
    return shipType;
}

public Status getStatus() {
    return status;
}

public void setStatus(Status status) {
    this.status = status;
}

public Berth getBerth() {
    return berth;
}

public void setBerth(Berth berth) {
    this.berth = berth;
}

public void displayDetails() {
    System.out.println(
        shipId + " | " + shipName
        + " | " + shipType
        + " | Status: " + status
        + " | Berth: "
        + (berth == null ? "None" : berth.getBerthId())
    );
}

}
