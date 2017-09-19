package br.com.jamilsonjunior.previsaodotempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AdicionarCidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_cidade);
    }

    public void adicionarCidade(View view) {
        EditText et_cidade = findViewById(R.id.et_cidade);
        EditText et_estado = findViewById(R.id.et_estado);
        Cidade cidade = new Cidade(et_cidade.getText().toString(), et_estado.getText().toString());
        cidade.save();
        finish();
    }
}
