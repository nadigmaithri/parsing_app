package com.example.parser;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class display extends AppCompatActivity {
    TextView dxml,djson;
    int mode=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        dxml=(TextView)findViewById(R.id.txml);
        djson=(TextView)findViewById(R.id.tjson);
        Intent it= getIntent();
        mode=it.getIntExtra("mode",0);
        System.out.println(mode);
        if(mode==1)
            parseJson();

        else
        parseXmlDocument();


    }

    public String parseXmlDocument() {
        try {
            InputStream is = getAssets().open("data1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element=doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("city");
            for (int i = 0; i< nList.getLength(); i++)
            {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element2 = (Element) node;
                    dxml.setText("City Name : " + getValue("city_name", element2)+"\n");
                    dxml.append("Latitude : " + getValue("Latitude", element2)+"\n");
                    dxml.append("Longitude : " + getValue("Longitude", element2)+"\n");
                    dxml.append("Temperature : " + getValue("Temperature", element2)+"\n");
                    dxml.append("Humidity : " + getValue("Humidity", element2)+"\n");
                    djson.setText("");
                }
            }
        }catch (Exception e)
        {e.printStackTrace();}
        return null;
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    public void parseJson() {
        try {
            InputStream inputStream=getAssets().open("data.json");
            byte[] data=new byte[inputStream.available()];
            inputStream.read(data);
            String readData=new String(data);
            JSONObject jsonObject=new JSONObject(readData);
            JSONObject jsonObject1=jsonObject.getJSONObject("city");
            djson.setText("City Name:"+jsonObject1.getString("city_name")+"\n");
            djson.append("Latitude:"+jsonObject1.getString("Latitude")+"\n");
            djson.append("Longitude"+jsonObject1.getString("Longitude")+"\n");
            djson.append("Temperature:"+jsonObject1.getInt("Temperature")+"\n");
            djson.append("Humidity"+jsonObject1.getString("Humidity")+"\n");
        }catch (Exception e){e.printStackTrace();}}
}