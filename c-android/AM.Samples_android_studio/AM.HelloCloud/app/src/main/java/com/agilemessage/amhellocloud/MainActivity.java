package com.agilemessage.amhellocloud;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//import org.w3c.dom.Element;

// link of netbeans plugin for axis2
// https://www.dropbox.com/sh/op64cwvn41kjiip/AABIkMaqnW8H8vR7VFIaZyR-a/courses/c-cloud/axis2-plugin/org-netbeans-modules-websvc-axis2.nbm?dl=0

public class MainActivity extends Activity {

    ProgressDialog progress;
    Context context = this;

    Document mainDoc;
    String toastStr;
    // fixed ip localhost type "ifconfig"
    String fixedIP = "192.168.182.185";
    String port = "8080";


    String urlStr =

            // uncomment one of the below

            // sbvb MessageCloud
            "http://sbvb.com.br:8081/axis2/services/MessageCloud/readAuthor";

    // localhost of phone is not localhost of computer !!!!
    // use ifconfig in linux to get actual ip
    // http://172.16.24.146:8080
//	"http://172.16.24.146:8080/axis2/services/ClassCloud/plus_a?in=";

    // fixed ip localhost type "ifconfig"
//	"http://" + fixedIP + ":" + port +
//            "/axis2/services/WS_Hello/plus_a?in=";
//    "/axis2/services/CloudHelloMain";
    // "http://172.16.24.102:8080/axis2/services/WS_Hello/plus_a?in=";
    // "http://192.168.182.159:8080/axis2/services/MyJavaClass/getArray?in=XYZ&size=22";

    // bigjhon server
    // "http://146.164.42.193:8081/axis2/services/WS_Hello/plus_a?in="

    // agilemessage.com server
    // "http://www.agilemessage.com:8081/axis2/services/ClassCloud/plus_a?in=";

    // do not use localhost, because it would point to the android device
    // use actual ip instead
    // "http://192.168.182.193:8080/axis2/services/MyJavaClass/plus_a?in=";
    // "http://localhost:8080/axis2/services/MyCloudJava/plus_a?in=";

    // rss do blog
    // "http://www.sabecomofazer.com.br/feed/";

    String outStr;

    Button myButton;
    EditText myText;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allow network in the UI thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myText = (EditText) findViewById(R.id.editText);
        // myText.setSingleLine();
        // myText.setInputType(InputType.TYPE_NULL);
        myText.setText("banan");

        myButton = (Button) findViewById(R.id.button);
        myButton.setText("plus_a on web services");

        // code for http://localhost
        // myButton.setOnClickListener(new OnClickListener() {
        // // @Override
        // public void onClick(View v) {
        //
        // try {
        // String inStr = myText.getText().toString();
        //
        // String urlStrComplete = urlStr + inStr;
        //
        // outStr = "empty";
        //
        // // outStr = getResponse(urlStrComplete);
        //
        // InputStream in = OpenHttpConnection(urlStrComplete);
        // DocumentBuilderFactory dbf = DocumentBuilderFactory
        // .newInstance();
        // DocumentBuilder builder = dbf.newDocumentBuilder();
        // Document doc = builder.parse(in);
        // NodeList nodeList = doc.getElementsByTagName("ns:return");
        // outStr = nodeList.item(0).getChildNodes().item(0)
        // .getNodeValue();
        //
        // Toast.makeText(getApplicationContext(),
        // "outStr =" + outStr, Toast.LENGTH_LONG).show();
        //
        // myText.setText(outStr);
        //
        // // } catch (IOException ex) {
        // //
        // // Toast.makeText(getApplicationContext(),
        // // "IOException, " + ex.getMessage(),
        // // Toast.LENGTH_LONG).show();
        //
        // } catch (Exception ex) {
        // Toast.makeText(getApplicationContext(),
        // "Exception, " + ex.getMessage(), Toast.LENGTH_LONG)
        // .show();
        //
        // }
        //
        // }
        // });

