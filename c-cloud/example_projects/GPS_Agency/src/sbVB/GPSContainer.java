/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbVB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sbvb
 */
public class GPSContainer {

    Map<Integer, GPSData> map;

    public GPSContainer() {
        this.map = new HashMap<Integer, GPSData>();
    }

    public GPSData getGPSData(int i) {
        return map.get(i);
    }

    public void setGPSData(int id, GPSData data) {
        map.put(id,data);
    }

    static void testMe() {
        System.out.println("=== GPSContainer.testMe()");

        GPSContainer gc = new GPSContainer();

        // put values into map
        gc.setGPSData(1, new GPSData(1.1, 2.1));
        gc.setGPSData(2, new GPSData(1.2, 2.2));
        gc.setGPSData(3, new GPSData(1.3, 2.3));

        for (Map.Entry<Integer, GPSData> entry : gc.map.entrySet()) {
            Integer key = entry.getKey();
            GPSData values = entry.getValue();
            System.out.println("Key = " + key);
            System.out.println("Values = " + values);
        }

        GPSData gd = gc.getGPSData(1);
        System.out.println("gd="+gd);
        
        // overwrite previous value
        gc.setGPSData(1, new GPSData(10.1, 20.1));
        gd = gc.getGPSData(1); 
        System.out.println("gd="+gd);

    }

}
