package com.example.documents.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CreditCard {

    @Id
    private String kontoNummer;
    private String bic;
    private String name;

    public CreditCard() { }

    public CreditCard(String kontoNummer, String bic, String name) {
        this.kontoNummer = kontoNummer;
        this.bic = bic;
        this.name = name;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (kontoNummer != null ? !kontoNummer.equals(that.kontoNummer) : that.kontoNummer != null) return false;
        if (bic != null ? !bic.equals(that.bic) : that.bic != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = kontoNummer != null ? kontoNummer.hashCode() : 0;
        result = 31 * result + (bic != null ? bic.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
