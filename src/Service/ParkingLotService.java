package Service;

import Entity.ParkingLevel;
import Entity.ParkingLot;
import Entity.ParkingRow;
import Entity.ParkingSpot.CompactSpot;
import Entity.ParkingSpot.LargeSpot;
import Entity.ParkingSpot.MotorcycleSpot;
import Entity.ParkingSpot.ParkingSpot;
import Entity.Vehicle.VehicleSize;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {

    public ParkingLot initializeParkingLot(int levels, int rows, int spots){
        long spotId = 1L;
        long rowId = 1L;
        long levelId = 1L;
        List<ParkingLevel> parkingLevels = new ArrayList<>();

        for(int level = 0; level < levels; level++){
            List<ParkingRow> parkingRows = new ArrayList<>();

            for(int row = 0; row < rows; row++){
                List<ParkingSpot> parkingSpots = new ArrayList<>();

                for(int spot = 0; spot < spots; spot++){
                    if(spot < spots * 0.2){
                        parkingSpots.add(new MotorcycleSpot(spotId++));
                    } else if(spot < spots * 0.6){
                        parkingSpots.add(new CompactSpot(spotId++));
                    } else{
                        parkingSpots.add(new LargeSpot(spotId++));
                    }
                }

                ParkingRow parkingRow = new ParkingRow(rowId++, parkingSpots);

                for(ParkingSpot spot : parkingSpots){
                    spot.setParkingRow(parkingRow);
                }

                parkingRows.add(parkingRow);
            }

            ParkingLevel parkingLevel = new ParkingLevel(levelId++, parkingRows);

            for(ParkingRow parkingRow : parkingRows){
                parkingRow.setParkingLevel(parkingLevel);
            }

            parkingLevels.add(parkingLevel);

        }

        ParkingLot lot = new ParkingLot(1L, parkingLevels);

        for(ParkingLevel parkingLevel : parkingLevels){
            parkingLevel.setParkingLot(lot);
        }

        return lot;

    }

    public void status(ParkingLot parkingLot){
        for(ParkingLevel level : parkingLot.getLevels()){
            System.out.println("----------Level : " + level.getId() + " ------------");
            for(ParkingRow row : level.getRows()){
                System.out.println("Row : " + row.getId());
                for(ParkingSpot spot : row.getSpots()){
                    System.out.println(spot.getSpotId() + " - " + spot.getParkingSpotType() + " - Available ? " + spot.isAvailable());
                }
            }
        }
    }

    public List<ParkingSpot> findNearestParkingSpots(ParkingLot parkingLot, VehicleSize size){
        for(ParkingLevel level : parkingLot.findLevelsByAvailability()){
            for(ParkingRow row : level.findRowsByAvailability()){
                return row.findSpotsByVehicleSize(size);
            }
        }
        return null;
    }

}
