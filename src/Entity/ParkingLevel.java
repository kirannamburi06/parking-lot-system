package Entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {

    private final Long id;
    private final List<ParkingRow> rows;
    private ParkingLot parkingLot;

    public ParkingLevel(Long id, List<ParkingRow> rows){
        this.id = id;
        this.rows = rows;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Long getId(){
        return this.id;
    }

    public ParkingLot getParkingLot(){
        return this.parkingLot;
    }

    public List<ParkingRow> getRows(){
        return this.rows;
    }

    public boolean isAvailable(){
        for(ParkingRow row : rows){
            if(row.isAvailable()){
                return true;
            }
        }
        return false;
    }

    public List<ParkingRow> findRowsByAvailability(){
        List<ParkingRow> availableRows = new ArrayList<>();

        for(ParkingRow row : rows){
            if(row.isAvailable()){
                availableRows.add(row);
            }
        }

        return availableRows;
    }

}
