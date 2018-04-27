package com.example.caputo.app_novitta;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

//TELA DE RELATORIOS

public class Main3Activity extends AppCompatActivity {
    //atributo da classe Alerta
    public AlertDialog alerta;

    //variaveis de acesso ao banco
    Main2Activity.Pesquisa _pesquisa;
    String selection = "";

    //List do expositores
    List<Main2Activity.Pesquisa> Expositores = new ArrayList<>();

    //List dos visitantes
    List<Main2Activity.Pesquisa> Visitantes = new ArrayList<>();

    //grid data1
    public TextView msatisfeitoR1; int mS1 = 0;
    public TextView satisfeitoR1; int s1 = 0;
    public TextView neutroR1; int n1 = 0;
    public TextView insatisfeitoR1; int ins1 = 0;
    public TextView minsatisfeitoR1; int mIns1 = 0;

    public TextView msatisfeitoR2; int mS2 = 0;
    public TextView satisfeitoR2; int s2 = 0;
    public TextView neutroR2; int n2 = 0;
    public TextView insatisfeitoR2; int ins2 = 0;
    public TextView minsatisfeitoR2; int mIns2 = 0;

    //grid data2
    public TextView msatisfeitoR3; int mS3 = 0;
    public TextView satisfeitoR3; int s3 = 0;
    public TextView neutroR3; int n3 = 0;
    public TextView insatisfeitoR3; int ins3 = 0;
    public TextView minsatisfeitoR3; int mIns3 = 0;

    public TextView msatisfeitoR4; int mS4 = 0;
    public TextView satisfeitoR4; int s4 = 0;
    public TextView neutroR4; int n4 = 0;
    public TextView insatisfeitoR4; int ins4 = 0;
    public TextView minsatisfeitoR4; int mIns4 = 0;

    //grid data3
    public TextView msatisfeitoR5; int mS5 = 0;
    public TextView satisfeitoR5; int s5 = 0;
    public TextView neutroR5; int n5 = 0;
    public TextView insatisfeitoR5; int ins5 = 0;
    public TextView minsatisfeitoR5; int mIns5 = 0;

    public TextView msatisfeitoR6; int mS6 = 0;
    public TextView satisfeitoR6; int s6 = 0;
    public TextView neutroR6; int n6 = 0;
    public TextView insatisfeitoR6; int ins6 = 0;
    public TextView minsatisfeitoR6; int mIns6 = 0;


    public TextView data1; String d1="";
    public TextView data2; String d2="";
    public TextView data3; String d3="";

    public ImageView imagem;

    public EditText data11;

