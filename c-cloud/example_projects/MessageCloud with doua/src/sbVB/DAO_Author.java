/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbVB;

import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import static sbVB.Global.log;

/**
 *
 * @author sbvb
 */
public class DAO_Author {

    /**
     * create a new Author
     *
     * @param author - author name
     * @return - primary key of created author
     */
    static String createAuthor(final String author) {

        Database_Base dbb = new Database_Base() {
            @Override
            void evaluate() throws SQLException {

                if (author.contains(Global.token)) {
                    ret = Global.NOT_OK + "author name must not contain '" + Global.token + "'";
                } else {
//              INSERT INTO tb_author (name) VALUES ('author sbvb');
                    String sql = "INSERT INTO tb_author (name) VALUES ('"
                            + author
                            + "')";
                    log.log(Level.INFO, "=== createAuthor sql=" + sql);
                    int rs = stmt.executeUpdate(sql);
                    sql = "select LAST_INSERT_ID();";
                    log.log(Level.INFO, "=== createAuthor sql=" + sql);
                    ResultSet rs2 = stmt.executeQuery(sql);
                    rs2.next();
                    int primaryKey = rs2.getInt(1);
                    ret = "" + primaryKey;
                }
            }
        };
        return dbb.returnString();
    }
    // select author_id,name from tb_author;
    /**
     * reads the existing authors
     *
     * @return an array where each line is the author_id;author_name
     */
    static String[] readAuthor() {
        Database_Base dbb = new Database_Base() {
            @Override
            void evaluate() throws SQLException {
//              INSERT INTO tb_author (name) VALUES ('author sbvb');
                ResultSet rs = stmt.executeQuery("select count(*) from tb_author");
                rs.next();
                int nLines = rs.getInt(1);
                log.log(Level.INFO, "=== readAuthor lines in table=" + nLines);
                retArray = new String[nLines];
                rs = stmt.executeQuery("select author_id,name from tb_author");
                rs.next();
                int rowCount = 0;
                // for each line of ResultSet
                for (int i = 0; i < nLines; i++) {
                    String data = rs.getString("author_id") + Global.token;
                    data += rs.getString("name");
                    rs.next();
                    retArray[i] = data;
                    log.log(Level.INFO, "=== readAuthor data=", data);
                }
            }
        };
        return dbb.returnStringArray();
    }

    // UPDATE tb_author SET name='new name' WHERE author_id=3;
    /**
     *
     * @param author_id
     * @param newName
     * @return
     */
    static String updateAuthor(final int author_id, final String newName) {
        Database_Base dbb = new Database_Base() {
            @Override
            void evaluate() throws SQLException {
                if (newName.contains(Global.token)) {
                    ret = Global.NOT_OK + "author name must not contain '" + Global.token + "'";
                } else {
                    String sql = "UPDATE tb_author SET name='"
                            + newName
                            + "' WHERE author_id="
                            + +author_id
                            + ";";
                    log.log(Level.INFO, "=== updateAuthor sql=" + sql);
                    int rs = stmt.executeUpdate(sql);
                    ret = Global.OK;
                }
            }
        };

        return dbb.returnString();
    }

    // delete from tb_author where author_id=4;
    static String deleteAuthor(final int author_id) {
        Database_Base dbb = new Database_Base() {
            @Override
            void evaluate() throws SQLException {
                String sql = "delete from tb_author where author_id="
                        + author_id
                        + ";";
                log.log(Level.INFO, "=== deleteAuthor sql=" + sql);
                int rs = stmt.executeUpdate(sql);
                ret = Global.OK;
            }
        };
        return dbb.returnString();
    }

