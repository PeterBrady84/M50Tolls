package com.peter.m50tolls;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final double CAR = 2.0;
    private static final double PUBLIC_SERVICE_VEHICLE = 2.0;
    private static final double BUS = 2.8;
    private static final double GOODS = 4.1;

    private static final double DISCOUNT_RATE = 0.2;

    private Spinner vehicleSelect;
    private CheckBox eTag;
    private TextView totalCharge;
    private double cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vehicleSelect = (Spinner) findViewById(R.id.tollSpinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.vehicle_type, R
                .layout.support_simple_spinner_dropdown_item);
        vehicleSelect.setAdapter(adapter);

        eTag = (CheckBox) findViewById(R.id.hasETagButton);

        Button calculate = (Button) findViewById(R.id.calculateButton);

        totalCharge = (TextView) findViewById(R.id.totalChargeTextView);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCost();
                DecimalFormat df = new DecimalFormat("0.00");
                Log.d("Cost", String.valueOf(cost));
                totalCharge.setText(getString(R.string.total_charge, df.format(cost)));
            }
        });
    }

    public void calculateCost() {
        if (vehicleSelect.getSelectedItem().toString().equals("Car")) {
            cost = CAR;
        } else if (vehicleSelect.getSelectedItem().toString().equals("Public Service Vehicle")) {
            cost = PUBLIC_SERVICE_VEHICLE;
        } else if (vehicleSelect.getSelectedItem().toString().equals("Bus")) {
            cost = BUS;
        } else {
            cost = GOODS;
        }
        if (eTag.isChecked()) {
            cost -= cost * DISCOUNT_RATE;
        }
    }
}
