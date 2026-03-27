package Entity.ParkingSpot;

import Entity.ParkingRow;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleSize;

import java.util.Set;

public interface ParkingSpot {

    boolean isAvailable();
    ParkingSpotType getParkingSpotType();
    Vehicle getVehicle();
    void occupySpot(Vehicle vehicle);
    void vacate();
    Long getSpotId();
    ParkingRow getParkingRow();
    void setParkingRow(ParkingRow row);
}
