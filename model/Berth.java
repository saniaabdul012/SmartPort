package model;

public class Berth {

    private String berthId;
    private String berthName;
    private boolean occupied;

    public Berth(String berthId, String berthName, boolean occupied) {
        this.berthId = berthId;
        this.berthName = berthName;
        this.occupied = occupied;
    }

    public String getBerthId() {
        return berthId;
    }

    public void setBerthId(String berthId) {
        this.berthId = berthId;
    }

    public String getBerthName() {
        return berthName;
    }

    public void setBerthName(String berthName) {
        this.berthName = berthName;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void displayDetails() {
        System.out.println("Berth ID: " + berthId);
        System.out.println("Berth Name: " + berthName);
        System.out.println("Occupied: " + occupied);
    }
}