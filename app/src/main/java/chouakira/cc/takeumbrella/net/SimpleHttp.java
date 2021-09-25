package chouakira.cc.takeumbrella.net;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import chouakira.cc.takeumbrella.common.ContextHolder;
import chouakira.cc.takeumbrella.util.Const;
import chouakira.cc.takeumbrella.util.Tool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by rsa on 2017/8/9.
 */

public class SimpleHttp {

    static OkHttpClient client = new OkHttpClient();

    public static String getXMLData(String url) {
        Request req = new Request.Builder().url(url).build();
        Response resp = null;
        ResponseBody body = null;
        try {
            resp = client.newCall(req).execute();
            body = resp.body();

            if(body == null) {
                return "";
            } else {
                return body.string();
            }
        } catch(IOException ex) {

        }

        return "";
    }

    public static InputStream getStreamXMLData(String url) {
        Request req = new Request.Builder().url(url).build();
        try(Response resp = client.newCall(req).execute()) {
            ResponseBody body = resp.body();

            FileOutputStream out = new FileOutputStream(new File(ContextHolder.getContext().getCacheDir(), "a.xml"));
            out.write(body.string().getBytes());
            out.close();

            Tool.ShowFileContent(new File(ContextHolder.getContext().getCacheDir(), "a.xml"));

            if(body == null) {
                return null;
            } else {
                return body.byteStream();
            }
        } catch(Exception ex) {
            Log.e(Const.TAG, "getStreamXMLData: IOException " + ex.toString());
        }

        return null;
    }
}
