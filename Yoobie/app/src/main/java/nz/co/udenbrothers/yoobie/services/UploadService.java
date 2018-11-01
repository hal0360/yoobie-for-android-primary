package nz.co.udenbrothers.yoobie.services;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import nz.co.udenbrothers.yoobie.entities.Stamp;
import nz.co.udenbrothers.yoobie.serverObjects.Response;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;

public class UploadService extends IntentService {

    public UploadService() {
        super("UploadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Stamp> stamps = Sql.get(Stamp.class);
        Response response = RequestTask.myHttpConnection(Json.to(stamps), Url.GET_CAMPAIGN, Profile.token());
        if(response.statusCode >= 200 && response.statusCode < 300) Sql.clear(Stamp.class);
    }

}
