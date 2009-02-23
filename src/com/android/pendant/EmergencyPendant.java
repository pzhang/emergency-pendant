package com.android.pendant;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
public class EmergencyPendant extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent svc = new Intent(this, InputWrapper.class);
        startService(svc);
        setContentView(R.layout.main);
    }
}