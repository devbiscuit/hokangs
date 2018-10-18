package com.nondalab.hokangs;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NFCReceiveActivity extends AppCompatActivity {

    private ImageView mDoorClose;
    private ImageView mDoorOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcreceive);
        mDoorClose = findViewById(R.id.iv_door_closed);
        mDoorOpen = findViewById(R.id.iv_door_open);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = getIntent();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);

            NdefMessage message = (NdefMessage) rawMessages[0]; // only one message transferred

            Toast.makeText(this, "Door unlocked", Toast.LENGTH_LONG).show();
            openDoor();
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
