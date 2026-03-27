package Repository;

import Entity.Vehicle.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepo {

    Map<String, Vehicle> vehicles = new HashMap<>();

    public Map<String, Vehicle> getVehicles() {
        return vehicles;
    }

    public void add(Vehicle vehicle){
        vehicles.put(vehicle.getVehicleNumber(), vehicle);
    }

    public Vehicle findByVehicleNumber(String vehicleNumber){
        return vehicles.getOrDefault(vehicleNumber, null);
    }
}
