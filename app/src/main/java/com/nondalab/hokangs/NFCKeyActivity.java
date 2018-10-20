package com.nondalab.hokangs;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class NFCKeyActivity extends AppCompatActivity implements CardKeyEvent.CardKeyEventCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfckey);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CardKeyEvent.getInstance().setCallback(this);
        CardKeyEvent.getInstance().setCanOpen(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        CardKeyEvent.getInstance().setCanOpen(false);
        CardKeyEvent.getInstance().setCallback(null);
    }

    @Override
    public void cardKeyUsed() {
        CardKeyEvent.getInstance().setCanOpen(false);
        CardKeyEvent.getInstance().setCallback(null);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(500); // for 500 ms
        }


        final Handler handler = new Handler();
        handler.postDelayed( () -> {
            actionOpenDoor(null);
        }, 200);
    }

    public void actionOpenDoor(View view) {
        Intent intent = new Intent(this, WebviewActivity.class);
        startActivity(intent);
    }

    public void actionSkip(View view) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
        finish();
    }

}
