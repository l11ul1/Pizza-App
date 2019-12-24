package e.vladislavmun.vladislav_mun_2;

import java.io.Serializable;

public class Pizza implements Serializable {
    private Size size;
    private Toppings toppings;
    private double total;
    public  Pizza(){

    }
    public Pizza(Size size, Toppings toppings) {
        this.size = size;
        this.toppings = toppings;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Toppings getToppings() {
        return toppings;
    }

    public void setToppings(Toppings toppings) {
        this.toppings = toppings;
    }

    public double getTot(){
        total = this.size.getVal() + this.toppings.gettVal();
        return total;
    }
}
