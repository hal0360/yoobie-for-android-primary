package nz.co.udenbrothers.yoobie;

import android.os.Bundle;
import android.widget.CheckBox;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.services.DataService;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Kit;
import nz.co.udenbrothers.yoobie.tools.Match;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.wigets.WaveView;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;

public class SignUp1Activity extends RootActivity {

    YoobieInput mail, pass, pass2;
    CheckBox terms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);

        initService(DataService.class);
        mail = findViewById(R.id.inputMail);
        pass = findViewById(R.id.inputPassword);
        pass2 = findViewById(R.id.inputComPassword);
        terms = findViewById(R.id.termsCheckbox);
        clicked(R.id.continueButton, v-> checkUser());
    }

    @Override
    protected void onStart() {
        super.onStart();
        WaveView waveView = findViewById(R.id.wave);
        delay(300, () -> waveView.setProgress(0.6f));
    }

    private void checkUser(){
        if(!Match.mail(mail.getText())){
            mail.error("Invalid mail");
            return;
        }
        if(pass.getText().length() < 6){
            pass.error("Must have at least 6 lengh");
            return;
        }
        if(!pass2.getText().equals(pass.getText())){
            pass2.error("Password not match");
            pass2.setText("");
            return;
        }
        if (!terms.isChecked()){
            Kit.show(this, "Please agree to terms and conditions");
            return;
        }

        RequestTask requestTask = new RequestTask(this, Url.CHECK_EMAIL(mail.getText()), null);
        requestTask.onSuccess(r->{
            if(r.content.equals("true")) {
                Profile.mail(mail.getText());
                Profile.pass(pass.getText());
                pushActivity(SignUp2Activity.class);
            }else{
                mail.error("Email already used");
            }
        });
        requestTask.onFail(r-> alert("Error! Server problem"));
        requestTask.send(null);
    }
}
