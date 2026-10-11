package com.nuevoproyecto.muestra;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int toques = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout raiz = new LinearLayout(this);
        raiz.setOrientation(LinearLayout.VERTICAL);
        raiz.setGravity(Gravity.CENTER);
        raiz.setBackgroundColor(Color.parseColor("#F4F6FA"));
        int margen = dp(24);
        raiz.setPadding(margen, margen, margen, margen);

        TextView titulo = new TextView(this);
        titulo.setText("Nuevo Proyecto");
        titulo.setTextSize(30);
        titulo.setTypeface(Typeface.DEFAULT_BOLD);
        titulo.setTextColor(Color.parseColor("#1B2A41"));
        titulo.setGravity(Gravity.CENTER);

        TextView subtitulo = new TextView(this);
        subtitulo.setText("Muestra de APK");
        subtitulo.setTextSize(16);
        subtitulo.setTextColor(Color.parseColor("#5B6B82"));
        subtitulo.setGravity(Gravity.CENTER);
        subtitulo.setPadding(0, dp(4), 0, dp(32));

        final TextView contador = new TextView(this);
        contador.setText("0");
        contador.setTextSize(72);
        contador.setTypeface(Typeface.DEFAULT_BOLD);
        contador.setTextColor(Color.parseColor("#2563EB"));
        contador.setGravity(Gravity.CENTER);

        Button boton = new Button(this);
        boton.setText("Tócame");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toques++;
                contador.setText(String.valueOf(toques));
            }
        });

        raiz.addView(titulo);
        raiz.addView(subtitulo);
        raiz.addView(contador);
        raiz.addView(boton);
        setContentView(raiz);
    }

    private int dp(int valor) {
        return (int) (valor * getResources().getDisplayMetrics().density);
    }
}
