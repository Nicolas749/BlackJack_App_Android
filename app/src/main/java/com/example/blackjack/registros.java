package com.example.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class registros extends AppCompatActivity {


    TextView tituloTxt;
    Button btnDelete;
    Context context;

    ListView listRegistros;
    ImageView cartaVista;
    ImageView winView;
    ImageView loseView;
    TextView scoreDT;
    TextView scoreJT;


    private resultados mainResults = resultados.getResults();
    ArrayList<Integer> listaM2 = new ArrayList<Integer>();
    ArrayList<Integer> listaM3 = new ArrayList<Integer>();


    int [] listaBaraja;
    private RecyclerView recyList;
    private Adaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        recyList = findViewById(R.id.recyList);
        tituloTxt = findViewById(R.id.tituloTxt);
        btnDelete = findViewById(R.id.btnDelete);
       scoreDT = findViewById(R.id.scoreD);
        scoreJT = findViewById(R.id.scoreJ);
        winView = findViewById(R.id.winView);
        loseView = findViewById(R.id.loseView);

        loadScore();

        SharedPreferences sp = getSharedPreferences("cartas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = sp.edit();
        String tipo = sp.getString("tipo","");
        Log.i("tipo enviado :", tipo);
        int size = sp.getInt("size",0);
        recyList.setLayoutManager(new LinearLayoutManager(this));
        if(size == 0){
            listaM2.add(R.drawable.back);
            adapter = new Adaptador(listaM2);
            recyList.setAdapter(adapter);
        }else{
            listaM2 = mainResults.getResultsList();
            for (int i = 0; i < size ; i++) {

                //int  carta = sp.getInt("cartas" + i,R.drawable.back);
               //listaM2.add(carta);
                editor.putInt("cart"+i, listaM2.get(i));
                adapter = new Adaptador(listaM2);
                recyList.setAdapter(adapter);
            }

        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItems(listaM2,view);
            }
        });



        for (int i = 0; i < listaM2.size(); i++) {
            Log.isLoggable("Lista M2: " , listaM2.get(i));
        }
    }
    public void deleteItems(ArrayList listaM2, View view){



        if(listaM2.size() == 0){
            Toast toast = Toast.makeText(this, "No hay datos para eliminar",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else{
            AlertDialog.Builder alertDialogue1 = new AlertDialog.Builder(registros.this);
            alertDialogue1.setTitle("Warning");
            alertDialogue1.setMessage("¿Seguro que Desea Eliminar El Historico?");
            alertDialogue1.setCancelable(false);
            alertDialogue1.setPositiveButton("Aceptar", (dialogInterface, i1) -> {
                listaM2.clear();

                adapter = new Adaptador(listaM2);
                recyList.setAdapter(adapter);
                scoreDT.setText(0+"");
                scoreJT.setText(0+"");
                SharedPreferences preferencesD = getSharedPreferences("score", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor  = preferencesD.edit();
                editor.remove("score");

                editor.putInt(MainActivity.FINAL_SCORED,0);
                editor.putInt(MainActivity.FINAL_SCOREJ,0);
                editor.apply();

                Toast toast = Toast.makeText(this, "Borrado Exitosamente",
                        Toast.LENGTH_SHORT);
                toast.show();
            });
            alertDialogue1.setNegativeButton("Cancel", (dialogInterface, i12) -> {
                Toast toast = Toast.makeText(this, "Se ha cancelado",
                        Toast.LENGTH_SHORT);
                toast.show();
            });
            alertDialogue1.show();



        }

    }
    public void loadScore(){
        SharedPreferences sp = getSharedPreferences("score", Context.MODE_PRIVATE);
        int scoreD = sp.getInt(MainActivity.FINAL_SCORED,0);
        int scoreJ = sp.getInt(MainActivity.FINAL_SCOREJ,0);
        scoreDT.setText(scoreD+"");
        scoreJT.setText(scoreJ+"");


    }



}