package com.example.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    //Declaracion del Singleton
    private resultados mainResults = resultados.getResults();

    private ImageView imageTittle;
    private TextView titleTxt;
    private Button btnJugar;
    private Button btnRegistros;
    private ImageButton btnStand;
    private ImageButton btnHit;
    private ImageView As;
    private TextView jugadorCount;
    private TextView dealerCount;
    private ImageView cartaTrasera;
    private ImageView cartaDealer;
    private ImageView trofeoDealer;
    private ImageView trofeoJugador;

    private int countJugador = 0 ;
    private int countDealer = 0;
    private int tipoJugador = 1;

    private int sumDealer;
    private int sumJugador;
    private int IndexCartasRecycleView =0;

    public int scoreJugador = 0;
    public int scoreDealer = 0;
    public int lastIndex;
    public static final String FINAL_SCORED = "scoreDealer";
    public static final String FINAL_SCOREJ = "scoreJugadorJ";
    Context context;





    Set<Integer> set = new HashSet<Integer>();



   // private int IndexCarta;
    private static final Random rgenerador = new Random();
    private ArrayList<Integer> barajaArray = new ArrayList<>();
    private static final Integer[] cartasID = {R.drawable.ace_of_clubs, R.drawable.ace_of_diamonds,
            R.drawable.ace_of_hearts, R.drawable.ace_of_spades,
            R.drawable.two_of_clubs, R.drawable.two_of_hearts, R.drawable.two_of_spades, R.drawable.two_of_diamonds,
            R.drawable.three_of_clubs, R.drawable.three_of_diamonds, R.drawable.three_of_hearts, R.drawable.three_of_spades,
            R.drawable.four_of_spades, R.drawable.four_of_clubs, R.drawable.four_of_diamonds, R.drawable.four_of_hearts,
            R.drawable.five_of_clubs, R.drawable.five_of_diamonds, R.drawable.five_of_spades, R.drawable.five_of_hearts,
            R.drawable.six_of_clubs, R.drawable.six_of_diamonds, R.drawable.six_of_hearts, R.drawable.six_of_spades,
            R.drawable.seven_of_clubs, R.drawable.seven_of_diamonds, R.drawable.seven_of_hearts, R.drawable.seven_of_spades,
            R.drawable.eight_of_clubs, R.drawable.eight_of_diamonds, R.drawable.eight_of_hearts, R.drawable.eight_of_spades,
            R.drawable.nine_of_clubs, R.drawable.nine_of_diamonds, R.drawable.nine_of_hearts, R.drawable.nine_of_spades,
            R.drawable.ten_of_clubs, R.drawable.ten_of_diamonds, R.drawable.ten_of_hearts, R.drawable.ten_of_spades,
            R.drawable.jack_of_clubs, R.drawable.jack_of_diamonds, R.drawable.jack_of_hearts, R.drawable.jack_of_spades,
            R.drawable.queen_of_clubs, R.drawable.queen_of_diamonds, R.drawable.queen_of_hearts, R.drawable.queen_of_spades,
            R.drawable.king_of_clubs, R.drawable.king_of_diamonds, R.drawable.king_of_hearts, R.drawable.king_of_spades};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageTittle = findViewById(R.id.imageTittle);
        titleTxt = findViewById(R.id.titleTxt);


        btnHit = (ImageButton) findViewById(R.id.btnHit);
        btnStand = (ImageButton) findViewById(R.id.btnStand);
        btnJugar = (Button) findViewById(R.id.btnJugar);
        btnRegistros = (Button)findViewById(R.id.btnRegistros);
        btnHit.setVisibility(View.INVISIBLE);
        btnStand.setVisibility(View.INVISIBLE);
        jugadorCount = (TextView) findViewById(R.id.jugadorCount);
        dealerCount = (TextView) findViewById(R.id.dealerCount);
        cartaDealer = (ImageView) findViewById(R.id.cartaDealer);
        cartaDealer.setVisibility(View.INVISIBLE);
        cartaTrasera = (ImageView) findViewById(R.id.cartaTrasera);
        trofeoDealer = (ImageView) findViewById(R.id.trofeoDealer);
        trofeoJugador = (ImageView) findViewById(R.id.trofeoJugador);
        trofeoJugador.setVisibility(View.INVISIBLE);
        trofeoDealer.setVisibility(View.INVISIBLE);
        cartaTrasera.setVisibility(View.INVISIBLE);
        jugadorCount.setText(0+"");
        jugadorCount.setVisibility(View.INVISIBLE);
        dealerCount.setText(0+"");
        dealerCount.setVisibility(View.INVISIBLE);
        As = (ImageView) findViewById(R.id.As);
        As.setVisibility(View.INVISIBLE);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.move);
        readScore();
        //readData();
        btnRegistros.setVisibility(View.INVISIBLE);






        btnRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
                btnRegistros.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(MainActivity.this, registros.class);
                startActivity(intent);
                readScore();

            }
        });



        btnJugar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                imageTittle.setVisibility(View.INVISIBLE);
                titleTxt.setVisibility(View.INVISIBLE);
                starGame(view);
                btnJugar.setEnabled(false);

            }
        });



        btnStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tipoJugador  = 1;
                btnHit.setEnabled(false);
                btnHit.setVisibility(View.INVISIBLE);
                btnStand.setVisibility(View.INVISIBLE);
                btnRegistros.setEnabled(true);

                while(sumDealer < 21){
                    if(sumDealer >= 17){
                        break;
                    }else{
                        cartaTrasera.setAnimation(animation);
                        repartirDealerSegunda(randomIndex(),view);


                    }

                }
                comprobarGanador(view);

            }

        });


        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoJugador = 0;


                int res = randomIndex();
                As.setBackgroundResource(res);
                As.setVisibility(View.VISIBLE);
                As.setImageResource(res);


                tirarCartas(res,view);

                int jugador = Integer.parseInt(jugadorCount.getText().toString());
                Log.i("jugador: " , String.valueOf(jugador));
                if (jugador  > 21 ){
                    tipoJugador = 1;
                    comprobarGanador(view);
                }


            }
        });


    }


    public void guardarCarta(){

        for (int i = 0; i < barajaArray.size(); i++) {
            SharedPreferences preferences = getSharedPreferences("cartas", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor  = preferences.edit();
            Log.i("Barajas  Array: " , String.valueOf(barajaArray.get(i)));
            editor.putInt("size",barajaArray.size());
            editor.putInt("cartas" + i,barajaArray.get(i));
            editor.commit();
            IndexCartasRecycleView = i;
        }
    }

    public void guardarScore(){

        SharedPreferences preferencesD = getSharedPreferences("score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferencesD.edit();
        editor.putInt(FINAL_SCORED,scoreDealer);
        editor.putInt(FINAL_SCOREJ,scoreJugador);
        editor.apply();
    }
    public void readScore(){
        SharedPreferences preferencesD = getSharedPreferences("score", Context.MODE_PRIVATE);

        scoreDealer = preferencesD.getInt(FINAL_SCORED,scoreDealer);
        scoreJugador = preferencesD.getInt(FINAL_SCOREJ,scoreJugador);

    }
    public void readData(){
        SharedPreferences preferencesD = getSharedPreferences("score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferencesD.edit();
        for (int i = 0; i < mainResults.getResultsList().size(); i++) {
            //Log.i("ReadData: " , ""+i);
            //preferencesD.getInt("cart"+i,i);
            System.out.println(mainResults.getResultsList().get(i));
            editor.putInt("cart"+i,mainResults.getResultsList().get(i));
            editor.apply();
        }

       

    }
    public void saveResults(int ind){
        mainResults.setResult(ind);
    }



    public void comprobarGanador(View view){
        int dealer =  Integer.parseInt(dealerCount.getText().toString());
        int jugador = Integer.parseInt(jugadorCount.getText().toString());
        btnHit.setEnabled(true);
        Animation animationTrofeo1 = AnimationUtils.loadAnimation(this,R.anim.zoom_in);



        if((dealer > jugador && dealer <= 21)|| (jugador > 21)){
            btnHit.setVisibility(View.INVISIBLE);
            btnStand.setVisibility(View.INVISIBLE);
            btnRegistros.setVisibility(View.VISIBLE);
            scoreDealer =1+ scoreDealer;
            guardarScore();
            guardarCarta();
            Log.i("Score Dealer: " , String.valueOf(scoreDealer));
            btnHit.setEnabled(false);
            showToast(view ,"Gana la casa");
            trofeoDealer.setVisibility(View.VISIBLE);

            btnJugar.setVisibility(View.VISIBLE);
            btnJugar.setText("Jugar de nuevo");
            btnJugar.setEnabled(true);
            btnJugar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    resetGame(view);
                }
            });



        }else if(jugador > dealer || dealer > 21){
            scoreJugador = 1+  scoreJugador;
            btnRegistros.setVisibility(View.VISIBLE);
            guardarScore();
            guardarCarta();
            Log.i("Score Jugador: " ,""+ scoreJugador);
            btnHit.setEnabled(false);
            trofeoJugador.setVisibility(View.VISIBLE);

            showToast(view ,"Ganaste, la casa pierde");
            btnJugar.setVisibility(View.VISIBLE);
            btnJugar.setText("Jugar de nuevo ");
            btnJugar.setEnabled(true);
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetGame(view);
                }
            });


        }else{
            guardarCarta();
            btnRegistros.setVisibility(View.VISIBLE);
            trofeoJugador.setVisibility(View.INVISIBLE);
            showToast(view ,"Nadie Gana");
            btnJugar.setVisibility(View.VISIBLE);
            btnJugar.setText("Jugar de nuevo");
            btnJugar.setEnabled(true);
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetGame(view);

                }
            });


        }

    }
    public void saveTipoJugador(String tipo){
        SharedPreferences preferences = getSharedPreferences("tipoJugador", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putString("tipo",tipo);
        Log.i("tipo enviado: " ,""+ tipo);
        editor.commit();

    }
    public void manejarJuego(int indexRes, int auxCount){
        //countJugador += auxCount;
        barajaArray.add(indexRes);
        saveResults(indexRes);

        if(tipoJugador == 0){
            countJugador += auxCount;
            jugadorCount.setText(countJugador +"");
            saveTipoJugador("Carta Jugador");

        }else{Log.i("Comprobar ESTADO: ","" + countJugador);
            countDealer += auxCount;
            dealerCount.setText(countDealer +"");
            saveTipoJugador("Carta Dealer");
        }

    }

    public void showToast(View view, String mensaje) {
        Toast toast = Toast.makeText(this, mensaje,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void comprobarEstadoJugador(View view) {
        sumDealer  = Integer.parseInt(dealerCount.getText().toString());
        sumJugador = Integer.parseInt(jugadorCount.getText().toString());
        Log.i("Comprobar ESTADO Jugador: " ,""+ sumJugador);
        Log.i("Comprobar ESTADO Dealer: " ,""+ sumDealer);
        if (sumJugador > 21) {
            btnHit.setEnabled(false);
            showToast(view ,"Gana la casa");
            trofeoDealer.setVisibility(View.VISIBLE);
            btnJugar.setVisibility(View.VISIBLE);
            btnJugar.setText("Jugar de nuevo");
            btnJugar.setVisibility(View.VISIBLE);
            btnJugar.setText("Jugar de nuevo");

            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetGame(view);

                }
            });
        }else if(sumDealer > 21){
            btnHit.setEnabled(false);
            trofeoJugador.setVisibility(View.VISIBLE);
            showToast(view ,"Ganaste, la casa pierde");
            btnJugar.setText("Jugar de nuevo");
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    resetGame(view);
                }
            });
        }

    }
    public int randomIndex(){
        int res = cartasID[rgenerador.nextInt(cartasID.length)];
        if(comprobarDuplicado(res)){
            res =  randomIndex();
        }
        return res;
    }
    public boolean comprobarDuplicado(int index){
        boolean duplicado = false;
        if(barajaArray.contains(index)){
            Log.i("Duplicado index: " ,""+ index);
            duplicado = true;
        }
        return duplicado;
    }
    public  void resetGame(View view){
        btnJugar.setVisibility(View.INVISIBLE);
        barajaArray.clear();
        jugadorCount.setText(0+"");
        dealerCount.setText(0+"");
        sumJugador = 0;
        countDealer = 0;
        countJugador = 0;
        sumDealer= 0;
        cartaTrasera.setImageResource(R.drawable.back);
        trofeoDealer.setVisibility(View.INVISIBLE);
        trofeoJugador.setVisibility(View.INVISIBLE);
        btnHit.setEnabled(true);
        //As.setImageResource(R.drawable.back);
        starGame(view);
    }
    public void repartirDealerPrimera(int dealer,View view){
        comprobarEstadoJugador(view);
        Log.i("Print Dealer Primera : ",""+dealer);
        cartaDealer.setImageResource(dealer);
        tirarCartas(dealer,view);
    }
    public void repartirDealerSegunda(int dealer,View view){
        comprobarEstadoJugador(view);
        Log.i("Print Dealer Segunda: ",""+dealer);
        cartaTrasera.setImageResource(dealer);
        tirarCartas(dealer,view);
    }
    public void repartirJugador(int cartaJugador,View view){
        tipoJugador = 0;
        As.setImageResource(cartaJugador);
        comprobarEstadoJugador(view);
        Log.i("Print Jugador: ",cartaJugador+"");
        tirarCartas(cartaJugador,view);
    }
    public void starGame(View v ){

        //IndexCarta = randomIndex();

        Log.i("Jugar","Incio Juego o Repite");


        trofeoJugador.setVisibility(View.INVISIBLE);
        trofeoDealer.setVisibility(View.INVISIBLE);

        btnJugar.setVisibility(View.INVISIBLE);
        btnHit.setEnabled(true);
        jugadorCount.setVisibility(View.VISIBLE);
        dealerCount.setVisibility(View.VISIBLE);
        btnHit.setVisibility(View.VISIBLE);
        btnStand.setVisibility(View.VISIBLE);
        As.setVisibility(View.VISIBLE);
        cartaDealer.setVisibility(View.VISIBLE);
        cartaTrasera.setVisibility(View.VISIBLE);
        cartaDealer.setImageResource(R.drawable.back);

        repartirDealerPrimera(randomIndex(),v);
        repartirJugador(randomIndex(),v);






    }
    public void tirarCartas(int res, View view){

        switch (res) {
            case R.drawable.ace_of_clubs:
            case R.drawable.ace_of_diamonds:
            case R.drawable.ace_of_hearts:
            case R.drawable.ace_of_spades:
                manejarJuego(res,1);
                comprobarEstadoJugador(view);
                break;
            case R.drawable.two_of_clubs:
            case R.drawable.two_of_diamonds:
            case R.drawable.two_of_hearts:
            case R.drawable.two_of_spades:
                manejarJuego(res,2);
                comprobarEstadoJugador(view);
                break;


            case R.drawable.three_of_clubs:
            case R.drawable.three_of_diamonds:
            case R.drawable.three_of_hearts:
            case R.drawable.three_of_spades:
                manejarJuego(res,3);
                comprobarEstadoJugador(view);
                break;

            case R.drawable.four_of_clubs:
            case R.drawable.four_of_diamonds:
            case R.drawable.four_of_hearts:
            case R.drawable.four_of_spades:
                manejarJuego(res,4);
                comprobarEstadoJugador(view);
                break;
            case R.drawable.five_of_clubs:
            case R.drawable.five_of_diamonds:
            case R.drawable.five_of_hearts:
            case R.drawable.five_of_spades:
                manejarJuego(res,5);
                comprobarEstadoJugador(view);
                break;


            case R.drawable.six_of_clubs:
            case R.drawable.six_of_diamonds:
            case R.drawable.six_of_hearts:
            case R.drawable.six_of_spades:
                manejarJuego(res,6);
                comprobarEstadoJugador(view);
                break;

            case R.drawable.seven_of_clubs:
            case R.drawable.seven_of_diamonds:
            case R.drawable.seven_of_hearts:
            case R.drawable.seven_of_spades:
                manejarJuego(res,7);
                comprobarEstadoJugador(view);
                break;


            case R.drawable.eight_of_clubs:
            case R.drawable.eight_of_diamonds:
            case R.drawable.eight_of_hearts:
            case R.drawable.eight_of_spades:
                manejarJuego(res,8);
                comprobarEstadoJugador(view);
                break;

            case R.drawable.nine_of_clubs:
            case R.drawable.nine_of_diamonds:
            case R.drawable.nine_of_hearts:
            case R.drawable.nine_of_spades:
                manejarJuego(res,9);
                comprobarEstadoJugador(view);
                break;


            case R.drawable.ten_of_clubs:
            case R.drawable.ten_of_diamonds:
            case R.drawable.ten_of_hearts:
            case R.drawable.ten_of_spades:
            case R.drawable.king_of_clubs:
            case R.drawable.king_of_diamonds:
            case R.drawable.king_of_hearts:
            case R.drawable.king_of_spades:
            case R.drawable.queen_of_clubs:
            case R.drawable.queen_of_diamonds:
            case R.drawable.queen_of_hearts:
            case R.drawable.queen_of_spades:
            case R.drawable.jack_of_clubs:
            case R.drawable.jack_of_diamonds:
            case R.drawable.jack_of_hearts:
            case R.drawable.jack_of_spades:
                manejarJuego(res,10);
                comprobarEstadoJugador(view);
                break;
            default:
        }

    }
}