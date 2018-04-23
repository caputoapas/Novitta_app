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


public class Main2Activity extends AppCompatActivity {
    private ImageView imagem;
    private Button voltar;
    private TextView tipoVoto;
    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

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
        Pesquisa p = new Pesquisa();

        p.setSatisfacao(TipoSatisfacao.satisfeito);
        p.setTipos(tp);
        p.setData(formataData.format(date));

        Pessoas.add(p);
        paginaInicial();
    }

    public void Insatisfeito(View v){
        Pesquisa p = new Pesquisa();

        p.setSatisfacao(TipoSatisfacao.insatisfeito);
        p.setTipos(tp);
        p.setData(formataData.format(date));

        Pessoas.add(p);
        paginaInicial();
    }

    public void Neutro(View v){
        Pesquisa p = new Pesquisa();

        p.setSatisfacao(TipoSatisfacao.neutro);
        p.setTipos(tp);
        p.setData(formataData.format(date));

        Pessoas.add(p);
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
    class CriaBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "votacao";
    private static final String ID = "_id";
    private static final String TIPO = "tipo";
    private static final String VOTO = "voto";
    private static final String DATA = "data";
    private static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + TIPO + " text,"
                + VOTO + " text,"
                + DATA + " text"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }


}