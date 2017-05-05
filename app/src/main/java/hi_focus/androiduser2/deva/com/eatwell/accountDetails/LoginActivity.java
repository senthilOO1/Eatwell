package hi_focus.androiduser2.deva.com.eatwell.accountDetails;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


import hi_focus.androiduser2.deva.com.eatwell.Home;
import hi_focus.androiduser2.deva.com.eatwell.R;


public class LoginActivity extends Activity implements View.OnClickListener{

     private CheckBox terms;

     private TextView accountText;

    private Button signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        terms = (CheckBox) findViewById(R.id.terms);

        accountText = (TextView) findViewById(R.id.accountText);

        signUp = (Button) findViewById(R.id.signUp);

        String teams = "<font color='#ffffff'>I agree with </font><u><font color='#79d440'>Terms & Conditions</font></u>";

        terms.setText(Html.fromHtml(teams));

        String account = "<font color='#ffffff'>I have </font><u><font color='#79d440'>an account</font></u>";

        accountText.setText(Html.fromHtml(account));

        terms.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        terms.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (terms.isChecked()) {
                    terms.setChecked(true);
                    terms.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#79d440")));

                } else {
                    terms.setButtonTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                    terms.setChecked(false);
                }


            }
        });

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.signUp:
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
        }
    }
}
