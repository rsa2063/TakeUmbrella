package chouakira.cc.takeumbrella.util;

import android.util.ArrayMap;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import chouakira.cc.takeumbrella.entity.Forecast;
import chouakira.cc.takeumbrella.enumcode.WeatherCode;

/**
 * Created by rsa on 2017/8/9.
 */

public class XMLParser {

    public static List getWeatherForecast(InputStream is) {
        List<Forecast> list = new ArrayList<Forecast>();

        //https://stackoverflow.com/questions/4138754/getting-an-attribute-value-in-xml-element
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(is);
            NodeList nodeList = document.getElementsByTagName("time");
            for(int x = 0, size = nodeList.getLength(); x < size; x++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                String symbol = nodeList.item(x).getFirstChild().getAttributes().getNamedItem("name").getNodeValue();
                Log.e(Const.TAG, "getWeatherForecast: node is " + nodeList.getLength());

                Forecast forecast = new Forecast();
                forecast.setTime(sdf.parse(nodeList.item(x).getAttributes().getNamedItem("from").getNodeValue()));
                forecast.setWeather((symbol.contains("rain") && !symbol.contains("light")) ? WeatherCode.Rain : WeatherCode.NotRain);

                list.add(forecast);
            }
        } catch(Exception ex) {
            Log.e(Const.TAG, "getWeatherForecast: Exception " + ex.toString());
        }


        return list;
    }
}
