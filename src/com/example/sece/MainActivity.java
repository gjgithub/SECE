package com.example.sece;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	public Button changeCreds;
	
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);
        changeCreds = (Button) findViewById(R.id.changeCreds);
    	changeCreds.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sece, menu);
        
        
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.reset:
			SharedPreferences myReset = getSharedPreferences("creds.txt",0);
			SharedPreferences.Editor myResetEditor = myReset.edit();
			SECEActivity object = new SECEActivity();
			//myResetEditor.putString(object.SECEusername, null);
			//myResetEditor.putString(object.SECEpassword, null);
			myResetEditor.clear().commit();
			Intent resetIntent = new Intent(this, SECEActivity.class);
			startActivity(resetIntent);
		}
    	
    	return true;
    
    }

	@Override
	public void onClick(View v) {
		if (v == changeCreds){
        	
        }
	}
    
}

