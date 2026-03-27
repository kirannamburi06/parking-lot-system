package Entity;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final Long id;
    private final List<ParkingLevel> levels;

    public ParkingLot(Long id, List<ParkingLevel> levels){
        this.id = id;
        this.levels = levels;
    }

    public Long getId(){
        return this.id;
    }

    public List<ParkingLevel> getLevels(){
        return this.levels;
    }

    public List<ParkingLevel> findLevelsByAvailability(){
        List<ParkingLevel> availableLevels = new ArrayList<>();

        for(ParkingLevel level : levels){
            if(level.isAvailable()){
                availableLevels.add(level);
            }
        }

        return availableLevels;
    }

}
