package management;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import model.Ship;
import model.Cargo;
import model.Berth;
import model.Crane;
public class Port {
    private String portName;
    private ArrayList<Ship> ships;
    private ArrayList<Cargo> cargos;
    private ArrayList<Berth> berths;
    private ArrayList<Crane> cranes;
    private Queue<Ship> waitingShips;
    public Port(String portName) {
        this.portName = portName;
        ships = new ArrayList<>();
        cargos = new ArrayList<>();
        berths = new ArrayList<>();
        cranes = new ArrayList<>();
        waitingShips = new LinkedList<>();
    }
    public String getPortName() {
        return portName;
    }
    public void setPortName(String portName) {
        this.portName = portName;
    }
    public void addShip(Ship ship) {
        ships.add(ship);
        System.out.println("Ship added to port.");
    }
    public void addCargo(Cargo cargo) {
        cargos.add(cargo);
        System.out.println("Cargo added to port.");
    }
    public void addBerth(Berth berth) {
        berths.add(berth);
        System.out.println("Berth added to port.");
    }
    public void addCrane(Crane crane) {
        cranes.add(crane);
        System.out.println("Crane added to port.");
    }
    public void addWaitingShip(Ship ship) {
        waitingShips.offer(ship);
        System.out.println("Ship added to waiting queue.");
    }
    public void displayShips() {
        System.out.println("\nShips in Port:");
        for (Ship ship : ships) {
            ship.displayDetails();
            System.out.println();
        }
    }
    public void displayCargos() {
        System.out.println("\nCargo in Port:");
        for (Cargo cargo : cargos) {
            System.out.println("Cargo ID: " + cargo.getCargoId());
        }
    }
    public void displayPortDetails() {
        System.out.println("\n===== PORT DETAILS =====");
        System.out.println("Port Name: " + portName);
        System.out.println("Ships: " + ships.size());
        System.out.println("Cargo: " + cargos.size());
        System.out.println("Berths: " + berths.size());
        System.out.println("Cranes: " + cranes.size());
        System.out.println("Waiting Ships: " + waitingShips.size());
    }
}