    @RequiresApi(api = VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        msatisfeitoR1 = (TextView) findViewById(R.id.msatisfeitoR1);
        msatisfeitoR2 = (TextView) findViewById(R.id.msatisfeitoR2);
        msatisfeitoR3 = (TextView) findViewById(R.id.msatisfeitoR3);
        msatisfeitoR4 = (TextView) findViewById(R.id.msatisfeitoR4);
        msatisfeitoR5 = (TextView) findViewById(R.id.msatisfeitoR5);
        msatisfeitoR6 = (TextView) findViewById(R.id.msatisfeitoR6);

        satisfeitoR1 = (TextView) findViewById(R.id.satisfeitoR1);
        satisfeitoR2 = (TextView) findViewById(R.id.satisfeitoR2);
        satisfeitoR3 = (TextView) findViewById(R.id.satisfeitoR3);
        satisfeitoR4 = (TextView) findViewById(R.id.satisfeitoR4);
        satisfeitoR5 = (TextView) findViewById(R.id.satisfeitoR5);
        satisfeitoR6 = (TextView) findViewById(R.id.satisfeitoR6);

        neutroR1 = (TextView) findViewById(R.id.neutroR1);
        neutroR2 = (TextView) findViewById(R.id.neutroR2);
        neutroR3 = (TextView) findViewById(R.id.neutroR3);
        neutroR4 = (TextView) findViewById(R.id.neutroR4);
        neutroR5 = (TextView) findViewById(R.id.neutroR5);
        neutroR6 = (TextView) findViewById(R.id.neutroR6);

        insatisfeitoR1 = (TextView) findViewById(R.id.insatisfeitoR1);
        insatisfeitoR2 = (TextView) findViewById(R.id.insatisfeitoR2);
        insatisfeitoR3 = (TextView) findViewById(R.id.insatisfeitoR3);
        insatisfeitoR4 = (TextView) findViewById(R.id.insatisfeitoR4);
        insatisfeitoR5 = (TextView) findViewById(R.id.insatisfeitoR5);
        insatisfeitoR6 = (TextView) findViewById(R.id.insatisfeitoR6);

        minsatisfeitoR1 = (TextView) findViewById(R.id.minsatisfeitoR1);
        minsatisfeitoR2 = (TextView) findViewById(R.id.minsatisfeitoR2);
        minsatisfeitoR3 = (TextView) findViewById(R.id.minsatisfeitoR3);
        minsatisfeitoR4 = (TextView) findViewById(R.id.minsatisfeitoR4);
        minsatisfeitoR5 = (TextView) findViewById(R.id.minsatisfeitoR5);
        minsatisfeitoR6 = (TextView) findViewById(R.id.minsatisfeitoR6);

        //data1 = (TextView) findViewById(R.id.data1);
        data2 = (TextView) findViewById(R.id.data2);
        data3 = (TextView) findViewById(R.id.data3);

        data11 = (EditText) findViewById(R.id.data11) ;

        d1 = data11.getText().toString();
        d2 = data2.getText().toString();
        d3 = data3.getText().toString();

        imagem = (ImageView) findViewById(R.id.logo);

        imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LimparView();
                fillView();
            }
        });
        LimparView();
        fillAllLists();
    }

    @RequiresApi(api = VERSION_CODES.M)
    public void fillView(){

        String d1 = String.valueOf(data11.getText());
        String d2 = String.valueOf(data2.getText());
        String d3 = String.valueOf(data3.getText());

        viewExpositores(d1,d2,d3);
        viewVisitantes(d1,d2,d3);
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = VERSION_CODES.M)
    public void viewExpositores(String d1, String d2, String d3){

        Expositores.forEach(n -> { if(n.getData().equals(d1)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS1++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s1++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n1++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins1++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns1++;
            }
        }
        });

        Expositores.forEach(n -> { if(n.getData().equals(d2)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS3++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s3++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n3++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins3++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns3++;
            }
        }
        });

        Expositores.forEach(n -> { if(n.getData().equals(d3)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS5++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s5++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n5++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins5++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns5++;
            }
        }
        });

        msatisfeitoR1.setText(String.valueOf(mS1));
        satisfeitoR1.setText(String.valueOf(s1));
        neutroR1.setText(String.valueOf(n1));
        insatisfeitoR1.setText(String.valueOf(ins1));
        minsatisfeitoR1.setText(String.valueOf(mIns1));

        msatisfeitoR3.setText(String.valueOf(mS3));
        satisfeitoR3.setText(String.valueOf(s3));
        neutroR3.setText(String.valueOf(n3));
        insatisfeitoR3.setText(String.valueOf(ins3));
        minsatisfeitoR3.setText(String.valueOf(mIns3));

        msatisfeitoR5.setText(String.valueOf(mS5));
        satisfeitoR5.setText(String.valueOf(s5));
        neutroR5.setText(String.valueOf(n5));
        insatisfeitoR5.setText(String.valueOf(ins5));
        minsatisfeitoR5.setText(String.valueOf(mIns5));
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = VERSION_CODES.M)
    public void viewVisitantes(String d1, String d2, String d3){

        Visitantes.forEach(n -> { if(n.getData().equals(d1)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS2++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s2++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n2++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins2++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns2++;
            }
        }
        });

        Visitantes.forEach(n -> { if(n.getData().equals(d2)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS4++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s4++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n4++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins4++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns4++;
            }
        }
        });

        Visitantes.forEach(n -> { if(n.getData().equals(d3)) {
            if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_satisfeito)){
                mS6++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.satisfeito)){
                s6++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.neutro)){
                n6++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.insatisfeito)){
                ins6++;
            }else if(n.getSatisfacao().equals(Main2Activity.TipoSatisfacao.muito_insatisfeito)){
                mIns6++;
            }
        }
        });

        msatisfeitoR2.setText(String.valueOf(mS2));
        satisfeitoR2.setText(String.valueOf(s2));
        neutroR2.setText(String.valueOf(n2));
        insatisfeitoR2.setText(String.valueOf(ins2));
        minsatisfeitoR2.setText(String.valueOf(mIns2));

        msatisfeitoR4.setText(String.valueOf(mS4));
        satisfeitoR4.setText(String.valueOf(s4));
        neutroR4.setText(String.valueOf(n4));
        insatisfeitoR4.setText(String.valueOf(ins4));
        minsatisfeitoR4.setText(String.valueOf(mIns4));

        msatisfeitoR6.setText(String.valueOf(mS6));
        satisfeitoR6.setText(String.valueOf(s6));
        neutroR6.setText(String.valueOf(n6));
        insatisfeitoR6.setText(String.valueOf(ins6));
        minsatisfeitoR6.setText(String.valueOf(mIns6));
    }

    @RequiresApi(api = VERSION_CODES.M)
    public void fillAllLists(){
        fillList(1); //expositor
        fillList(2); //visitante
    }

    @RequiresApi(api = VERSION_CODES.M)
    public void fillList(int tipo){
        PostDbHelper mDbHelper = new PostDbHelper(getBaseContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //variaveis que vai retornar da tabela
        String[] projection = {
                PostContract.PostEntry._ID,
                PostContract.PostEntry.COLUMN_NAME_TIPO,
                PostContract.PostEntry.COLUMN_NAME_VOTO,
                PostContract.PostEntry.COLUMN_NAME_DATA
        };

        //clausula where
        EscolheSelect(tipo);

        //busca dados no banco
        Cursor c = db.query(
                PostContract.PostEntry.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        //for each do cursor
        while(!c.isAfterLast()){
            _pesquisa = fillPesquisa(c);
            EscolheLista(tipo);
            c.moveToNext();
        }
    }

    public void EscolheSelect(int tipo){
        switch (tipo){
            case 1:
                selection = PostContract.PostEntry.COLUMN_NAME_TIPO + "='expositor'";
                break;
            case 2:
                selection = PostContract.PostEntry.COLUMN_NAME_TIPO + "='visitante'";
                break;
        }
    }

    @RequiresApi(api = VERSION_CODES.M)
    public void EscolheLista(final int tipo){
        switch (tipo){
            case 1:
                Expositores.add(_pesquisa);
                break;
            case 2:
                Visitantes.add(_pesquisa);
                break;
        }
    }

    public void LimparView(){
        msatisfeitoR1.setText(""); mS1 = 0;
        msatisfeitoR2.setText(""); mS2 = 0;
        msatisfeitoR3.setText(""); mS3 = 0;
        msatisfeitoR4.setText(""); mS4 = 0;
        msatisfeitoR5.setText(""); mS5 = 0;
        msatisfeitoR6.setText(""); mS6 = 0;

        satisfeitoR1.setText(""); s1 = 0;
        satisfeitoR2.setText(""); s2 = 0;
        satisfeitoR3.setText(""); s3 = 0;
        satisfeitoR4.setText(""); s4 = 0;
        satisfeitoR5.setText(""); s5 = 0;
        satisfeitoR6.setText(""); s6 = 0;

        neutroR1.setText(""); n1 = 0;
        neutroR2.setText(""); n2 = 0;
        neutroR3.setText(""); n3 = 0;
        neutroR4.setText(""); n4 = 0;
        neutroR5.setText(""); n5 = 0;
        neutroR6.setText(""); n6 = 0;

        insatisfeitoR1.setText(""); ins1 = 0;
        insatisfeitoR2.setText(""); ins2 = 0;
        insatisfeitoR3.setText(""); ins3 = 0;
        insatisfeitoR4.setText(""); ins4 = 0;
        insatisfeitoR5.setText(""); ins5 = 0;
        insatisfeitoR6.setText(""); ins6 = 0;

        minsatisfeitoR1.setText(""); mIns1 = 0;
        minsatisfeitoR2.setText(""); mIns2 = 0;
        minsatisfeitoR3.setText(""); mIns3 = 0;
        minsatisfeitoR4.setText(""); mIns4 = 0;
        minsatisfeitoR5.setText(""); mIns5 = 0;
        minsatisfeitoR6.setText(""); mIns6 = 0;

    }

    public void LimparList(){
        Expositores.clear();
        Visitantes.clear();
    }

    private Main2Activity.Pesquisa fillPesquisa(Cursor c) {
        Main2Activity.Pesquisa pegaDadosCursor = new Main2Activity.Pesquisa();

        String tipoPessoa = c.getString(1);
        String tipoSatisfacao = c.getString(2);

        pegaDadosCursor.set_id(c.getInt(0));
        pegaDadosCursor.setTipos(ConverterDadosPesquisaPessoa(tipoPessoa));
        pegaDadosCursor.setSatisfacao(ConverterDadosPesquisaSatisfacao(tipoSatisfacao));
        pegaDadosCursor.setData(c.getString(3));

        return pegaDadosCursor;
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

        }
        if (id == R.id.action_limpar) {
            LimparDados();
        }
        return super.onOptionsItemSelected(item);
    }

    private void LimparDados() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Limpar Dados");
        //define a mensagem
        builder.setMessage("Deseja Limpar todos os Dados ?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                PostDbHelper mDbHelper = new PostDbHelper(getBaseContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                /** irá remover todos os registros **/
                db.execSQL(String.format("DELETE FROM votacao"));
                db.execSQL("VACUUM");
                LimparList();
                LimparView();

                Toast.makeText(Main3Activity.this, "Limpeza Concluida", Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(Main3Activity.this, "Limpeza Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();

    }

}
