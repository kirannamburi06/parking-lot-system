package Entity;

import Entity.ParkingSpot.ParkingSpot;
import Entity.Vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {

    private final Long id;
    private final List<ParkingSpot> spots;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private final Customer customer;
    private final Vehicle vehicle;

    public Ticket(Long id, List<ParkingSpot> spots, Customer customer, Vehicle vehicle){
        this.spots = spots;
        this.id = id;
        this.customer = customer;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Long getId() {
        return id;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setExitTime(LocalDateTime time){
        this.exitTime = time;
    }
}
