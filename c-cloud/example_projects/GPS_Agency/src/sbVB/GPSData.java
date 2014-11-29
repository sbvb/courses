/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbVB;

/**
 *
 * @author sbvb
 */
public class GPSData {
    double latitude, longitude;
    GPSData(double lat_in, double longitude_in) {
        latitude = lat_in;
        longitude = longitude_in;
    }
    
    @Override
     public String toString() {
        return "" + latitude + ";" + longitude;
    }
}
