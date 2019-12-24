package e.vladislavmun.vladislav_mun_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class Checkout extends AppCompatActivity {
    TextView txtPizza, txtToppings, txtTotal;
    Button btnPayment;
    RadioGroup rgPayment;
    RadioButton rbCash, rbCredit;
    Random random = new Random();
    double total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent intent = getIntent();
        Pizza myPizza = (Pizza)intent.getSerializableExtra("myNewPizza");
        btnPayment = findViewById(R.id.btnPay);
        txtPizza = findViewById(R.id.txtPizza);
        txtToppings = findViewById(R.id.txtToppings);
        txtTotal = findViewById(R.id.txtTot);
        rgPayment = findViewById(R.id.rgPayment);
        rbCash = findViewById(R.id.rbCash);
        rbCredit = findViewById(R.id.rbCredit);

        txtPizza.setText(myPizza.getSize().getSize() + " Pizza with: ");
        for(int i=0; i < myPizza.getToppings().getTopp().size(); i++){
            txtToppings.setText(txtToppings.getText() + myPizza.getToppings().getTopp().get(i) + "\n");
        }

        total = Math.round(myPizza.getTot()*1.13*100.0)/100.0;
        txtTotal.setText("Total Price with HST: " + total);

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentProcedure();
            }
        });
    }

    public void paymentProcedure(){
        int radioId = rgPayment.getCheckedRadioButtonId();
        if(radioId == R.id.rbCash){
            Intent intentResult = new Intent();
            intentResult.putExtra("myTotal", total);
            setResult(RESULT_OK, intentResult);
            finish();
        }
        if(radioId == R.id.rbCredit){
            if (next() == 1) {
                Intent intentResult = new Intent();
                setResult(RESULT_CANCELED, intentResult);
                finish();
            } else {
                Intent intentResult = new Intent();
                intentResult.putExtra("myTotal", total);
                setResult(RESULT_OK, intentResult);
                finish();
            }
        }
    }

    private int next() {
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 2;
        }
    }


}
