/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sbVB;

// cp /home/sbvb/Dropbox/Public/courses/c-cloud/example_projects/GPS_Agency/build/axis2/WEB-INF/services/GPS_Agency.aar /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/webapps/axis2/WEB-INF/services
// /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/shutdown.sh
// /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/startup.sh

// http://localhost:8080/axis2/services/GPSCloud/getMessage
    

/**
 *
 * @author sbvb
 */
public class GPSCloud {
    
    public String getMessage() {
        return "GPS Agency";
    }

}
