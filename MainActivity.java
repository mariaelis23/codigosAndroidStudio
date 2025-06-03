package com.example.provaterca;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText descricao;
    private EditText valor;
    private Button salvar;
    private TextView informacoes;
    private MyDatabase db;
    private int valorTotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        descricao = findViewById(R.id.editTextdescricao);
        valor = findViewById(R.id.editTextValor);
        salvar = findViewById(R.id.buttonSalvar);
        informacoes = findViewById(R.id.textViewInformacoes);
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, name:"meusgastos.db").build();

        salvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salvarGasto();
            }
        });
    }

    private void salvarGasto(){
        String descricaoInformada = descricao.getText().toString();
        float valorInformado = Float.parseFloat(valor.getText().toString());
        Gasto gasto = new Gasto(0, descricaoInformada, valorInformado);

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.gastoDAO().insert(gasto);
                List<Gasto> listaGastos = db.gastoDAO().buscaTodosGastos();

                for (Gasto gastoAtual: listaGastos){
                    valorTotal += gastoAtual.getValor();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        descricao.setText("");
                        valor.setText("");
                        Toast.makeText(MainActivity.this, "Gasto Cadastrado", Toast.LENGTH_SHORT);
                        informacoes.setText("Você gastou até o momento: " + valorTotal);
                    }
                });
            }
        }).start();


    }
}