    /**
     * tests this class
     *
     * @param stringList
     * @return strings confirming test ok of this class
     */
    static void testMe(LinkedList<String> stringList) {
        try {
            stringList.add("=== DAO_Author.testMe");

            // create author
            String authorName = "the author name";
            String xmlData = Global.getUrlData("http://localhost:8080/axis2/services/MessageCloudMain/createAuthor?author="
                    + Global.encodeURI(authorName));
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            stringList.add("DEBUG 1");
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            stringList.add("DEBUG 2");
            org.w3c.dom.Document doc = dBuilder.parse(new InputSource(new StringReader(xmlData)));
//            stringList.add("DEBUG 3");
            NodeList nodeList = doc.getElementsByTagName("ns:return");
//            stringList.add("DEBUG 4");
            String itemStr = nodeList.item(0).getChildNodes().item(0).getNodeValue();
//            stringList.add("DEBUG: item Added=" + itemStr);
            int primaryKey = Integer.parseInt(itemStr);
//            stringList.add("DEBUG 5");
            int primaryKeyRecovered = 0;
//            stringList.add("DEBUG 6");
            // read author to see if it exists
            xmlData = Global.getUrlData
        ("http://localhost:8080/axis2/services/MessageCloudMain/readAuthor");
//            stringList.add("DEBUG 7");
            doc = dBuilder.parse(new InputSource(new StringReader(xmlData)));
//            stringList.add("DEBUG 8");
            nodeList = doc.getElementsByTagName("ns:return");
//            stringList.add("DEBUG 9");
            boolean testOK = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                itemStr = nodeList.item(i).getChildNodes().item(0).getNodeValue();
//                stringList.add("DEBUG 100 - " + itemStr);
                StringTokenizer st =new StringTokenizer(itemStr);
//                stringList.add("DEBUG 101");
                // st.hasMoreElements();
                String primaryKeyRecoveredStr = st.nextElement().toString();
//                stringList.add("DEBUG 102 - " + primaryKeyRecoveredStr);
//                String primaryKeyRecoveredStr = itemStr.split(Global.token,2);
//                stringList.add("DEBUG 101 - " + parts[0]);
//                stringList.add("DEBUG 101 - " + parts[1]);
//                stringList.add("DEBUG 101 - " + parts[2]);
                primaryKeyRecovered = Integer.parseInt(primaryKeyRecoveredStr);
//                stringList.add("DEBUG primaryKeyRecovered = " + primaryKeyRecovered);
                if (primaryKey == primaryKeyRecovered) {
                    testOK = true;
                    break;
                }
            }
//            stringList.add("DEBUG 10");
            if (testOK) {
                stringList.add("Test ok : primaryKey == primaryKeyRecovered");
            } else {
                stringList.add("TEST NOT OK : primaryKey " + primaryKey + 
                        "== primaryKeyRecovered" + primaryKeyRecovered);
            }

//            stringList.add("xmldata = '" + xmlData + "'");
        } catch (Exception ex) {
            stringList.add("=== Exception");
        }

    }

}

//
////    /**
////     * create a new Author
////     *
////     * @param author - author name
////     * @return - primary key of created author
////     */
////    static String createAuthor2(String author) {
////        String ret = Global.NOT_OK;
////
////        try {
//////            Class.forName(driver);
//////
//////            // for mysql
//////            Connection con = DriverManager.getConnection(
//////                    "jdbc:" + driverStr + ":" + database + "?"
//////                    + "user=" + user + "&password=" + pwd);
////
////            Connection con = Global.connectDB();
////
////            java.sql.Statement stmt = con.createStatement();
//////              INSERT INTO tb_author (name) VALUES ('author sbvb');
////
////            String sql = "INSERT INTO tb_author (name) VALUES ('"
////                    + author
////                    + "')";
////
////            log.log(Level.INFO, "=== sql=" + sql);
////
////            int rs = stmt.executeUpdate(sql);
////
////            sql = "select LAST_INSERT_ID();";
////            log.log(Level.INFO, "=== sql=" + sql);
////
////            ResultSet rs2 = stmt.executeQuery(sql);
////            rs2.next();
////            int primaryKey = rs2.getInt(1);
////
////            ret = "" + primaryKey;
////
////        } catch (SQLException e) {
////            ret = Global.NOT_OK + "SQLException";
//////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            ret = Global.NOT_OK + "ClassNotFoundException";
////        } catch (Exception e) {
////            ret = Global.NOT_OK + "Unhandled Exception";
////        }
////
////        return ret;
////
////    }
////    // select author_id,name from tb_author;
////    /**
////     * reads the existing authors
////     *
////     * @return an array where each line is the author_id;author_name
////     */
////    static String[] readAuthor() {
////        String[] ret = {Global.NOT_OK};
////
////        try {
//////            Class.forName(driver);
//////
//////            // for mysql
//////            Connection con = DriverManager.getConnection(
//////                    "jdbc:" + driverStr + ":" + database + "?"
//////                    + "user=" + user + "&password=" + pwd);
////
////            Connection con = Global.connectDB();
////
////            java.sql.Statement stmt = con.createStatement();
//////              INSERT INTO tb_author (name) VALUES ('author sbvb');
////
////            ResultSet rs = stmt.executeQuery("select count(*) from tb_author");
////            rs.next();
////            int nLines = rs.getInt(1);
////
////            log.log(Level.INFO, "=== lines in table=" + nLines);
////
////            ret = new String[nLines];
////
////            rs = stmt.executeQuery("select author_id,name from tb_author");
////            rs.next();
////            int rowCount = 0;
////
////            // for each line of ResultSet
//////            while (rs.next()) {
////            for (int i = 0; i < nLines; i++) {
////                String token = ";";
////                String data = rs.getString("author_id") + token;
////                data += rs.getString("name");
//////                if (i < nLines-1)
//////                    data = data + token;
////                rs.next();
////                ret[i] = data;
////                log.log(Level.INFO, "=== data=" + data);
////            }
////
////        } catch (SQLException e) {
////            ret[0] = Global.NOT_OK + "SQLException";
//////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            ret[0] = Global.NOT_OK + "ClassNotFoundException";
////        } catch (Exception e) {
////            ret[0] = Global.NOT_OK + "Unhandled Exception";
////        }
////
////        return ret;
////    }
