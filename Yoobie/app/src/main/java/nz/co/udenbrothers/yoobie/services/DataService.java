package nz.co.udenbrothers.yoobie.services;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import nz.co.udenbrothers.yoobie.entities.Country;
import nz.co.udenbrothers.yoobie.entities.Region;
import nz.co.udenbrothers.yoobie.serverObjects.Response;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;


public class DataService extends IntentService {

    public DataService() {
        super("DataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Response response = RequestTask.myHttpConnection(null, Url.GET_COUNTRIES, null);
        if(response.statusCode >= 400) return;
        List<Country> countries = Json.fromList(response.content, Country.class);
        Sql.clear(Country.class);
        Sql.clear(Region.class);
        for (Country country: countries){
            Response response2 = RequestTask.myHttpConnection(null, Url.GET_REGION(country.id), null);
            List<Region> regions = Json.fromList(response2.content, Region.class);
            for (Region region: regions){
                region.countryId = country.id;
                region.save();
            }
            country.save();
        }
    }
}
