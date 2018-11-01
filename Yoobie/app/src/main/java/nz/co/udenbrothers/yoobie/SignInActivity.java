package nz.co.udenbrothers.yoobie;

import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.serverObjects.LoginData;
import nz.co.udenbrothers.yoobie.serverObjects.LoginToken;
import nz.co.udenbrothers.yoobie.services.DownloadService;
import nz.co.udenbrothers.yoobie.services.ProfileService;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.Match;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.wigets.WaveView;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;

public class SignInActivity extends RootActivity {

    YoobieInput mail, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mail = findViewById(R.id.inputMail);
        pass = findViewById(R.id.inputPassword);
        clicked(R.id.signInButton, v-> login());
    }

    @Override
    protected void onStart() {
        super.onStart();
        WaveView waveView = findViewById(R.id.wave);
        delay(300, () -> waveView.setProgress(0.6f));
    }

    private void login(){
        if(!Match.mail(mail.getText())){
            mail.error("Invalid mail");
            return;
        }
        if(pass.getText().length() < 6){
            pass.error("Must have at least 6 lengh");
            return;
        }
        LoginData loginData = new LoginData(mail.getText(), pass.getText(), Build.MANUFACTURER + " " + android.os.Build.MODEL);
        RequestTask requestTask = new RequestTask(this, Url.SIGN_IN, null);
        requestTask.onError(r-> pass.error("Incorrect password or email"));
        requestTask.onSuccess(r->{
            LoginToken loginToken = Json.from(r.content,LoginToken.class);
            Profile.userID(loginToken.userId);
            try {
                Profile.token(Base64.encodeToString((loginToken.userId + ":" + loginToken.accessToken).getBytes("UTF-8"), Base64.NO_WRAP));
                initService(ProfileService.class);
               // initService(DownloadService.class);
                clearActivity(MainActivity.class);
            } catch (UnsupportedEncodingException e) {
                Log.e("Error Base64",e+"");
                alert("Data fetching error");
            }
        });
        requestTask.send(loginData.toJson());
    }

}
