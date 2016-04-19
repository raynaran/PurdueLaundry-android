package xyz.jhughes.laundry;

import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import xyz.jhughes.laundry.LaundryParser.Location;
import xyz.jhughes.laundry.LaundryParser.Machine;
import xyz.jhughes.laundry.LaundryParser.MachineList;

public class ModelOperations {
    public static boolean machinesOffline(List<Machine> machines) {
        for (Machine m : machines) {
            if (!(m.getStatus().equals("Not online"))) {
                return false;
            }
        }
        return true;
    }

    public static List<Location> machineMapToLocationList(Map<String, MachineList> machineListMap) {
        List<Location> locations = new ArrayList<>();
        for(String key: machineListMap.keySet()){
            MachineList ml = machineListMap.get(key);
            Location location = new Location(key,ml);
            if(ml.isOffline()){
                locations.add(location);
            } else {
                locations.add(0,location);
            }
        }
        return locations;
    }

    public static Integer[] getAvailableCounts(List<Machine> machines){
        final Integer[] countArray = new Integer[4];
        for (int i = 0; i < 4; i++) {
            countArray[i] = 0;
        }
        for (Machine machine : machines) {
            if (machine.getType().equals("Dryer")) {
                //Increments Total Dryer Count For Specific Place
                countArray[0] = countArray[0] + 1;
                if (machine.getStatus().equals("Available")) {
                    //Increments Available Dryer Count For Specific Place
                    countArray[1] = countArray[1] + 1;
                }
            } else {
                //Increments Total Washer Count For Specific Place
                countArray[2] = countArray[2] + 1;
                if (machine.getStatus().equals("Available")) {
                    //Increments Available Washer Count For Specific Place
                    countArray[3] = countArray[3] + 1;
                }
            }
        }
        return countArray;
    }


}