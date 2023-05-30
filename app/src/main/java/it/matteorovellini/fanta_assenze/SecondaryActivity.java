package it.matteorovellini.fanta_assenze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

    ArrayList<TextView> textViews=new ArrayList<>();
    Double potential_win=1.0;
    Double bet=2.0;
    Double total_stake=1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        LinearLayout scroll_layout=(LinearLayout) findViewById(R.id.scroll_layout2);
        TextView textView_potential_win=(TextView) findViewById(R.id.textView_potential_win);
        EditText editText_bet=(EditText) findViewById(R.id.editTextNumber_bet);
        editText_bet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    bet=Double.parseDouble(editText_bet.getText().toString());
                    potential_win=bet*total_stake;
                    textView_potential_win.setText(String.format("%.2f", potential_win)+" €");
                } catch (Exception e) {
                    //editText_bet.setText("2");
                    //potential_win=2.0;
                }
            }
        });

        int i=0;
        while(i<MainActivity.list.size()) {
            create_textView(scroll_layout, i);
            total_stake*=getStake(MainActivity.list.get(i));
            i++;
        }

        potential_win=Double.parseDouble(editText_bet.getText().toString())*total_stake;
        textView_potential_win.setText(String.format("%.2f", potential_win)+" €");
    }

    private void create_textView(LinearLayout layout, int i) {
        textViews.add(new TextView(this));
        textViews.get(textViews.size()-1).setText(MainActivity.list.get(i));
        layout.addView(textViews.get(textViews.size()-1));
    }

    public static double getStake(String string) {
        String[] splitted_string=string.split("\\s+");
        splitted_string[splitted_string.length-1]=splitted_string[splitted_string.length-1].replace(",", ".");
        return Double.parseDouble(splitted_string[splitted_string.length-1]);
    }

    public void scommetti(View view) {
        Toast.makeText(this,
                "Scommessa piazzata, attendi e scoprirai se hai vinto "+String.format("%.2f", potential_win)+" €"+", BUONA FORTUNA!",
                Toast.LENGTH_SHORT).show();
    }
}