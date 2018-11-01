package nz.co.udenbrothers.yoobie.services;

import android.app.IntentService;
import android.content.Intent;

import nz.co.udenbrothers.yoobie.serverObjects.ProfileToken;
import nz.co.udenbrothers.yoobie.serverObjects.Response;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.RequestTask;

public class ProfileService extends IntentService {

    public ProfileService() {
        super("ProfileService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Response response = RequestTask.myHttpConnection(null, Url.GET_PROFILE, Profile.token());
        if(response.statusCode >= 400) return;
        ProfileToken profileToken = Json.from(response.content, ProfileToken.class);
        Profile.name(profileToken.firstName + " " + profileToken.lastName);
        if(profileToken.gender == 0) Profile.gender("Male");
        else Profile.gender("Female");
        Profile.phone(profileToken.phoneMobile);
        Profile.dateOfBirth(profileToken.dateOfBirth);
    }
}
