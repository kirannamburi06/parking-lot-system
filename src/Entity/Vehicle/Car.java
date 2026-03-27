package Entity.Vehicle;

public class Car implements Vehicle {

    private final String vehicleNumber;
    private final VehicleSize size;

    public Car(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.size = VehicleSize.MEDIUM;
    }

    @Override
    public String getVehicleNumber() {
        return this.vehicleNumber;
    }

    @Override
    public VehicleSize getSize() {
        return this.size;
    }
}
