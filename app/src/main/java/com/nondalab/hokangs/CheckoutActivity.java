package com.nondalab.hokangs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = "CheckoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_checkout);
    }

    public void actionCheckout(View view) {
        Intent intent = new Intent(this, ReceiptActivity.class);
        startActivity(intent);
        finish();
    }
}
