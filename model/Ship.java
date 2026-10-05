package model;

import enums.ShipType;
import enums.Status;

public class Ship {

	private String shipId;
	private String shipName;
	private ShipType shipType;
	private Status status;


public Ship(String shipId, String shipName, ShipType shipType, Status status){

    	this.shipId = shipId;
   	this.shipName = shipName;
   	this.shipType = shipType;
        this.status = status;

	}

	public String getShipId() {
   	 return shipId;
	}
	public void setShipId(String shipId) {
         this.shipId = shipId;
}
	public String getShipName() {
    	return shipName;
}
	public void setShipName(String shipName) {
    	this.shipName = shipName;
}
	public ShipType getShipType() {
    	return shipType;
}
	public void setShipType(ShipType shipType) {
    	this.shipType = shipType;
}
	public Status getStatus() {
   	 return status;
}

	public void setStatus(Status status) {
    	this.status = status;
}
	public void displayDetails() {
    	 System.out.println("Ship ID: " + shipId);
    	 System.out.println("Ship Name: " + shipName);
    	 System.out.println("Ship Type: " + shipType);
    	 System.out.println("Status: " + status);
}
}