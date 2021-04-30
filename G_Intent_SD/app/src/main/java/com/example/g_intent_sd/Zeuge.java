package com.example.g_intent_sd;

import java.io.Serializable;

public class Zeuge implements Serializable {
    String vorname;
    String nachnaname;
    int alter;

    public Zeuge(String vorname, String nachnaname, int alter) {
        this.vorname = vorname;
        this.nachnaname = nachnaname;
        this.alter = alter;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachnaname() {
        return nachnaname;
    }

    public void setNachnaname(String nachnaname) {
        this.nachnaname = nachnaname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    @Override
    public String toString() {
        return "Zeuge: " + vorname.toUpperCase() + ", " + nachnaname.toUpperCase();
    }
}
