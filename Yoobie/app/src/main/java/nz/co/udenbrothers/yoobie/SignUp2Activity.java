package nz.co.udenbrothers.yoobie;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.entities.Country;
import nz.co.udenbrothers.yoobie.entities.Region;
import nz.co.udenbrothers.yoobie.serverObjects.LoginToken;
import nz.co.udenbrothers.yoobie.serverObjects.RegisterData;
import nz.co.udenbrothers.yoobie.services.DownloadService;
import nz.co.udenbrothers.yoobie.services.ProfileService;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.Match;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;
import nz.co.udenbrothers.yoobie.wigets.WaveView;
import nz.co.udenbrothers.yoobie.wigets.YoobieInput;
import nz.co.udenbrothers.yoobie.wigets.YoobieSpinner;

public class SignUp2Activity extends RootActivity {

    private YoobieInput nameIn, phoneIn, dobIn;
    private YoobieSpinner regionSpin;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        myCalendar = Calendar.getInstance();
        myCalendar.set(Calendar.YEAR, 1999);
        myCalendar.set(Calendar.MONTH, 9);
        myCalendar.set(Calendar.DAY_OF_MONTH, 9);

        TextView mail = findViewById(R.id.emailTxt);
        mail.setText(Profile.mail());
        RegisterData regData = new RegisterData();
        regData.deviceModel = Build.MANUFACTURER + " " + android.os.Build.MODEL;
        regData.email = Profile.mail();
        regData.password = Profile.pass();

        YoobieSpinner countrySpin = findViewById(R.id.inputCountry);
        regionSpin = findViewById(R.id.inputRegion);
        List<Country> countries = Sql.get(Country.class);
        List<String> counNames = new ArrayList<>();
        for (Country country: countries){
            counNames.add(country.name);
        }
        countrySpin.init(counNames);
        countrySpin.selected(i ->{
            regData.countryId = countries.get(i).id;
            List<Region> regions = Sql.get(Region.class, r -> r.countryId == countries.get(i).id);
            List<String> regNames = new ArrayList<>();
            for (Region region: regions){
                regNames.add(region.name);
            }
            regionSpin.init(regNames);
            regionSpin.selected(ii -> regData.countryRegionId = regions.get(ii).id);
        });

        dobIn = findViewById(R.id.inputDOB);
        clicked(dobIn, v -> {
            DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                dobIn.setText(sdf.format(myCalendar.getTime()));
            };
            new DatePickerDialog(v.getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        YoobieSpinner genderSpin = findViewById(R.id.inputGender);
        genderSpin.init(new String[]{"Male", "Female"});
        genderSpin.selected(i -> regData.gender = i);
        nameIn = findViewById(R.id.inputName);
        phoneIn = findViewById(R.id.inputPhone);

        clicked(R.id.signUpButton, v -> {
            String fullName = nameIn.getText();
            if(fullName.equals("")){
                nameIn.error("Please enter your name");
                return;
            }
            String[] nameSplit = fullName.split("\\s+");
            regData.firstName = nameSplit[0];
            if(nameSplit.length > 1) regData.lastName = nameSplit[1];

            if(!Match.phone(phoneIn.getText())){
                phoneIn.error("Invalid phone number");
                return;
            }
            regData.phoneMobile = phoneIn.getText();

            if(dobIn.getText().equals("")){
                dobIn.error("Please enter birthday");
                return;
            }
            regData.dateOfBirth = dobIn.getText();

            RequestTask requestTask = new RequestTask(this, Url.SIGN_UP, null);
            requestTask.onSuccess(r->{
                LoginToken loginToken = Json.from(r.content,LoginToken.class);
                Profile.userID(loginToken.userId);
                try {
                    Profile.token(Base64.encodeToString((loginToken.userId + ":" + loginToken.accessToken).getBytes("UTF-8"), Base64.NO_WRAP));
                    initService(ProfileService.class);
                    initService(DownloadService.class);
                    clearActivity(MainActivity.class);
                } catch (UnsupportedEncodingException e) {
                    Log.e("Error Base64",e+"");
                    alert("Data fetching error");
                }
            });
            requestTask.onError(r -> alert(r.statusCode + " error!"));
            requestTask.onFail(r-> alert("Error! Server problem"));
            requestTask.send(regData.toJson());
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        WaveView waveView = findViewById(R.id.wave);
        delay(300, () -> waveView.setProgress(0.6f));
    }

}
