package com.example.cdaviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class showPatient extends AppCompatActivity {

    TextView txtViewPatientShow;
    TextView txtViewArztShow;
    ListView lstViewEinträgeShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String file = b.getString("filename");
        String path = b.getString("path");
        txtViewPatientShow = findViewById(R.id.txtViewPatientShow);
        txtViewArztShow = findViewById(R.id.txtViewArztShow);
        lstViewEinträgeShow = findViewById(R.id.lstViewEinträgeShow);

        try {
            InputStream is = getAssets().open(path + "/" + file);
            DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            //PATIENT auslesen
            NodeList nList = doc.getElementsByTagName("patientRole");
            Node node = nList.item(0);
            Element e = (Element) node;
            txtViewPatientShow.setText(txtViewPatientShow.getText().toString() + "\n" + getValue("given", e) + "\n" +
                    getValue("family", e) + "\n" +
                    getValue("city", e));

            //ARZT auslesen
            nList = doc.getElementsByTagName("assignedAuthor");
            node = nList.item(0);
            e = (Element) node;
            txtViewArztShow.setText(txtViewArztShow.getText().toString() + "\n" + getValue("given", e) + "\n" +
                    getValue("family", e) + "\n" +
                    getValue("city", e));

            //PROTOKOLL auslesen
            ArrayList<String> einträge = new ArrayList<>();
            nList = doc.getElementsByTagName("component");
            for (int i = 0; i < nList.getLength(); i++) {
                node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    e = (Element) node;
                    einträge.add(getValue("title", e) + "\t" + getValue("text", e));
                }
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, einträge);
            lstViewEinträgeShow.setAdapter(adapter);
            Toast.makeText(this, "Einlesen war erfolgreich", Toast.LENGTH_SHORT).show();

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
            Toast.makeText(this, "Einlesen fehlgeschlagen. Unbekannter fehler", Toast.LENGTH_LONG).show();

        }
    }

    public String getValue(String tag, Element element) {
        NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nList.item(0);
        return node.getNodeValue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}