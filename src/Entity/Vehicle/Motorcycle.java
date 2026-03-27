package Entity.Vehicle;

public class Motorcycle implements Vehicle {

    private final String vehicleNumber;
    private final VehicleSize size;

    public Motorcycle(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.size = VehicleSize.SMALL;
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
