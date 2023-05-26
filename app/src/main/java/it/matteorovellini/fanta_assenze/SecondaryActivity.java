package it.matteorovellini.fanta_assenze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

    ArrayList<TextView> textViews=new ArrayList<>();
    Double potential_win=2.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        LinearLayout scroll_layout=(LinearLayout) findViewById(R.id.scroll_layout2);
        TextView textView_potential_win=(TextView) findViewById(R.id.textView_potential_win);

        int i=0;
        while(i<MainActivity.list.size()) {
            create_textView(scroll_layout, i);
            potential_win=potential_win*getStake(MainActivity.list.get(i));
            textView_potential_win.setText(String.format("%.2f", potential_win)+" €");
            i++;
        }
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