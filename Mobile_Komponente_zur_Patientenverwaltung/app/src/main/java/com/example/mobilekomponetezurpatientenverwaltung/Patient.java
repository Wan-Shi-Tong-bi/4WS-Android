package com.example.mobilekomponetezurpatientenverwaltung;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient implements Serializable {
    int id;
    String vorname;
    String nachname;
    ArrayList<Eintrag> einträge;

    public Patient(int id, String vorname, String nachname) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
    }

    public Patient(int id, String vorname, String nachname, ArrayList<Eintrag> einträge) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.einträge = einträge;
    }

    public Patient() {
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public ArrayList<Eintrag> getEinträge() {
        return einträge;
    }

    public void setEinträge(ArrayList<Eintrag> einträge) {
        this.einträge = einträge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addEintrag (Eintrag e){
        einträge.add(e);
    }

    @Override
    public String toString() {
        return vorname +", " + nachname;
    }
}
