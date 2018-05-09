package pl.lab4_pw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {

    private int modify_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);

        Bundle extras = getIntent().getExtras();
        try{
            if(extras.getSerializable("element") != null){
                Animal zwierz = (Animal) extras.getSerializable("element");
                EditText kolor = (EditText)findViewById(R.id.kolor);
                EditText wielkosc = (EditText)findViewById(R.id.wielkosc);
                EditText opis = (EditText)findViewById(R.id.opis);
                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());

                this.modify_id = zwierz.get_id();
            }
        } catch(Exception ex){
            this.modify_id=0;
        }

        ArrayAdapter gatunki = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);
        gatunek.setAdapter(gatunki);
    }

    public void wyslij(View view){

        EditText kolor = (EditText)findViewById(R.id.kolor);
        EditText wielkosc = (EditText)findViewById(R.id.wielkosc);
        EditText opis = (EditText)findViewById(R.id.opis);
        Spinner gatunek = (Spinner) findViewById(R.id.gatunek);

        Animal zwierze = new Animal(gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString());

        zwierze.set_id(this.modify_id);

        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }

}
