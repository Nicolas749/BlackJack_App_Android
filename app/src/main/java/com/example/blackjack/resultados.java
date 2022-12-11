package com.example.blackjack;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class resultados
{
    //Attributes
    private final ArrayList<Integer> resultsList; //List to save results
    private static final resultados resultsInstance = new resultados(); //Instance of class (Singleton)
    private SharedPreferences savedResults;

    public static resultados getResults()
    {

        return resultsInstance;
    }

    private resultados() //Private constructor because it is a singleton
    {
        resultsList = new ArrayList<>();
    }

    public void clear() //Clear list
    {
        resultsList.clear();
    }

    public void setResult(int result) //Put a result in list
    {
        resultsList.add(result);
    }

    public ArrayList<Integer> getResultsList()
    {
        return resultsList;
    }
}
