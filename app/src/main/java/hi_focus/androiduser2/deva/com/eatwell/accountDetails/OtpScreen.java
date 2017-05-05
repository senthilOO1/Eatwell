package hi_focus.androiduser2.deva.com.eatwell.accountDetails;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import hi_focus.androiduser2.deva.com.eatwell.R;


public class OtpScreen extends Activity {
    TextView accountText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        accountText = (TextView) findViewById(R.id.accountText);

        String account = "<font color='#ffffff'>Do not receive sms.</font><u><font color='#79d440'>Repeat</font></u>";

        accountText.setText(Html.fromHtml(account));
    }
}
