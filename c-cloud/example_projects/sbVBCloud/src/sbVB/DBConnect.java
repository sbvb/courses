/*

mysql –u root –p <rootpwd>

CREATE DATABASE db_hello;
show databases;


DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

CREATE USER 'sbvb'@'localhost‘ IDENTIFIED BY 'pwd';
GRANT ALL PRIVILEGES ON *.* TO 'sbvb'@'localhost' WITH GRANT OPTION;

CREATE TABLE tb_varvalue(
var VARCHAR(30) NOT NULL,
PRIMARY KEY(var),
value VARCHAR(300)
);

INSERT INTO tb_varvalue (var, value)
VALUES('myvar', 'myvalue');

select * from tb_varvalue;

/////////////////////////////////////////////////
// tb_book, for mysql
/////////////////////////////////////////////////

drop database db_bib;
create database db_bib;
connect db_bib;


CREATE TABLE tb_book
(
title text,
author text,
editor text,
year integer,
bookid serial NOT NULL,
PRIMARY KEY (bookid)
);

insert into tb_book (title,author,editor,year)
values ('Microelectronics Circuits','Sedra / Smith','HRW Saunders',1991);

insert into tb_book (title,author,editor,year)
values ('Practical Unix Security','Simson Garfinkel','O''Reilly',1994);

mysql> select * from tb_book;
+---------------------------+------------------+--------------+------+--------+
| title                     | author           | editor       | year | bookid |
+---------------------------+------------------+--------------+------+--------+
| Microelectronics Circuits | Sedra / Smith    | HRW Saunders | 1991 |      1 |
| Practical Unix Security   | Simson Garfinkel | O'Reilly     | 1994 |      2 |
+---------------------------+------------------+--------------+------+--------+


*/
package sbVB;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author sbvb
 */
@SuppressWarnings("all")
public class DBConnect {

    static Logger log = Logger.getLogger("DBConnect");

    static final String driver = "com.mysql.jdbc.Driver";
    static final String driverStr = "mysql";
    static final String database = "//localhost/db_bib";
    static final String user = "sbvb";
    static final String pwd = "pwd";

    static final String myURL = "http://localhost:8080/axis2/services/ClassCloud";

//    public String[] getAllElementsAsSgtring() {
//        DBConnect db = new DBConnect();
//        return db.getAllElements();
//    }
    String[] getAllElements() {
//        System.out.println("getAllElements()");
        log.log(Level.INFO, "=== getAllElements()");

        String ret[] = null;
        // hard coded version
//        DataBaseDataType ret[] = new DataBaseDataType[2];
//        ret[0] = new DataBaseDataType("t1", "a1", "e1", 1);
//        ret[1] = new DataBaseDataType("t2", "a2", "e2", 2);
//        return ret;
        try {
            Class.forName(driver);

            // for mysql
            Connection con = DriverManager.getConnection(
                    "jdbc:" + driverStr + ":" + database + "?"
                    + "user=" + user + "&password=" + pwd);

            /*
             * Statement stmt = con.createStatement(); stmt.execute("insert intoInt
             * tb_book (title,author,editor,year) values" + "('New Title','New
             * author', 'New editor',2001)"); System.out.print("query
             * executed");
             */
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from tb_book");
            rs.next();
            int nLines = rs.getInt(1);
            log.log(Level.INFO, "=== lines in table=" + nLines);

            ret = new String[nLines];

            rs = stmt.executeQuery("select * from tb_book");
            rs.next();
            int rowCount = 0;

            // for each line of ResultSet
//            while (rs.next()) {
            for (int i = 0; i < nLines; i++) {
                String token = ";";
                String data = rs.getString("title") + token;
                data += rs.getString("author") + token;
                data += rs.getString("editor") + token;
                data += "" + rs.getInt("year");
                rs.next();
                ret[i] = data;
                log.log(Level.INFO, "=== data=" + data);
            }

            /*
             String st = "delete from tb_book where bookid=3;";
             System.out.println("DEBUG " + st);
             Statement stmt2 = con.createStatement();
             stmt2.execute(st);
             */
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unhandled Exception");
            e.printStackTrace();
        }

        return ret;
    }

