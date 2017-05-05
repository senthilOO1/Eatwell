package hi_focus.androiduser2.deva.com.eatwell.accountDetails;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hi_focus.androiduser2.deva.com.eatwell.R;

import static hi_focus.androiduser2.deva.com.eatwell.R.id.profile;


public class SignActivity extends Activity implements View.OnClickListener {

    TextView signInText,forgotPassword;

    CallbackManager callbackManager;

    LoginButton login;

    private String facebook_id,f_name, m_name, l_name, gender, profile_image, full_name, email_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        signInText = (TextView) findViewById(R.id.signInText);

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        callbackManager = CallbackManager.Factory.create();
        //login = (LoginButton)findViewById(R.id.login_button);

        String teams = "<u><font color='#79d440'>Forgot Password</font></u>";

        forgotPassword.setText(Html.fromHtml(teams));

        String account = "<font color='#ffffff'>New here?</font><u><font color='#79d440'> Sign Up</font></u>";

        signInText.setText(Html.fromHtml(account));

        forgotPassword.setOnClickListener(this);

        getKeyHash();

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AccessToken.getCurrentAccessToken() != null) {

                }
            }
        });

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebook_id=f_name= m_name= l_name= gender= profile_image= full_name= email_id="";

                if(AccessToken.getCurrentAccessToken() != null){
                    RequestData();

                }

                }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgotPassword:

                Intent intent = new Intent(this,ForgotPassword.class);
                startActivity(intent);

                break;
        }
    }

    private void getKeyHash() {

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.androidlift.facebookdemo", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }

    public void RequestData(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                JSONObject json = response.getJSONObject();
                System.out.println("Json data :"+json);
                try {
                    if(json != null){
                        String text = "<b>Name :</b> "+json.getString("name")+"<br><br><b>Email :</b> "+json.getString("email")+"<br><br><b>Profile link :</b> "+json.getString("link");
                        /*details_txt.setText(Html.fromHtml(text));
                        profile.setProfileId(json.getString("id"));*/
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
