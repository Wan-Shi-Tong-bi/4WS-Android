package com.example.mobilekomponetezurpatientenverwaltung;

import java.io.Serializable;

public class Eintrag implements Serializable {
    String datum;
    String nachricht;


    public Eintrag(String datum, String nachricht) {
        this.datum = datum;
        this.nachricht = nachricht;
    }

    public Eintrag() {
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    @Override
    public String toString() {
        return datum +" --> " + nachricht;
    }
}
