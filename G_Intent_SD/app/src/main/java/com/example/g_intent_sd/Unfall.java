package com.example.g_intent_sd;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Unfall implements Serializable {
    String datum;
    String uhrzeit;
    String strasse;
    int hausnummer;
    int plz;
    String ort;
    boolean verlezte;
    boolean andereSchaeden;
    int id;
    ArrayList <Zeuge> zeugen;

    public Unfall(String datum, String uhrzeit, String strasse, int hausnummer, int plz, String ort, boolean verlezte, boolean andereSchaeden, int id) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.verlezte = verlezte;
        this.andereSchaeden = andereSchaeden;
        this.id = id;
    }

    public Unfall(String datum, String uhrzeit, String strasse, int hausnummer, int plz, String ort, boolean verlezte, boolean andereSchaeden, int id, ArrayList<Zeuge> zeugen) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
        this.verlezte = verlezte;
        this.andereSchaeden = andereSchaeden;
        this.id = id;
        this.zeugen = zeugen;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        this.uhrzeit = uhrzeit;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public boolean isVerlezte() {
        return verlezte;
    }

    public void setVerlezte(boolean verlezte) {
        this.verlezte = verlezte;
    }

    public boolean isAndereSchaeden() {
        return andereSchaeden;
    }

    public void setAndereSchaeden(boolean andereSchaeden) {
        this.andereSchaeden = andereSchaeden;
    }

    @NonNull
    @Override
    public String toString() {
        return "Unfall " + id +", am " + datum + ", " +uhrzeit+" Zeugen: " + zeugen.size();
    }
}
