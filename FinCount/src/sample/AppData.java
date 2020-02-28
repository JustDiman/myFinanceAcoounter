package sample;

import java.util.ArrayList;
import java.util.List;

public class AppData {

    private String date;
    private String mainIncome;
    private String advanceIncome;
    private String anotherIncome;
    private String toolTip;
    private String costEat;
    private String costApartament;
    private String costNeeded;
    private String costUseful;
    private String costDetrimental;


    AppData(String date){
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public String getMainIncome() { return mainIncome; }

    public void setMainIncome(String mainIncome) {
        this.mainIncome = mainIncome;
    }

    public String getAdvanceIncome() {
        return advanceIncome;
    }

    public void setAdvanceIncome(String advanceIncome) {
        this.advanceIncome = advanceIncome;
    }

    public String getAnotherIncome() {
        return anotherIncome;
    }

    public void setAnotherIncome(String anotherIncome) {
        this.anotherIncome = anotherIncome;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getCostEat() {
        return costEat;
    }

    public void setCostEat(String costEat) {
        this.costEat = costEat;
    }

    public String getCostApartament() {
        return costApartament;
    }

    public void setCostApartament(String costApartament) {
        this.costApartament = costApartament;
    }

    public String getCostNeeded() { return costNeeded; }

    public void setCostNeeded(String costNeeded) {
        this.costNeeded = costNeeded;
    }

    public String getCostUseful() { return costUseful; }

    public void setCostUseful(String costUseful) {
        this.costUseful = costUseful;
    }

    public String getCostDetrimental() {
        return costDetrimental;
    }

    public void setCostDetrimental(String costDetrimental) {
        this.costDetrimental = costDetrimental;
    }


}

class Promisser {

    private String promisser;
    private String countPromiss;

    public void setPromisser(String prom) {
        this.promisser = prom;
    }

    public String getPromisser() {
        return promisser;
    }

    public String getCountPromiss() {
        return countPromiss;
    }

    public void setCountPromiss(String countPromiss) {
        this.countPromiss = countPromiss;
    }
}


