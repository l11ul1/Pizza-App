package e.vladislavmun.vladislav_mun_2;

import java.io.Serializable;

public class Size implements Serializable {
    private double val;
    private String size;

    public Size(){}

    public Size(double val, String size) {
        this.val = val;
        this.size = size;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
