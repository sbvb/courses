/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// sudo cp /var/www/sbVBCloud.aar /home/ubuntu/tomcat/apache-tomcat-7.0.12/webapps/axis2/WEB-INF/services

// rm /home/sbvb/Dropbox/Public/courses/c-cloud/example_projects/sbVBCloud/build/axis2/WEB-INF/services/sbVBCloud.aar 
// cp /home/sbvb/Dropbox/Public/courses/c-cloud/example_projects/sbVBCloud/build/axis2/WEB-INF/services/sbVBCloud.aar /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/webapps/axis2/WEB-INF/services
// /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/shutdown.sh
// /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/startup.sh

// http://localhost:8080/axis2/services/ClassCloud/getMessage

package sbVB;


import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import static sbVB.DBConnect.myURL;

/**
 *
 * @author sbvb
 */
@SuppressWarnings("sunapi")
public class ClassCloud {

    public String getMessage() {
        return "I really like java and axis2";
    }

    public String getMessage2() {
        return "another message";
    }

    public String plus_a(String in) {
        return in + "a";
    }

//    // http://localhost:8080/axis2/services/ClassCloud/getFirstElementsHardCoded
//    public String getFirstElementsHardCoded() {
//        DBConnect db = new DBConnect();
//        return db.getAllElementsHardCoded()[0].toString();
//    }

    // http://localhost:8080/axis2/services/ClassCloud/getAllElementsHardCoded
    public String[] getAllElements() {
        DBConnect db = new DBConnect();
        return db.getAllElements();
    }
    
    static void testMe() {
        System.out.println("=== ClassCloud.testMe()");
        ClassCloud m = new ClassCloud();
        Assert.assertTrue(m.getMessage().equals("I really like java and axis2"));
        Assert.assertTrue(m.plus_a("banan").equals("banana"));
    }

    static void testMeWeb()  {
        System.out.println("=== ClassCloud.testMeWeb()");
        Document doc;
        String data;
        try {

            String urlStr = myURL + "/getMessage";
            DOMParser parser = new DOMParser();

            parser.parse(new InputSource(new URL(urlStr).openStream()));
            doc = parser.getDocument();

//            System.out.println("DEBUG: Root element :" + doc.getDocumentElement().getNodeName());
//            NodeList nodeList = doc.getElementsByTagName("ns:return");
//            for (int i = 0; i < nodeList.getLength(); i++) {
//                String data = nodeList.item(i).getFirstChild().getNodeValue();
//                System.out.println("DEBUG: data=" + data);
//            }
            data = doc.getElementsByTagName("ns:return").item(0).getFirstChild().getNodeValue();
            Assert.assertTrue("I really like java and axis2".equals(data));

            urlStr = myURL + "/plus_a?in=banan";
            parser.parse(new InputSource(new URL(urlStr).openStream()));
            doc = parser.getDocument();
            data = doc.getElementsByTagName("ns:return").item(0).getFirstChild().getNodeValue();
            Assert.assertTrue("banana".equals(data));

        } catch (MalformedURLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
