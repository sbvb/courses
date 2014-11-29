/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sbvb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import junit.framework.Assert;
import org.junit.runners.model.Statement;

/**
 *
 * @author sbvb
 */

public class DBConnect {

    static final String driver = "com.mysql.jdbc.Driver";
    static final String driverStr = "mysql";
    static final String database = "//localhost/db_bib";
    static final String user = "sbvb";
    static final String pwd = "pwd";

    DataBaseDataType[] getAllElementsHardCoded() {

        DataBaseDataType ret[] = new DataBaseDataType[2];
        ret[0] = new DataBaseDataType("t1", "a1", "e1", 1);
        ret[1] = new DataBaseDataType("t2", "a2", "e2", 2);
        return ret;

//        try {
//            Class.forName(driver);
//
//            // for mysql
//            Connection con = DriverManager.getConnection(
//                    "jdbc:" + driverStr + ":" + database + "?"
//                    + "user=" + user + "&password=" + pwd);
//
//            /*
//             * Statement stmt = con.createStatement(); stmt.execute("insert intoInt
//             * tb_book (title,author,editor,year) values" + "('New Title','New
//             * author', 'New editor',2001)"); System.out.print("query
//             * executed");
//             */
//            java.sql.Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from tb_book");
//            int rowCount = 0;
//
//            // System.out.println("japanese = æ—¥æœ¬èªžã‚’å‹‰å¼·ã—ã¾ã™");
//            // for each line of ResultSet
////            while (rs.next()) {
//            for (int i = 0; i < element; i++) {
//                ret = new String[4];
//                ret[0] = rs.getString("title");
//                ret[1] = rs.getString("author");
//                ret[2] = rs.getString("editor");
//                ret[3] = "" + rs.getInt("year");
//                rs.next();
//            }
//
//            /*
//             String st = "delete from tb_book where bookid=3;";
//             System.out.println("DEBUG " + st);
//             Statement stmt2 = con.createStatement();
//             stmt2.execute(st);
//             */
//        } catch (SQLException e) {
//            System.out.println("SQLException");
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException");
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("Unhandled Exception");
//            e.printStackTrace();
//        }
    }

    static void testMe() {

        System.out.println("=== testDbConnect()");
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
}
