package hi_focus.androiduser2.deva.com.eatwell.accountDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hi_focus.androiduser2.deva.com.eatwell.R;


public class DashBoard extends Activity implements View.OnClickListener {

    Button signUp,signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        signUp = (Button) findViewById(R.id.signUp);

        signIn = (Button) findViewById(R.id.signIn);

        signUp.setOnClickListener(this);

        signIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signUp:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.signIn:
                Intent intent1 = new Intent(this,SignActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
