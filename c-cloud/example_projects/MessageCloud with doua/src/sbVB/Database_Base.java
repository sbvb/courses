/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbVB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sbVB.Global.log;

/**
 *
 * @author sbvb
 */
public abstract class Database_Base {

    String ret;
    String retArray[];
    Connection con;
    java.sql.Statement stmt;

    abstract void evaluate() throws SQLException;

    String returnString() {

        ret = Global.NOT_OK;

        try {
//            Class.forName(driver);
//
//            // for mysql
//            Connection con = DriverManager.getConnection(
//                    "jdbc:" + driverStr + ":" + database + "?"
//                    + "user=" + user + "&password=" + pwd);

            con = Global.connectDB();
            stmt = con.createStatement();

            evaluate();

        } catch (SQLException e) {
            ret = Global.NOT_OK + "SQLException";
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ret = Global.NOT_OK + "ClassNotFoundException";
        } catch (Exception e) {
            ret = Global.NOT_OK + "Unhandled Exception";
        }
        return ret;
    }

    String[] returnStringArray() {

        retArray = new String[1];
        retArray[0] = Global.NOT_OK;

        try {
//            Class.forName(driver);
//
//            // for mysql
//            Connection con = DriverManager.getConnection(
//                    "jdbc:" + driverStr + ":" + database + "?"
//                    + "user=" + user + "&password=" + pwd);

            con = Global.connectDB();
            stmt = con.createStatement();

            evaluate();

        } catch (SQLException e) {
            retArray[0] = Global.NOT_OK + "SQLException";
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            retArray[0] = Global.NOT_OK + "ClassNotFoundException";
        } catch (Exception e) {
            retArray[0] = Global.NOT_OK + "Unhandled Exception";
        }
        return retArray;
    }

}