    static void testMe() {

        System.out.println("=== DbConnect.testMe()");
//        String driver = "com.mysql.jdbc.Driver";
//        String driverStr = "mysql";
//        String database = "//localhost/db_bib";
//        String user = "sbvb";
//        String pwd = "pwd";

        try {
            Class.forName(driver);
            // Class.forName("org.postgresql.Driver");
            // org.postgresql.Driver d = new org.postgresql.Driver();

            // for mysql
            Connection con = DriverManager.getConnection(
                    // jdbc:<driverStr>:<dataBase>
                    // jdbc:postgresql:[<//host>[:<5432>/]]<database>
                    "jdbc:" + driverStr + ":" + database + "?"
                    + "user=" + user + "&password=" + pwd);

//            Connection con = DriverManager.getConnection(
//                    // jdbc:<driverStr>:<dataBase>
//                    // jdbc:postgresql:[<//host>[:<5432>/]]<database>
//                    "jdbc:" + driverStr + ":" + database,
//                    user,
//                    pwd);
//            Connection con = DriverManager.getConnection(
//                    // jdbc:<dataBase>:<base>
//                    "jdbc:postgresql:db_bib", // jdbc:postgresql:[<//host>[:<5432>/]]<database>
//                    "sbvb",
//                    "pwd");
            // System.out.println("never reaching code !!!!");
            String expectedAnswer[] = {
                "title=Microelectronics Circuits;author=Sedra / Smith;editor=HRW Saunders;year=1991",
                "title=Practical Unix Security;author=Simson Garfinkel;editor=O'Reilly;year=1994",};

            /*
             * Statement stmt = con.createStatement(); stmt.execute("insert into
             * tb_book (title,author,editor,year) values" + "('New Title','New
             * author', 'New editor',2001)"); System.out.print("query
             * executed");
             */
            java.sql.Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tb_book");
            int rowCount = 0;

            // System.out.println("japanese = æ—¥æœ¬èªžã‚’å‹‰å¼·ã—ã¾ã™");
            // for each line of ResultSet
            while (rs.next()) {
                String dbStr = "title=" + rs.getString("title") + ";"
                        + "author=" + rs.getString("author") + ";"
                        + "editor=" + rs.getString("editor") + ";"
                        + "year=" + rs.getInt("year");
                Assert.assertTrue(expectedAnswer[rowCount++].equals(dbStr));
//                System.out.print("Row[" + rowCount++ + "] = ");
//                System.out.print("title=" + rs.getString("title") + ";");
//                System.out.print("author=" + rs.getString("author") + ";");
//                System.out.print("editor=" + rs.getString("editor") + ";");
//                System.out.println("year=" + rs.getInt("year"));
            }

            /*
             String st = "delete from tb_book where bookid=3;";
             System.out.println("DEBUG " + st);
             Statement stmt2 = con.createStatement();
             stmt2.execute(st);
             */
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unhandled Exception");
            e.printStackTrace();
        }
    }

    static void testMeWeb() {
        System.out.println("=== DbConnect.testMeWeb()");
        Document doc;
        String data;
        DataBaseDataType d = new DataBaseDataType();
        DataBaseDataType d_expected[] = new DataBaseDataType[2];
//        d_expected[0] = new DataBaseDataType("t1", "a1", "e1", 1);
//        d_expected[1] = new DataBaseDataType("t2", "a2", "e2", 2);
        d_expected[0] = new DataBaseDataType("Microelectronics Circuits", "Sedra / Smith", "HRW Saunders", 1991);
        d_expected[1] = new DataBaseDataType("Practical Unix Security", "Simson Garfinkel", "O'Reilly", 1994);
        try {

            String urlStr = myURL + "/getAllElements";
            DOMParser parser = new DOMParser();

            parser.parse(new InputSource(new URL(urlStr).openStream()));
            doc = parser.getDocument();

//            System.out.println("DEBUG: Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("ns:return");
//            System.out.println("DEBUG: length:" + nodeList.getLength());
            for (int i = 0; i < nodeList.getLength(); i++) {
                data = "" + nodeList.item(i).getFirstChild().getNodeValue();
                d.setFromString(data);
//                System.out.println("DEBUG: d=" + d.toString());
                Assert.assertTrue(d.equals(d_expected[i]));
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
