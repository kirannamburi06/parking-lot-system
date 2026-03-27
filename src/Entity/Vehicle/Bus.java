package Entity.Vehicle;

public class Bus implements Vehicle {

    private final String vehicleNumber;
    private final VehicleSize size;

    public Bus(String vehicleNumber){
        this.vehicleNumber = vehicleNumber;
        this.size = VehicleSize.LARGE;
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
