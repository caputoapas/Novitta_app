package com.example.caputo.app_novitta;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

// TELA PRINCIPAL EXPOSITOR/VISITANTE

public class MainActivity extends AppCompatActivity {
    private ImageView imagem;

    //atributo da classe Alerta.
    public AlertDialog alerta;
    int rLimpa;

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
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                /** irá remover todos os registros **/
//                db.execSQL(String.format("DELETE FROM ", "votacao"));
//                db.execSQL("VACUUM");

                Toast.makeText(MainActivity.this, "Limpeza Concluida", Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "Limpeza Cancelada", Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

}
