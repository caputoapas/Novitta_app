package com.example.caputo.app_novitta;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

//TELA DE RELATORIOS

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List lista = new ArrayList();

        PostDbHelper mDbHelper = new PostDbHelper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //variaveis que vai retornar
        String[] projection = {
                PostContract.PostEntry._ID,
                PostContract.PostEntry.COLUMN_NAME_TIPO,
                PostContract.PostEntry.COLUMN_NAME_VOTO,
                PostContract.PostEntry.COLUMN_NAME_DATA
        };

        //clausula where
        String selection = PostContract.PostEntry.COLUMN_NAME_TIPO + "='expositor' AND "
                +  PostContract.PostEntry.COLUMN_NAME_VOTO + "='satisfeito'";

        //argumeos da clausula where
        //String[] selectionArgs = { PostContract.PostEntry.COLUMN_NAME_TIPO+"='expositor'", PostContract.PostEntry.COLUMN_NAME_VOTO +"='satisfeito'" };

        //ordenacao dos dados
        String sortOrder =
                PostContract.PostEntry.COLUMN_NAME_DATA+" DESC";

        Cursor c = db.query(
                PostContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                sortOrder);

        c.moveToFirst();

        while(!c.isAfterLast()){
            Main2Activity.Pesquisa p = fillPesquisa(c);
            lista.add(p);
            c.moveToNext();
        }



        long itemId = c.getLong(
                c.getColumnIndexOrThrow(PostContract.PostEntry.COLUMN_NAME_TIPO)
        );
    }

    public void paginaRelatorio(){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    private Main2Activity.Pesquisa fillPesquisa(Cursor c) {
        Main2Activity.Pesquisa p = new Main2Activity.Pesquisa();
        String tP = c.getString(1);
        String tS = c.getString(2);

        p.set_id(c.getInt(0));
        p.setTipos(ConverterDadosPesquisaPessoa(tP));
        p.setSatisfacao(ConverterDadosPesquisaSatisfacao(tS));
        p.setData(c.getString(3));
        String t = c.getString(1);
       // c.close();
        return p;
    }

    public Main2Activity.TipoSatisfacao ConverterDadosPesquisaSatisfacao(String tipoSatisfacao){
        Main2Activity.Pesquisa _p = new Main2Activity.Pesquisa();

        switch (tipoSatisfacao) {
            case "muito_satisfeito": _p.setSatisfacao(Main2Activity.TipoSatisfacao.muito_satisfeito);
            break;
            case "satisfeito": _p.setSatisfacao(Main2Activity.TipoSatisfacao.satisfeito);
                break;
            case "neutro": _p.setSatisfacao(Main2Activity.TipoSatisfacao.neutro);
                break;
            case "insatisfeito": _p.setSatisfacao(Main2Activity.TipoSatisfacao.insatisfeito);
                break;
            case "muito_insatisfeito": _p.setSatisfacao(Main2Activity.TipoSatisfacao.muito_insatisfeito);
                break;
        }
        return _p.getSatisfacao();
    }
    public Main2Activity.TipoPessoa ConverterDadosPesquisaPessoa(String tipoPessoa){
        Main2Activity.Pesquisa _p = new Main2Activity.Pesquisa();

        switch (tipoPessoa) {
            case "expositor": _p.setTipos(Main2Activity.TipoPessoa.expositor);
                break;
            case "visitante": _p.setTipos(Main2Activity.TipoPessoa.visitante);
                break;

        }
        return _p.getTipos();
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
            finish();
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
