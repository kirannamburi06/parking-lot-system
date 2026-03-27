package Entity;

import Entity.ParkingSpot.ParkingSpot;
import Entity.ParkingSpot.ParkingSpotType;
import Entity.Vehicle.VehicleSize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParkingRow {

    private final Long id;
    private final List<ParkingSpot> spots;
    private ParkingLevel parkingLevel;

    public ParkingRow(Long id, List<ParkingSpot> spots){
        this.id = id;
        this.spots = spots;
    }

    public void setParkingLevel(ParkingLevel parkingLevel) {
        this.parkingLevel = parkingLevel;
    }

    public Long getId(){
        return this.id;
    }

    public List<ParkingSpot> getSpots(){
        return this.spots;
    }

    public ParkingLevel getParkingLevel(){
        return this.parkingLevel;
    }

    public boolean isAvailable(){
        for(ParkingSpot spot : spots){
            if(spot.isAvailable()){
                return true;
            }
        }
        return false;
    }

    public Map<ParkingSpotType, List<ParkingSpot>> findSpotsByAvailability(){
        Map<ParkingSpotType, List<ParkingSpot>> availableSpots = new LinkedHashMap<>();

        for(ParkingSpot spot : spots){
            if(spot.isAvailable()){
                availableSpots.computeIfAbsent(
                        spot.getParkingSpotType(), (l) -> new ArrayList<>())
                        .add(spot);
            }
        }

        return availableSpots;
    }

    public List<ParkingSpot> findSpotsByVehicleSize(VehicleSize vehicleSize){

        Map<ParkingSpotType, List<ParkingSpot>> availableSpots = findSpotsByAvailability();

        if(vehicleSize == VehicleSize.SMALL){
            if(availableSpots.containsKey(ParkingSpotType.MOTORCYCLE_SPOT))
                return List.of(availableSpots.get(ParkingSpotType.MOTORCYCLE_SPOT).getFirst());
            else if(availableSpots.containsKey(ParkingSpotType.COMPACT_SPOT))
                return List.of(availableSpots.get(ParkingSpotType.COMPACT_SPOT).getFirst());
            else if(availableSpots.containsKey(ParkingSpotType.LARGE_SPOT))
                return List.of(availableSpots.get(ParkingSpotType.LARGE_SPOT).getFirst());
            else
                return null;
        } else if(vehicleSize == VehicleSize.MEDIUM){
            if(availableSpots.containsKey(ParkingSpotType.COMPACT_SPOT))
                return List.of(availableSpots.get(ParkingSpotType.COMPACT_SPOT).getFirst());
            else if(availableSpots.containsKey(ParkingSpotType.LARGE_SPOT))
                return List.of(availableSpots.get(ParkingSpotType.LARGE_SPOT).getFirst());
            else
                return null;
        } else{
            int c = 0;
            List<ParkingSpot> consecutiveSpots = new ArrayList<>();
            for(ParkingSpot spot : spots){
                if(spot.isAvailable() && spot.getParkingSpotType() == ParkingSpotType.LARGE_SPOT){
                    c++;
                    consecutiveSpots.add(spot);
                    if(c == 5){
                        return consecutiveSpots;
                    }
                } else{
                    c = 0;
                    consecutiveSpots.clear();
                }
            }
            return null;
        }
    }

}
