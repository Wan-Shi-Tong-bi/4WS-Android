package com.example.drg_fallpauschalenkatalog;

public class Datensatz {
    String kürzel;
    String bezeichnung;
    double mittlereVerweildauer;
    int ersterTagAbschalg;
    int ersterTagZusätzlichesGeld;

    public Datensatz(String kürzel, String bezeichnung, double mittlereVerweildauer, int ersterTagAbschalg, int ersterTagZusätzlichesGeld) {
        this.kürzel = kürzel;
        this.bezeichnung = bezeichnung;
        this.mittlereVerweildauer = mittlereVerweildauer;
        this.ersterTagAbschalg = ersterTagAbschalg;
        this.ersterTagZusätzlichesGeld = ersterTagZusätzlichesGeld;
    }

    public String getKürzel() {
        return kürzel;
    }

    public void setKürzel(String kürzel) {
        this.kürzel = kürzel;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getMittlereVerweildauer() {
        return mittlereVerweildauer;
    }

    public void setMittlereVerweildauer(double mittlereVerweildauer) {
        this.mittlereVerweildauer = mittlereVerweildauer;
    }

    public int getErsterTagAbschalg() {
        return ersterTagAbschalg;
    }

    public void setErsterTagAbschalg(int ersterTagAbschalg) {
        this.ersterTagAbschalg = ersterTagAbschalg;
    }

    public int getErsterTagZusätzlichesGeld() {
        return ersterTagZusätzlichesGeld;
    }

    public void setErsterTagZusätzlichesGeld(int ersterTagZusätzlichesGeld) {
        this.ersterTagZusätzlichesGeld = ersterTagZusätzlichesGeld;
    }

    @Override
    public String toString() {
        return "Datensatz: " + kürzel;
    }
}
