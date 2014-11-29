/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbVB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import static sbVB.Global.log;

/**
 *
 * @author sbvb
 */
public class DAO_Message {

//    /**
//     * delete message of given message_id
//     *
//     * @param message_id
//     * @return Global.OK if succeeded, Global.NOT_OK otherwise
//     */
//    static String deleteMessage(final int message_id) {
//        Database_Base dbb = new Database_Base() {
//            @Override
//            void evaluate() throws SQLException {
//                String sql = "delete from tb_message where message_id="
//                        + message_id
//                        + ";";
//                log.log(Level.INFO, "=== deleteMessage sql=" + sql);
//                int rs = stmt.executeUpdate(sql);
//                ret = Global.OK;
//            }
//        };
//        return dbb.returnString();
//    }
//
//    /**
//     * upddate message
//     *
//     * @param message_id
//     * @param newData - new message data
//     * @return Global.OK if succeeded, Global.NOT_OK otherwise
//     */
//    static String updateMessage(final int message_id, final String newData) {
//        Database_Base dbb = new Database_Base() {
//            @Override
//            void evaluate() throws SQLException {
//                if (newData.contains(Global.token)) {
//                    ret = Global.NOT_OK + "message must not contain '" + Global.token + "'";
//                } else {
//                    String sql = "UPDATE tb_message SET name='"
//                            + newData
//                            + "' WHERE message_id='"
//                            + message_id
//                            + "';";
//                    log.log(Level.INFO, "=== updateMessage sql=" + sql);
//                    int rs = stmt.executeUpdate(sql);
//                    ret = Global.OK;
//                }
//            }
//        };
//
//        return dbb.returnString();
//    }
//
//    /**
//     * reads all messages of a given author_id. if author_id==0, reads all
//     * messages select message_id,a.name, m.data,m.mydate from tb_message m join
//     * tb_author a on a.author_id=m.author_fk where author_id=3;
//     *
//     * @param author_id
//     * @return messages or Global.NOT_OK if some problem happens
//     */
//    static String[] readMessages(final int author_id) {
//        Database_Base dbb = new Database_Base() {
//            @Override
//            void evaluate() throws SQLException {
//                ResultSet rs = stmt.executeQuery("select count(*) from tb_message");
//                rs.next();
//                int nLines = rs.getInt(1);
//                log.log(Level.INFO, "=== readMessages lines in table=" + nLines);
//                retArray = new String[nLines];
//                if (author_id == 0) {
//                    rs = stmt.executeQuery("select message_id,a.name, m.data,m.mydate from tb_message m join tb_author a on a.author_id=m.author_fk;");
//                } else {
//                    rs = stmt.executeQuery("select message_id,a.name, m.data,m.mydate from tb_message m join tb_author a on a.author_id=m.author_fk where author_id="
//                            + author_id + ";");
//                }
//                rs.next();
//                int rowCount = 0;
//                // for each line of ResultSet
//                for (int i = 0; i < nLines; i++) {
//                    String data = rs.getInt("message_id") + Global.token
//                            + rs.getInt("name") + Global.token
//                            + rs.getString("data") + Global.token
//                            + rs.getDate("mydate");
//                    rs.next();
//                    retArray[i] = data;
//                    log.log(Level.INFO, "=== readMessages data=", data);
//                }
//            }
//        };
//        return dbb.returnStringArray();
//    }
//
//    /**
//     * reade the message for given message_id
//     *
//     * @param message_id
//     * @return
//     */
//    static String readMessage(final int message_id) {
//        Database_Base dbb = new Database_Base() {
//            @Override
//            void evaluate() throws SQLException {
//                String sql = "select message_id,a.name, m.data,m.mydate from tb_message m join tb_author a on a.author_id=m.author_fk where message_id="
//                        + message_id;
//                ResultSet rs = stmt.executeQuery(sql);
//                log.log(Level.INFO, "=== readMessage sql=", sql);
//                rs.next();
//                String data = rs.getString("message_id") + Global.token +
//                    rs.getString("name") + Global.token +
//                    rs.getString("data") + Global.token +
//                    rs.getString("mydate");
//                ret = data;
//                log.log(Level.INFO, "=== readAuthor data=", data);
//            }
//        };
//
//        return dbb.returnString();
//    }
//
//
//    /**
//     * create message for a given author_id
//     * INSERT INTO tb_message (data,author_fk,mydate) VALUES ('message from sbvb',3,now());
//     * @param message
//     * @param author_id
//     * @return primary key of new message
//     */
//    static String createMessage(final String message, final int author_id) {
//        Database_Base dbb = new Database_Base() {
//            @Override
//            void evaluate() throws SQLException {
//
//                if (message.contains(Global.token)) {
//                    ret = Global.NOT_OK + "message name must not contain '" + Global.token + "'";
//                } else {
//                    String sql = "INSERT INTO (data,author_fk,mydate) VALUES ('"
//                            + message + 
//                            "'," + author_id + 
//                            ",now());'";
//                    log.log(Level.INFO, "=== createMessage sql=" + sql);
//                    int rs = stmt.executeUpdate(sql);
//                    sql = "select LAST_INSERT_ID();";
//                    log.log(Level.INFO, "=== createMessage sql=" + sql);
//                    ResultSet rs2 = stmt.executeQuery(sql);
//                    rs2.next();
//                    int primaryKey = rs2.getInt(1);
//                    ret = "" + primaryKey;
//                }
//            }
//        };
//        return dbb.returnString();
//    }

}
