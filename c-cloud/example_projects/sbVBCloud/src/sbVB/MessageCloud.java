/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.



 mysql -u root -p
 <rootPwd>

 CREATE DATABASE db_messageCloud; 
 DROP USER 'sbvb'@'localhost';
 CREATE USER 'sbvb'@'localhost' IDENTIFIED BY 'sbvbpwd';
    GRANT ALL ON db_messageCloud.* TO 'sbvb'@'localhost';
 flush privileges;
 exit


 ///////////////

 mysql -u sbvb -p
 <sbvbpwd>
 USE db_messageCloud;

 drop table tb_message;
 drop table tb_author;

 CREATE TABLE `tb_author` (
 `author_id` INT(11) NOT NULL AUTO_INCREMENT,
 `name` TEXT NULL DEFAULT NULL,
 PRIMARY KEY (`author_id`)
 )
 COLLATE='utf8_general_ci'
 ENGINE=InnoDB;


 CREATE TABLE `tb_message` (
 `message_id` INT(11) NOT NULL AUTO_INCREMENT,
 `author_id` INT(11) NULL DEFAULT NULL,
 `data` TEXT NULL,
 `mydate` DATETIME NULL DEFAULT NULL,
 PRIMARY KEY (`message_id`),
 INDEX `FK__tb_author` (`author_id`),
 CONSTRAINT `FK__tb_author` FOREIGN KEY (`author_id`) REFERENCES `tb_author` (`author_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
 )
 COLLATE='utf8_general_ci'
 ENGINE=InnoDB;


 show tables;

 delete from tb_author;

 INSERT INTO tb_author (name) VALUES ('author sbvb');
 INSERT INTO tb_author (name) VALUES ('author someone');

 drop table tb_message;


 INSERT INTO tb_message (data,date) VALUES ('message from sbvb',now()) where 
 author_id in (select author_id from tb_author where name = 'author sbvb');

 INSERT INTO tb_message (data,author_id,mydate) VALUES ('message from sbvb',3,now());

 select author_id from tb_author where name = 'author sbvb' as author_int;

 INSERT INTO tb_message (data,author_id,mydate) 
 select 'my new message',author_id,now() from tb_author where name = 'author sbvb';



 where 
 author_id_int in (select author_id from tb_author where name = 'author sbvb');




 select author_id from tb_author where name = 'author sbvb';



 */
package sbVB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CRUD Create Read Update Delete
 *
 * @author sbvb
 */
public class MessageCloud {

    static Logger log = Logger.getLogger("MessageCloud");

    static final String driver = "com.mysql.jdbc.Driver";
    static final String driverStr = "mysql";
    static final String database = "//localhost/db_messageCloud";
    static final String user = "sbvb";
    static final String pwd = "sbvbpwd";

    // http://localhost:8080/axis2/services/MessageCloud/createAuthor?author=my+new+author+2
    public String createAuthor(String author) {
        String ret = "null";

        try {
            Class.forName(driver);

            // for mysql
            Connection con = DriverManager.getConnection(
                    "jdbc:" + driverStr + ":" + database + "?"
                    + "user=" + user + "&password=" + pwd);

            java.sql.Statement stmt = con.createStatement();
//              INSERT INTO tb_author (name) VALUES ('author sbvb');

            int rs = stmt.executeUpdate("INSERT INTO tb_author (name) VALUES ('"
                    + author
                    + "')");

            ret = "ok";

        } catch (SQLException e) {
            ret = "SQLException";
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ret = "ClassNotFoundException";
        } catch (Exception e) {
            ret = "Unhandled Exception";
        }

        return ret;

    }

    // http://localhost:8080/axis2/services/MessageCloud/readAuthor
    // select author_id,name from tb_author;
    public String[] readAuthor() {
        String[] ret = {"null"};

        try {
            Class.forName(driver);

            // for mysql
            Connection con = DriverManager.getConnection(
                    "jdbc:" + driverStr + ":" + database + "?"
                    + "user=" + user + "&password=" + pwd);

            java.sql.Statement stmt = con.createStatement();
//              INSERT INTO tb_author (name) VALUES ('author sbvb');

            ResultSet rs = stmt.executeQuery("select count(*) from tb_author");
            rs.next();
            int nLines = rs.getInt(1);

            log.log(Level.INFO, "=== lines in table=" + nLines);

            ret = new String[nLines];

            rs = stmt.executeQuery("select author_id,name from tb_author");
            rs.next();
            int rowCount = 0;

            // for each line of ResultSet
//            while (rs.next()) {
            for (int i = 0; i < nLines; i++) {
                String token = ";";
                String data = rs.getString("author_id")  + token;
                data += rs.getString("name");
//                if (i < nLines-1)
//                    data = data + token;
                rs.next();
                ret[i] = data;
                log.log(Level.INFO, "=== data=" + data);
            }

        } catch (SQLException e) {
            ret[0] = "SQLException";
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ret[0] = "ClassNotFoundException";
        } catch (Exception e) {
            ret[0] = "Unhandled Exception";
        }

        return ret;

    }

}
