package nz.co.udenbrothers.yoobie.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import nz.co.udenbrothers.yoobie.entities.Campaign;
import nz.co.udenbrothers.yoobie.serverObjects.Response;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Url;
import nz.co.udenbrothers.yoobie.tools.Json;
import nz.co.udenbrothers.yoobie.tools.RequestTask;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;


public class DownloadService extends IntentService {

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Response response = RequestTask.myHttpConnection(null, Url.GET_CAMPAIGN, Profile.token());
        List<Campaign> campains = Json.fromList(response.content, Campaign.class);

        if(campains != null){

            File filess = getExternalFilesDir(null);
            if (filess != null) {
                File[] filenames = filess.listFiles();
                for (File tmpf : filenames) {
                    if(!tmpf.delete()) Log.e("Yoobie error","Error deleting images on external directory");
                }
            }
            Sql.clear(Campaign.class);

            String fullImgId;
            for(Campaign camp : campains) {
                try {
                    URL url = new URL(Url.GET_IMAGES(camp.id, camp.imageId));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("Authorization", "Basic " + Profile.token());
                    connection.setDoInput(true);
                    connection.connect();
                    String contentType = connection.getContentType();
                    String[] parts = contentType.split("/");
                    InputStream is = connection.getInputStream();
                    OutputStream os;
                    if(parts[1] != null) fullImgId = camp.imageId +  "." + parts[1];
                    else fullImgId = camp.imageId +  ".jpg";
                    os = new FileOutputStream(new File(getExternalFilesDir(null), fullImgId));
                    camp.fullImageId = fullImgId;
                    camp.save();
                    byte[] b = new byte[2048];
                    int length;
                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }
                    is.close();
                    os.close();
                } catch (Exception e) {
                    Log.e("Error downloding images",e+"");

                  //  Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
                  //  dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   // getApplication().startActivity(dialogIntent);
                }
            }
        }
    }
}
