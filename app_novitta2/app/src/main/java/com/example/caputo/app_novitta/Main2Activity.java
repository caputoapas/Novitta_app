package com.example.caputo.app_novitta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private ImageView imagem;
    private Button voltar;
    private TextView tipoVoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tipoVoto = (TextView)findViewById(R.id.Tipo);
        Intent intent_v = getIntent();
        Bundle parametro = intent_v.getExtras();
        if(parametro != null)
        {
            String tipo = parametro.getString("chave_nome");
            //Toast.makeText(Main2Activity.this, tipo, Toast.LENGTH_SHORT).show();
            tipoVoto.setText(tipo);

        }

        voltar = (Button)findViewById(R.id.butonVoltar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaInicial();
            }
        });

        imagem = (ImageView) findViewById(R.id.satisfeito);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaInicial();
            }
        });
        imagem = (ImageView) findViewById(R.id.insatisfeito);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaInicial();
            }
        });
        imagem = (ImageView) findViewById(R.id.neutro);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaInicial();
            }
        });

    }
    public void paginaInicial(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void paginaRelatorio(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
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
        if (id == R.id.action_pesquisa) {

            paginaInicial();
        }
        if (id == R.id.action_relatorio) {
            paginaRelatorio();
        }
        if (id == R.id.action_limpar) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
