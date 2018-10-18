package com.nondalab.hokangs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Toast;

import static android.nfc.NdefRecord.createMime;

public class NFCKeyActivity extends AppCompatActivity implements NfcAdapter.CreateNdefMessageCallback,
        NfcAdapter.OnNdefPushCompleteCallback {

    NfcAdapter mNfcAdapter;
    boolean mAndroidBeamAvailable  = false;
    private static final int MESSAGE_SENT = 1;
    /** This handler receives a message from onNdefPushComplete */
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_SENT:
                        Toast.makeText(getApplicationContext(), "Message sent!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        setContentView(R.layout.activity_nfckey);
        mAndroidBeamAvailable = true;
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        mNfcAdapter.setNdefPushMessageCallback(this, this);
        // Register callback to listen for message-sent success
        mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        String text = ("Beam me up, Android!\n\n" +
                "Beam Time: " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMime(
                        "application/vnd.com.example.android.beam", text.getBytes())
                        /**
                         * The Android Application Record (AAR) is commented out. When a device
                         * receives a push with an AAR in it, the application specified in the AAR
                         * is guaranteed to run. The AAR overrides the tag dispatch system.
                         * You can add it back in to guarantee that this
                         * activity starts when receiving a beamed message. For now, this code
                         * uses the tag dispatch system.
                        */
                        //,NdefRecord.createApplicationRecord("com.example.android.beam")
                });
        return msg;

    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        mHandler.obtainMessage(MESSAGE_SENT).sendToTarget();

    }


}
