package nz.co.udenbrothers.yoobie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.tools.Kit;
import nz.co.udenbrothers.yoobie.tools.Match;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;


public class SignUpFragment extends RootFragment {

    YoobieInput mail, pass, conPass;
    CheckBox termsCheck;

    public SignUpFragment() {
        super(R.layout.fragment_sign_up);
    }

    @Override
    public void created() {
        StartActivity activity = (StartActivity) parent;

        mail = findViewById(R.id.inputMail);
        pass = findViewById(R.id.inputPassword);
        conPass = findViewById(R.id.inputComPassword);
        termsCheck = findViewById(R.id.termsCheckbox);

    }


    private void registerOne(){
        if(!Match.mail(mail.getText())) {
            mail.error("Invalid email address");
            return;
        }

        if(pass.getText().length() < 6){
            pass.error("Must have atleast 6 length");
            return;
        }

        if(!pass.getText().equals(conPass.getText())){
            conPass.error("Password not match");
            return;
        }

        if (!termsCheck.isChecked()){
            Kit.show(parent,"Please agree to terms and conditions");
            return;
        }

    }

}
