/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sbvb;

/**
 *
 * @author sbvb
 */
class DataBaseDataType {

    String title;
    String author;
    String editor;
    int year;

    DataBaseDataType(String titlein, String authorin, String editorin, int yearin) {
        title = titlein;
        author = authorin;
        editor = editorin;
        year = yearin;
    }
};
