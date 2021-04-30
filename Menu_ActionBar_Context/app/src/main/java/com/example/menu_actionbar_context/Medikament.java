package com.example.menu_actionbar_context;

public class Medikament {

    String datum;
    String uhrzeit;
    String hersteller;
    String produkt;
    int menge;
    int position;

    public Medikament(String datum, String uhrzeit, String hersteller, String produkt, int menge) {
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.hersteller = hersteller;
        this.produkt = produkt;
        this.menge = menge;
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

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getProdukt() {
        return produkt;
    }

    public void setProdukt(String produkt) {
        this.produkt = produkt;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return produkt.toUpperCase() + " von " + hersteller + " (" + menge + ") ";
    }
}
