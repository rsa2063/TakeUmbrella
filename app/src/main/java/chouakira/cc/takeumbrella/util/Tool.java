package chouakira.cc.takeumbrella.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import chouakira.cc.takeumbrella.entity.Forecast;

/**
 * Created by rsa on 2017/8/16.
 */

public class Tool {

    public static void ShowArrayMap(List list) {
        int i = 0;
        for(Object object : list) {
            Log.e(Const.TAG, object.toString());
        }
    }

    public static void ShowFileContent(File file) {
        if(file == null || !file.exists() || file.length() > 1024 * 1024 * 5) {
            return;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null) {
                Log.i(Const.TAG, line);
            }
        } catch(Exception ex) {

        }

    }

    public static Date setTodayTime(Date today) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        if(calendar.get(Calendar.HOUR_OF_DAY) > 12) {
            calendar.add(Calendar.DATE, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        today.setTime(calendar.getTimeInMillis());

        Log.e(Const.TAG, "setTodayTime: " + today);
        return today;
    }

    public static int searchForecastArrays(List list, Date date) {
        int result = -1;
        for(int i = 0; i < list.size(); i++) {
            if(((Forecast)list.get(i)).getTime().equals(date)) {
                result = i;
                break;
            }
        }

        return result;
    }

    public static void Test() {
//        java.io.Files
    }
}
