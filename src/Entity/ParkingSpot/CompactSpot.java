package Entity.ParkingSpot;

import Entity.ParkingRow;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompactSpot implements ParkingSpot {

    private final Long id;
    private Vehicle vehicle;
    private final ParkingSpotType parkingSpotType;
    private ParkingRow parkingRow;

    public CompactSpot(Long id)
    {
        this.id = id;
        this.parkingSpotType = ParkingSpotType.COMPACT_SPOT;
    }

    public void setParkingRow(ParkingRow row) {
        this.parkingRow = row;
    }

    @Override
    public boolean isAvailable() {
        return this.vehicle == null;
    }

    @Override
    public ParkingSpotType getParkingSpotType() {
        return this.parkingSpotType;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public void occupySpot(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }

    @Override
    public Long getSpotId() {
        return this.id;
    }

    @Override
    public ParkingRow getParkingRow() {
        return this.parkingRow;
    }
}
