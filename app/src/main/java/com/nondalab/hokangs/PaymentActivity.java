package com.nondalab.hokangs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public final class PaymentActivity extends AppCompatActivity {

    private static final String TAG = "PaymentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_payment);
    }

    public void actionAuthentication(View view) {
        // next
    }

    public void actionPay(View view) {
        Intent intent = new Intent(this, NFCKeyActivity.class);
        startActivity(intent);
        finish();
    }
}