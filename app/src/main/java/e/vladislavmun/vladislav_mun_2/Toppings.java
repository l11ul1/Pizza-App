package e.vladislavmun.vladislav_mun_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Toppings implements Serializable {
    private List<String> toppings;
    private double tVal;

    public Toppings(){}

    public Toppings(List toppings, double tVal) {
        this.toppings = toppings;
        this.tVal = tVal;
    }

    public List<String> getTopp() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public double gettVal() {
        return tVal;
    }

    public void settVal(double tVal) {
        this.tVal = tVal;
    }
}
