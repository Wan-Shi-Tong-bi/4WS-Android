package com.example.mobilekomponetezurpatientenverwaltung;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ClientTaskNewPatient extends AsyncTask<String, Object, Void> {

    boolean activated;
    boolean activated2;
    public MainActivity activity;
    public patient_anzeigen activityNeuerEintrag;
    ListView lstViewPatienten;
    ListView lstViewEintrag;
    int compare;

    public ClientTaskNewPatient(MainActivity activity) {
        this.activity = activity;
    }

    public ClientTaskNewPatient(patient_anzeigen activityNeuerEintrag) {
        this.activityNeuerEintrag = activityNeuerEintrag;
    }

    public ClientTaskNewPatient() {
    }


    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        activated = true;
        activated2 = true;
        compare = 0;
        if (activity != null) {
            lstViewPatienten = activity.findViewById(R.id.lstViewPatienten);
        } else if (activityNeuerEintrag != null) {
            lstViewEintrag = activityNeuerEintrag.findViewById(R.id.lstViewEintraegeShow);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        if (values[0].equals("patient")) {
            ArrayAdapter<Patient> arrAdapterPatient = new ArrayAdapter<Patient>(activity, android.R.layout.simple_list_item_1, (ArrayList) values[1]);
            lstViewPatienten.setAdapter(arrAdapterPatient);
        } else if (values[0].equals("eintrag")) {
            ArrayAdapter<Patient> arrAdapterEintrag = new ArrayAdapter<Patient>(activityNeuerEintrag, android.R.layout.simple_list_item_1, (ArrayList) values[1]);
            lstViewEintrag.setAdapter(arrAdapterEintrag);
        }

        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            if (strings[0].equals("newpatient")) {
                URL newpatient = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/newpatient/" + strings[1] + "/" + strings[2]);
                BufferedReader br = new BufferedReader(new InputStreamReader(newpatient.openStream()));
                String antwort = br.readLine();
                br.close();
                Log.i("NEWPATIENT", "OK" + antwort);
            } else if (strings[0].equals("neweintrag")) {
                URL neweintrag = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/newentry/" + strings[1] + "/" + strings[2]);
                BufferedReader br = new BufferedReader(new InputStreamReader(neweintrag.openStream()));
                String antwort = br.readLine();
                br.close();
                Log.i("NEWENTRY", "OK  " + antwort);
            } else if (strings[0].equals("updatestart")) {
                while (activated) {
                    try {
                        URL alleIds = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/patientenIDs");
                        BufferedReader br = new BufferedReader(new InputStreamReader(alleIds.openStream()));
                        String patientjson = br.readLine();
                        br.close();
                        JSONObject myjson = new JSONObject(patientjson);
                        JSONArray jsonarry = myjson.getJSONArray("Liste der IDs");
                        ArrayList<String> array = new ArrayList<String>();
                        for (int i = 0; i < jsonarry.length(); i++) {
                            JSONObject another_json_object = jsonarry.getJSONObject(i);
                            array.add(another_json_object.getString("id"));
                        }

//                if (array.size() != compare) {
                        ArrayList<Patient> patienten = new ArrayList<>();
                        for (int i = 0; i < array.size(); i++) {
                            URL patient = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/patient/" + array.get(i));
                            br = new BufferedReader(new InputStreamReader(patient.openStream()));
                            patientjson = br.readLine();
                            br.close();
                            myjson = new JSONObject(patientjson);

                            JSONArray jsonarray = myjson.getJSONArray("Daten");
                            ArrayList<Eintrag> arrayEintrag = new ArrayList();
                            for (int j = 0; j < jsonarray.length(); j++) {
                                JSONObject another_json_object = jsonarray.getJSONObject(j);
                                Eintrag e = new Eintrag(another_json_object.getString("Datum"), another_json_object.getString("Eintrag"));
                                arrayEintrag.add(e);
                            }
                            Patient p = new Patient(myjson.getInt("id"), myjson.getString("vorname"), myjson.getString("nachname"), arrayEintrag);
                            patienten.add(p);
                        }

                        publishProgress("patient", patienten);
//                }
//                        compare = array.size();
                        Thread.sleep(2000);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("EXCEPTION", e.getMessage() + " --> " + e.getLocalizedMessage());
                    }
                }
            } else if (strings[0].equals("update")) {
                URL patient = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/patient/" + strings[1]);
                BufferedReader br = new BufferedReader(new InputStreamReader(patient.openStream()));
                String datenjson = br.readLine();
                br.close();

                JSONObject myjson = new JSONObject(datenjson);
                JSONArray jsonarray = myjson.getJSONArray("Daten");
                ArrayList<Eintrag> arrayEintrag = new ArrayList();
                for (int j = 0; j < jsonarray.length(); j++) {
                    JSONObject another_json_object = jsonarray.getJSONObject(j);
                    Eintrag e = new Eintrag(another_json_object.getString("Datum"), another_json_object.getString("Eintrag"));
                    arrayEintrag.add(e);
                }
                publishProgress("eintrag", arrayEintrag);
            }else if(strings[0].equals("updatepatientalways")){
                while(activated2){
                    URL patient = new URL("http://192.168.0.120:8080" + "/E_REST_Server/resources/patienten/patient/" + strings[1]);
                    BufferedReader br = new BufferedReader(new InputStreamReader(patient.openStream()));
                    String datenjson = br.readLine();
                    br.close();

                    JSONObject myjson = new JSONObject(datenjson);
                    JSONArray jsonarray = myjson.getJSONArray("Daten");
                    ArrayList<Eintrag> arrayEintrag = new ArrayList();
                    for (int j = 0; j < jsonarray.length(); j++) {
                        JSONObject another_json_object = jsonarray.getJSONObject(j);
                        Eintrag e = new Eintrag(another_json_object.getString("Datum"), another_json_object.getString("Eintrag"));
                        arrayEintrag.add(e);
                    }
                    publishProgress("eintrag", arrayEintrag);
                    Thread.sleep(4000);
                }

            }

        } catch (Exception e) {
            Log.e("NEWPATIENT/NEWENTRY EXCEPTION", e.getMessage());
        }
        return null;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setActivated2(boolean activated2) {
        this.activated2 = activated2;
    }
}
