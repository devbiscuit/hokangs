package com.nondalab.hokangs;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nondalab.hokangs.hce.LoyaltyCardReader;

public class NFCReceiveActivity extends AppCompatActivity implements LoyaltyCardReader.AccountCallback {

    public static int READER_FLAGS =
            NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;
    public LoyaltyCardReader mLoyaltyCardReader;

    private ImageView mDoorClose;
    private ImageView mDoorOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcreceive);
        mDoorClose = findViewById(R.id.iv_door_closed);
        mDoorOpen = findViewById(R.id.iv_door_open);

        mLoyaltyCardReader = new LoyaltyCardReader(this);
        // Disable Android Beam and register our card reader callback
        enableReaderMode();
    }

    @Override
    public void onAccountReceived(String account) {
        Log.d("HCE_READ", account);
        if(account.equals("20000")) {
            runOnUiThread(this::openDoor);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        disableReaderMode();
    }

    @Override
    public void onResume() {
        super.onResume();
        enableReaderMode();
    }

    private void enableReaderMode() {

        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.enableReaderMode(this, mLoyaltyCardReader, READER_FLAGS, null);
        }
    }

    private void disableReaderMode() {
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.disableReaderMode(this);
        }
    }


    public void closeDoor(View view){
        mDoorClose.setVisibility(View.VISIBLE);
        mDoorOpen.setVisibility(View.GONE);
    }

    private void openDoor() {
        mDoorClose.setVisibility(View.GONE);
        mDoorOpen.setVisibility(View.VISIBLE);
    }

}
