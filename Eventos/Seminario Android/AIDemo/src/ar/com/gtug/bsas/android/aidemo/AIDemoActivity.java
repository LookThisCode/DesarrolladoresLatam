package ar.com.gtug.bsas.android.aidemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AIDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button  = (Button) findViewById(R.id.button1);
        
        button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				 Toast.makeText(getApplicationContext(), 
					        "Votos enviados",
					        Toast.LENGTH_SHORT).show();
				
			}
		});
    }
}