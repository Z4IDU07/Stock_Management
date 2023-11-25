package com.example.signuploginfirebase;

public class HelperClass {
    String pur_id, supp;
    int unit, rat, tot;

    public HelperClass(String pur_id, String supp, int unit, int rat, int tot) {
        this.pur_id = pur_id;
        this.supp = supp;
        this.unit = unit;
        this.rat = rat;
        this.tot = tot;
    }

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public String getSupp() {
        return supp;
    }

    public void setSupp(String supp) {
        this.supp = supp;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getRat() {
        return rat;
    }

    public void setRat(int rat) {
        this.rat = rat;
    }

    public int getTot() {
        return tot;
    }

    public void setTot(int tot) {
        this.tot = tot;
    }

    public HelperClass() {
    }


}
