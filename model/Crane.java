package model;

public class Crane {

    private String craneId;
    private String craneName;
    private boolean available;

    public Crane(String craneId, String craneName, boolean available) {
        this.craneId = craneId;
        this.craneName = craneName;
        this.available = available;
    }

    public String getCraneId() {
        return craneId;
    }

    public void setCraneId(String craneId) {
        this.craneId = craneId;
    }

    public String getCraneName() {
        return craneName;
    }

    public void setCraneName(String craneName) {
        this.craneName = craneName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayDetails() {
        System.out.println("Crane ID: " + craneId);
        System.out.println("Crane Name: " + craneName);
        System.out.println("Available: " + available);
    }
}