        // code for http://www.sabecomofazer.com.br/feed/
        myButton.setOnClickListener(new OnClickListener() {

            void doLongTask() {

                try {

                    // toastStr = urlStr;
                    // if (urlStr == null) {
                    // toastStr = "urlStr is NULL";
                    // }
                    // runOnUiThread(new Runnable() {
                    // @Override
                    // public void run() {
                    // Toast.makeText(getApplicationContext(), toastStr,
                    // Toast.LENGTH_LONG).show();
                    // }
                    // });

                    outStr = "empty";
                    mainDoc = HttpHelper.getXMLFromWeb(urlStr);

//					InputStream in;
//					in = HttpHelper.OpenHttpConnection(urlStr);
//					DocumentBuilderFactory dbf = DocumentBuilderFactory
//							.newInstance();
//					DocumentBuilder builder;
//					builder = dbf.newDocumentBuilder();
//					mainDoc = builder.parse(in);

                    // runOnUiThread(new Runnable() {
                    // @Override
                    // public void run() {
                    // outStr = "empty";
                    // InputStream in;
                    // try {
                    // in = OpenHttpConnection(urlStr);
                    // DocumentBuilderFactory dbf = DocumentBuilderFactory
                    // .newInstance();
                    // DocumentBuilder builder;
                    // builder = dbf.newDocumentBuilder();
                    // mainDoc = builder.parse(in);
                    // } catch (Exception e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    // }
                    // });

                    // NodeList channelNodeList =
                    // mainDoc.getElementsByTagName("channel");
                    // org.w3c.dom.Element channelDocument =
                    // (org.w3c.dom.Element) channelNodeList.item(0);
                    // NodeList titleChannelNodeList =
                    // channelDocument.getElementsByTagName("title");
                    // org.w3c.dom.Element titleDocument = (org.w3c.dom.Element)
                    // titleChannelNodeList.item(0);
                    // orchannelg.w3c.dom.Element itemDocument =
                    // (org.w3c.dom.Element)
                    // channelDocument.getElementsByTagName("item").item(0);
                    // org.w3c.dom.Element title2Document =
                    // (org.w3c.dom.Element)
                    // itemDocument.getElementsByTagName("title").item(0);

                    // for all categories in xml
                    // NodeList categoryNodeList =
                    // mainDoc.getElementsByTagName("category");

                    // blog sabercomofazer begin
                    // for the categories only of first item
                    // org.w3c.dom.Element channelDoc = (org.w3c.dom.Element)
                    // mainDoc
                    // .getElementsByTagName("channel").item(0);
                    // org.w3c.dom.Element itemDoc = (org.w3c.dom.Element)
                    // channelDoc
                    // .getElementsByTagName("item").item(0);
                    // NodeList categoryNodeList = itemDoc
                    // .getElementsByTagName("category");
                    //
                    // outStr = "número de categorias = "
                    // + categoryNodeList.getLength();
                    // outStr =
                    // titleDocument.getChildNodes().item(0).getNodeValue();
                    // blog sabercomofazer end

                    // plus_a local begin
                    NodeList nodeList = mainDoc
                            .getElementsByTagName("ns:return");
                    outStr = // myText.getText() +
                            nodeList.item(0).getChildNodes().item(0)
                                    .getNodeValue();
                    // plus_a local end

                    // plus_a local alternative begin
                    // HttpRequester hr = new HttpRequester();
                    // hr.setRequest(urlStr);
                    // outStr = hr.getResponse();
                    // plus_a local alternative end

                    // runOnUiThread(new Runnable() {
                    // @Override
                    // public void run() {
                    // Toast.makeText(getApplicationContext(),
                    // "outStr =" + outStr, Toast.LENGTH_LONG).show();
                    // }
                    // });
                    //
                    // //Toast.makeText(getApplicationContext(),
                    // // "outStr =" + outStr, Toast.LENGTH_LONG).show();
                    // Log.v("outStr =" + outStr,"==== outstr");

                    // getArray local begin
                    // NodeList nodeList = mainDoc
                    // .getElementsByTagName("ns:return");
                    // for (int i = 0; i < nodeList.getLength(); i++)
                    // outStr += "|"
                    // + nodeList.item(i).getChildNodes()
                    // .item(0).getNodeValue();
                    // getArray local end

                    // runOnUiThread(new Runnable() {
                    // @Override
                    // public void run() {
                    // outStr = "qualquer coisa";
                    // myText.setText(outStr);
                    // }
                    // });

                } catch (Exception ex) {
                    // Toast.makeText(getApplicationContext(),
                    // "Exception, " + ex.getMessage(), Toast.LENGTH_LONG)
                    // .show();
                    Log.v("exception", "==== outstr");
                    outStr = "exception !!!";

                }

            }

            // @Override
            public void onClick(View v) {

                progress = new ProgressDialog(context);
                progress.setMessage("accessing the cloud");
                progress.setTitle("wait");
                // progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(true);
                progress.setProgress(0);
                progress.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        doLongTask();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress.dismiss();
                                Log.v("taskdone", "==== taskdone");

                                myText.setText(outStr);

                                // Toast.makeText(context, "task done",
                                // Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).start();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
