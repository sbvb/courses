/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbVB;

import junit.framework.Assert;

/**
 *
 * @author sbvb
 */
class DataBaseDataType {

    String title;
    String author;
    String editor;
    int year;
    String token = ";";

    DataBaseDataType() {    
    }
    
    DataBaseDataType(String titlein, String authorin, String editorin, int yearin) {
        title = titlein;
        author = authorin;
        editor = editorin;
        year = yearin;
    }

    @Override
    public String toString() {
        return title + token + author + token + editor + token + year;
    }

    void setFromString(String in) {
        String[] parts = in.split(token);
        title = parts[0];
        author = parts[1];
        editor = parts[2];
        year = Integer.parseInt(parts[3]);
    }

    protected DataBaseDataType clone() {
        return new DataBaseDataType(title, author, editor, year);
    }

    public boolean equals(DataBaseDataType obj) {
        if (obj == null || this == null) {
            System.out.println("null");
            return false;
        }
        if (!obj.title.equals(title)) {
            System.out.println("title");
            return false;
        }
        if (!obj.author.equals(author)) {
            System.out.println("author");
            return false;
        }
        if (!obj.editor.equals(editor)) {
            System.out.println("editor");
            return false;
        }
        if (year != obj.year) {
            System.out.println("year");
            return false;
        }

        return true;
    }

    static void testMe() {
        System.out.println("=== DataBaseDataType.testMe()");

        DataBaseDataType d = new DataBaseDataType("t1", "a1", "e1", 1);
        DataBaseDataType d2 = d.clone();
        Assert.assertTrue(d.equals(d2));
        String dStr = d.toString();
        d2.setFromString(dStr);
        Assert.assertTrue(d.equals(d2));

    }

};
