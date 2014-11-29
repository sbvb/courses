package br.com.sbvb;

import junit.framework.Assert;

public class MyClassForCloud {

    public String getMessage() {
        return "I really like java and axis2";
    }

    public String plus_a(String in) {
        return in + "a";
    }

//    DataBaseDataType[] getAllElementsHardCoded() {
//        DBConnect db = new DBConnect();
//        return db.getAllElementsHardCoded();
//    }
//    
//    static void testMe() {
//        MyClassForCloud m = new MyClassForCloud();
//        Assert.assertTrue(m.getMessage().equals("I really like java and axis2"));
//        Assert.assertTrue(m.plus_a("banan").equals("banana"));
//    }

}
