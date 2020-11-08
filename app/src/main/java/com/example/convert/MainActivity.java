package com.example.convert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    int r =20;
    String parseR = new String();
    String parseU = new String();
    String parseE = new String();
    int a = 0;
    int b = 0;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io").addConverterFactory(ScalarsConverterFactory.create()).build();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);


        rApi();
        uApi();
        eApi();
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String item = (String)parent.getItemAtPosition(position);
                a = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String)parent.getItemAtPosition(position);
                b = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
        spinner3.setOnItemSelectedListener(itemSelectedListener2);
    }

    public void ho(View view)
    {
        EditText edit = (EditText)findViewById(R.id.fg);
        EditText edit2 = (EditText)findViewById(R.id.fg2);
        double []dE = new double[2];
        double []dU = new double[2];
        double []dR = new double[2];
        double n;

        String s = new String();
        dE = parse_string("RUB", "USD", parseE);
        dR = parse_string("EUR", "USD", parseR);
        dU = parse_string("RUB", "EUR", parseU);
        s = edit.getText().toString();
        n = Double.parseDouble(s);
        if (a == 0)
        {
            if (b == 0)
            {
                n= n;
            }
            else if (b == 1){
                n*=dR[1];
            }
            else if (b == 2){
                n*=dR[0];
            }
        }
        else if (a == 1)
        {
            if (b == 0)
            {
                n*=dU[0];
            }
            else if (b == 1){
                n = n;
            }
            else if (b == 2){
                n*=dU[1];
            }
        }
        else if (a == 2)
        {
            if (b == 0)
            {
                n*=dE[0];
            }
            else if (b == 1){
                n*=dE[1];
            }
            else if (b == 2){
                n = n;
            }
        }



        s = Double.toString(n);
        edit2.setText(s);


    }

    void    rApi()
    {
        Rub rubApi = retrofit.create(Rub.class);
        Call<String> r = rubApi.rub();
        r.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                parseR = response.body();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }

    void    uApi()
    {
        Usd usdApi = retrofit.create(Usd.class);
        Call<String> u = usdApi.usd();
        u.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                parseU = response.body();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }

    void    eApi()
    {
        Eur eurApi = retrofit.create(Eur.class);
        Call<String> e = eurApi.eur();
        e.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                parseE = response.body();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });

    }

    double []parse_string(String a, String b, String str)
    {
        int i = 0;
        int n;
        double []d = new double[2];
        char []s = str.toCharArray();
        char []sa = a.toCharArray();
        char []sb = b.toCharArray();
        String ad  = new String();
        String bd  = new String();


        n = str.length();
        while (i < n - 10)
        {
            if (s[i] == sa[0] && s[i + 1] == sa[1] && s[i + 2] == sa[2])
            {
                i = i + 5;
                while (s[i] != ',')
                ad = ad + s[i++];
            }
            if (s[i] == sb[0] && s[i + 1] == sb[1] && s[i + 2] == sb[2])
            {
                i = i + 5;
                while (s[i] != ',')
                    bd = bd + s[i++];
            }
            i++;

        }
        d[0] = Double.parseDouble(ad);
        d[1] = Double.parseDouble(bd);
    return (d);

    }


}

