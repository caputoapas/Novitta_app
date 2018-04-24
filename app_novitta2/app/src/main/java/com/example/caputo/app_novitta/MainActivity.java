package com.example.caputo.app_novitta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

// TELA PRINCIPAL EXPOSITOR/VISITANTE

public class MainActivity extends AppCompatActivity {
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imagem = (ImageView) findViewById(R.id.expositor);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaVotoE();
            }
        });
        imagem = (ImageView) findViewById(R.id.visitante);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaVotoV();
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

    public void paginaVotoE(){
        String nome = "EXPOSITOR";
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        Bundle parametros = new Bundle();
        parametros.putString("chave_nome", nome);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    public void paginaVotoV(){
        String nome = "VISITANTE";
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        Bundle parametros = new Bundle();
        parametros.putString("chave_nome", nome);
        intent.putExtras(parametros);
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
