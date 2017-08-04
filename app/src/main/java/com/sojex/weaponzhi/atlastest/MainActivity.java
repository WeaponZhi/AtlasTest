package com.sojex.weaponzhi.atlastest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButton, mButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_first_bundle);
        mButton1 = (Button) findViewById(R.id.btn_dynamic);
        mButton.setOnClickListener(this);
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Updater.update(getBaseContext());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }.execute();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName(getBaseContext(), "com.atlas.mylibrary.FirstBundleActivity");
        startActivity(intent);
    }
}
