package br.com.jamilsonjunior.previsaodotempo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ExibirPrevisao extends AppCompatActivity {

    String cidade;
    String url;
    TextView tv_cidade;
    TextView tv_temperatura;
    TextView tv_condicoesatuais;
    ListView lv_previsao;
    String CLASSE = "ExibirPrevisao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_previsao);
        Bundle extras = getIntent().getExtras();
        cidade = extras.getString("cidade");
        url = "https://api.hgbrasil.com/weather/?format=json&city_name=" + cidade + "&key=59961be7";
        //Log.i(CLASSE, url);

        tv_cidade = findViewById(R.id.tv_cidade);
        tv_temperatura = findViewById(R.id.tv_temperatura);
        tv_condicoesatuais = findViewById(R.id.tv_condicaoatual);
        lv_previsao = findViewById(R.id.lv_previsao);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PrevisaoAPI previsaoAPI = new PrevisaoAPI();
        previsaoAPI.execute(url);
    }

    private class PrevisaoAPI extends AsyncTask<String, Integer, HGWeather> {

        @Override
        protected HGWeather doInBackground(String... urls) {
            HGWeather hgWeather = null;
            try {
                String line, newjson = "";
                Log.i(CLASSE, urls[0]);
                URL endereco = new URL(urls[0]);
                BufferedReader reader = new BufferedReader(new InputStreamReader(endereco.openStream(), "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    newjson += line;
                }
                Log.i(CLASSE, newjson);
                Gson gson = new Gson();
                hgWeather = gson.fromJson(newjson, HGWeather.class);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(CLASSE, "Erro no doInBackground");
            }
            return hgWeather;
        }

        @Override
        protected void onPostExecute(HGWeather hgWeather) {
            super.onPostExecute(hgWeather);
            Results results = hgWeather.getResults();
            Log.i(CLASSE, "" + results.getCity());
            Log.i(CLASSE, "" + results.getTemp());
            tv_cidade.setText(results.getCity().replace(",", ""));
            tv_temperatura.setText(results.getTemp().toString() + "ºC");
            tv_condicoesatuais.setText(results.getDescription());

            ListaPersonalizada listaPersonalizada = new ListaPersonalizada(results.getForecast(), ExibirPrevisao.this);
            lv_previsao.setAdapter(listaPersonalizada);
        }
    }
}
