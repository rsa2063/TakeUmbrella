package chouakira.cc.takeumbrella;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chouakira.cc.takeumbrella.entity.Forecast;
import chouakira.cc.takeumbrella.enumcode.WeatherCode;
import chouakira.cc.takeumbrella.util.Const;
import chouakira.cc.takeumbrella.util.OpenWeatherMapGetter;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.obj == WeatherCode.NotRain) {
                display.setText("not rain");
            } else {
                display.setText("rain");
            }
        }
    };

    private TextView display = null;
    private RecyclerView weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        display = (TextView) findViewById(R.id.display);

        Thread thread = new Thread();

        new Thread() {
            @Override
            public void run() {
                OpenWeatherMapGetter weatherGetter = new OpenWeatherMapGetter();
                Message msg = new Message();
                msg.obj = weatherGetter.getWeather();
                handler.sendMessage(msg);
            }
        }.start();

        weatherList = (RecyclerView)findViewById(R.id.weather_list);
        weatherList.setLayoutManager(new LinearLayoutManager(this));

        //TODO new List<>; new Adapter; setAdapter

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Context getActivity() {
        return this;
    }

    class ForecastHolder extends RecyclerView.ViewHolder {

        private Forecast forecast;

        public TextView date;
        public TextView weather;

        public ForecastHolder(View itemView) {
            super(itemView);

            date = (TextView)itemView.findViewById(R.id.list_item_forecast_date);
            weather = (TextView)itemView.findViewById(R.id.list_item_forecast_weather);
        }

        public void bind(Forecast forecast) {
            this.forecast = forecast;
            date.setText(Const.sdf.format(this.forecast.getTime()));
            weather.setText(this.forecast.getWeather().getLabel());
        }
    }

    class ForecastAdapter extends RecyclerView.Adapter<ForecastHolder> {

        private List<Forecast> forecasts;

        public ForecastAdapter(List<Forecast> forecasts) {
            this.forecasts = forecasts;
        }

        @Override
        public ForecastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_forecast, parent, false);
            return new ForecastHolder(view);
        }

        @Override
        public void onBindViewHolder(ForecastHolder holder, int position) {
            Forecast forecast = forecasts.get(position);
            holder.bind(forecast);
        }

        @Override
        public int getItemCount() {
            return forecasts.size();
        }
    }
}
