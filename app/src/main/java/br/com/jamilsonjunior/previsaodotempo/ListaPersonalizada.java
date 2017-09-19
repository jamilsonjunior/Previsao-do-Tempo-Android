package br.com.jamilsonjunior.previsaodotempo;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jamilson on 18/09/2017.
 */

public class ListaPersonalizada extends BaseAdapter {

    private final List<Forecast> forecasts;
    private final Activity activity;

    public ListaPersonalizada(List<Forecast> forecasts, Activity activity) {
        this.forecasts = forecasts;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return forecasts.size();
    }

    @Override
    public Object getItem(int i) {
        return forecasts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = activity.getLayoutInflater().inflate(R.layout.lista_previsao, viewGroup, false);
        Forecast forecast = forecasts.get(i);

        TextView tv_dia = view1.findViewById(R.id.tv_data);
        TextView tv_diadasemana = view1.findViewById(R.id.tv_diadasemana);
        TextView tv_descricao = view1.findViewById(R.id.tv_descricao);
        TextView tv_max = view1.findViewById(R.id.tv_max);
        TextView tv_min = view1.findViewById(R.id.tv_min);

        tv_dia.setText(forecast.getDate());
        tv_diadasemana.setText(forecast.getWeekday());
        tv_descricao.setText(forecast.getDescription());
        tv_max.setText("Max: " + forecast.getMax() + "ºC");
        tv_min.setText("Min: " + forecast.getMin() + "ºC");

        return view1;
    }
}
