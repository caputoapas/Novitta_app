package com.example.caputo.app_novitta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
    private ImageView imagem;
    private Button voltar;
    private TextView tipoVoto;
    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    BancoController crud = new BancoController(getBaseContext());

   final List<Pesquisa> Pessoas = new ArrayList<Pesquisa>();

    TipoPessoa tp = null;

    public enum TipoPessoa{
        expositor,
        visitante
    }

    public enum TipoSatisfacao{
        satisfeito,
        neutro,
        insatisfeito
    }

    public class Pesquisa{
        public TipoPessoa tipos;
        public TipoSatisfacao satisfacao;
        public String data;

        public TipoSatisfacao getSatisfacao() {
            return satisfacao;
        }

        public void setSatisfacao(TipoSatisfacao satisfacao) {
            this.satisfacao = satisfacao;
        }

        public TipoPessoa getTipos() {
            return tipos;
        }

        public void setTipos(TipoPessoa tipos) {
            this.tipos = tipos;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent_v = getIntent();
        Bundle parametro = intent_v.getExtras();

        String r = parametro.getString("chave_nome");

        if (r.equals("EXPOSITOR")) {
            tp = TipoPessoa.expositor;
        }else if (r.equals("VISITANTE"))
            tp = TipoPessoa.visitante;
        else
            paginaInicial();

        tipoVoto = (TextView)findViewById(R.id.Tipo);

        if(parametro != null) {
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
                Satisfeito(v);
            }
        });

        imagem = (ImageView) findViewById(R.id.insatisfeito);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insatisfeito(v);
            }
        });

        imagem = (ImageView) findViewById(R.id.neutro);
        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Neutro(v);
            }
        });
    }

    public void Satisfeito (View v){
        String tipo = String.valueOf(tp);
        String voto = String.valueOf(TipoSatisfacao.satisfeito);
        String data = formataData.format(date);

        String resultado = crud.insereDado(tipo,voto,data);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
//        Pesquisa p = new Pesquisa();
//
//        p.setSatisfacao(TipoSatisfacao.satisfeito);
//        p.setTipos(tp);
//        p.setData(formataData.format(date));
//
//        Pessoas.add(p);

        paginaInicial();
    }

    public void Insatisfeito(View v){
        String tipo = String.valueOf(tp);
        String voto = String.valueOf(TipoSatisfacao.satisfeito);
        String data = formataData.format(date);

        String resultado = crud.insereDado(tipo,voto,data);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

        paginaInicial();
    }

    public void Neutro(View v){
        String tipo = String.valueOf(tp);
        String voto = String.valueOf(TipoSatisfacao.satisfeito);
        String data = formataData.format(date);

        String resultado = crud.insereDado(tipo,voto,data);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

        paginaInicial();
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
