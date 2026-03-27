package Service;

import Entity.Vehicle.*;
import Repository.VehicleRepo;

public class VehicleService {

    private final VehicleRepo vehicleRepo;

    public VehicleService(VehicleRepo vehicleRepo){
        this.vehicleRepo = vehicleRepo;
    }

    public Vehicle addVehicle(String vehicleNumber, VehicleSize size){
        Vehicle vehicle = null;
        if(size == VehicleSize.LARGE){
            vehicle = new Bus(vehicleNumber);
        } else if(size == VehicleSize.SMALL){
            vehicle = new Motorcycle(vehicleNumber);
        } else if(size == VehicleSize.MEDIUM){
            vehicle = new Car(vehicleNumber);
        }

        return vehicle;
    }

}
