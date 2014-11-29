/*
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

 drop schema db_messageCloud;
 create schema db_messageCloud;
 use db_messageCloud;


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
 `author_fk` INT(11) NULL DEFAULT NULL,
 `data` TEXT NULL,
 `mydate` DATETIME NULL DEFAULT NULL,
 PRIMARY KEY (`message_id`),
 INDEX `FK__tb_author` (`author_fk`),
 CONSTRAINT `FK__tb_author` FOREIGN KEY (`author_fk`) REFERENCES `tb_author` (`author_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
 )
 COLLATE='utf8_general_ci'
 ENGINE=InnoDB;


 show tables;

 delete from tb_author;

 INSERT INTO tb_author (name) VALUES ('author sbvb');
 INSERT INTO tb_author (name) VALUES ('author someone');


 INSERT INTO tb_message (data,author_fk,mydate) 
 select 'my new message',author_id,now() 
 from tb_author where name = 'author sbvb';


 INSERT INTO tb_message (data,author_fk,mydate) 
 select 'I like beer',author_id,now() 
 from tb_author where name = 'author someone';


 select author_id from tb_author where name = 'author sbvb';

 cp /home/sbvb/Dropbox/Public/courses/c-cloud/example_projects/MessageCloud/build/axis2/WEB-INF/services/MessageCloud.aar /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/webapps/axis2/WEB-INF/services

 /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/shutdown.sh
 /home/sbvb/Dropbox/app/apache-tomcat-7.0.52/bin/startup.sh

 */
package sbVB;

import sbVB.DOUA.User;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Assert;

/**
 *
 * @author sbvb
 */
public class MessageCloudMain {

    // http://localhost:8080/axis2/services/MessageCloudMain/loadDouaConfig
    public static String loadDouaConfig() {
        return DouaController.loadDouaConfig();
    }

    /**
     * http://localhost:8080/axis2/services/MessageCloudMain/getAbout
     *
     * @return about string
     */
    public String getAbout() {
        return "MessageCloud, by sbVB 10:45";
    }

    /**
     * http://localhost:8080/axis2/services/MessageCloudMain/lazyLogin?userLogin=Camila&pwd=camila_pwd
     *
     * @param userLogin
     * @param pwd
     * @return sessionhash
     */
    public String lazyLogin(String userLogin, String pwd) {
        return DouaController.lazyLogin(userLogin, pwd);
    }

    // DOUA controlled Resource
    // http://localhost:8080/axis2/services/MessageCloudMain/managementReportA?sessionHash=39a4f8b71a1c1927ca51eca7c762a70f&multiplyTable=8
    public String managementReportA(String sessionHash, int multiplyTable) {
        return DouaController.managementReportA(sessionHash, multiplyTable);
    }

    
    
    /**
     * http://localhost:8080/axis2/services/MessageCloudMain/test
     *
     * @return one string per test
     */
    public static String[] test() {
        LinkedList<String> stringList = new LinkedList<String>();

        stringList.add("====== MessageCloud.test");

        DAO_Author.testMe(stringList);

        stringList.add("str 1");
        stringList.add("str 2");
//        stringList.add("str 3");
//        stringList.add("str 4");
//        stringList.add("str 5");

        String ret[] = new String[stringList.size()];
        stringList.toArray(ret);

        return ret;
    }

    // CRUD = create, read, update and delete
    ///////////////////////////////////////////////////////////////////////////////////////
    // CRUD author begin
    // http://localhost:8080/axis2/services/MessageCloud/createAuthor?author=the+author+name
    public static String createAuthor(String author) {
        return DAO_Author.createAuthor(author);
    }

    // http://localhost:8080/axis2/services/MessageCloud/readAuthor
    public static String[] readAuthor() {
        return DAO_Author.readAuthor();
    }

    // http://localhost:8080/axis2/services/MessageCloud/updateAuthor?author_id=3&newName=the+new+name
    public static String updateAuthor(int author_id, String newName) {
        return DAO_Author.updateAuthor(author_id, newName);
    }

    // http://localhost:8080/axis2/services/MessageCloud/deleteAuthor?author_id=3
    public static String deleteAuthor(int author_id) {
        return DAO_Author.deleteAuthor(author_id);
    }
    // CRUD author end
    ///////////////////////////////////////////////////////////////////////////////////////

//    ///////////////////////////////////////////////////////////////////////////////////////
//    // CRUD message begin
//    // http://localhost:8080/axis2/services/MessageCloud/createAuthor?author=the+author+name
//    public static String createMessage(String message, int author_id) {
//        return DAO_Message.createMessage(message,author_id);
//    }
//
//    // http://localhost:8080/axis2/services/MessageCloud/readAuthor
//    public static String readMessage(int message_id) {
//        return DAO_Message.readMessage(message_id);
//    }
//
//    public static String[] readMessages(int author_id) {
//        return DAO_Message.readMessages(author_id);
//    }
//
//    // http://localhost:8080/axis2/services/MessageCloud/updateAuthor?author_id=3&newName=the+new+name
//    public static String updateMessage(int message_id, String newData) {
//        return DAO_Message.updateMessage(message_id, newData);
//    }
//
//    // http://localhost:8080/axis2/services/MessageCloud/deleteAuthor?author_id=3
//    public static String deleteMessage(int message_id) {
//        return DAO_Message.deleteMessage(message_id);
//    }
//    // CRUD message begin
//    ///////////////////////////////////////////////////////////////////////////////////////
}
