package e.vladislavmun.vladislav_mun_2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnPayment;
    RadioButton rbSmall, rbMedium, rbLarge;
    RadioGroup rgSize;
    CheckBox cbCheese, cbPeperoni, cbSausages, cbBacon, cbGreenPepper;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgSize = findViewById(R.id.RGSize);
        btnPayment = findViewById(R.id.btnPayment);
        cbCheese = findViewById(R.id.cbCheese);
        cbPeperoni = findViewById(R.id.cbPeperoni);
        cbSausages = findViewById(R.id.cbSausages);
        cbBacon = findViewById(R.id.cbBacon);
        cbGreenPepper = findViewById(R.id.cbGreenPepper);
        result = findViewById(R.id.result);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCheckout();
            }
        });
    }

    public Size pizzaSize(View v){
        Size size = new Size();
        int radioId = rgSize.getCheckedRadioButtonId();

        if(radioId == R.id.rbSmall){
            size.setSize("Small");
            size.setVal(8.0);
        }
        if(radioId == R.id.rbMedium){
            size.setSize("Medium");
            size.setVal(10.0);
        }
        if(radioId == R.id.rbLarge){
            size.setSize("Large");
            size.setVal(12.0);
        }

        return size;
    }

    public Toppings getToppingValue(){
        double toppingValue = 0;
        List<String> toppingsName = new ArrayList();
        if(cbCheese.isChecked()){
            toppingValue += 1.00;
            toppingsName.add("Cheese");
        }
        if(cbPeperoni.isChecked()){
            toppingValue += 1.50;
            toppingsName.add("Peperoni");
        }
        if(cbSausages.isChecked()){
            toppingValue += 1.75;
            toppingsName.add("Sausages");
        }
        if(cbBacon.isChecked()){
            toppingValue += 1.25;
            toppingsName.add("Bacon");
        }
        if(cbGreenPepper.isChecked()){
            toppingValue += 1.00;
            toppingsName.add("Green pepper");
        }
        Toppings toppings = new Toppings(toppingsName,toppingValue);
        return toppings;
    }

    public void goToCheckout(){
        Intent intent = new Intent(this, Checkout.class);
        RadioButton rbSize = findViewById(rgSize.getCheckedRadioButtonId());
        Pizza newPizza = new Pizza(pizzaSize(rbSize), getToppingValue());
        intent.putExtra("myNewPizza", newPizza);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if (resultCode == RESULT_OK) {
                double myTotal = data.getDoubleExtra("myTotal",0);
                result.setText("Payment accepted\nYour total is " + myTotal);
                result.setTextColor(getResources().getColor(R.color.black));
                btnPayment.setText("create new order");
                btnPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.setText("");
                        btnPayment.setText("proceed to checkout");
                        cbCheese.setChecked(false);
                        cbPeperoni.setChecked(false);
                        cbGreenPepper.setChecked(false);
                        cbBacon.setChecked(false);
                        cbSausages.setChecked(false);
                        rgSize.check(R.id.rbSmall);
                        btnPayment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToCheckout();
                            }
                        });
                    }
                });
            }

            if (resultCode == RESULT_CANCELED){
                result.setTextColor(getResources().getColor(R.color.red));
                result.setText("Payment not accepted\nSorry unable to finish transaction");
                btnPayment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToCheckout();
                    }
                });
            }
        }
    }
}
