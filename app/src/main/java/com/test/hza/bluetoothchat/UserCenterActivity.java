package com.test.hza.bluetoothchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserCenterActivity extends AppCompatActivity {

    private EditText edUsername;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);


        pref = getSharedPreferences(Constants.PREF,0);

        if(!pref.getString(Constants.USERNAME,"").equalsIgnoreCase("")){ // check whether username provided or not
            startMainActivity();
        }

        edUsername = (EditText) findViewById(R.id.ed_username);
        Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername = edUsername.getText().toString();
                if(!mUsername.equalsIgnoreCase("")){
                    pref.edit().putString(Constants.USERNAME,mUsername).apply();
                    startMainActivity();
                }else{
                    edUsername.setError("Please provide username!");

                }
            }
        });
    }

    private void startMainActivity() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_center, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
