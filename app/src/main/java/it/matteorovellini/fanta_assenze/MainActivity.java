package it.matteorovellini.fanta_assenze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final int N_STUDENTS=19;
    public static final String[] STUDENTS={"Aliaj Elio", "Bandrill Jerome", "Bellato Andrea", "Croce Mattia",
            "Fabbri Attalo", "Ferrante Simone", "Fornoni Simone", "Gamage Dulara", "Guardado Steven", "Gurzau Mirko",
            "Lanzillotta Manuel", "Pantano Luca", "Petrera Federico", "Rovellini Matteo", "Santarsiero Matteo",
            "Sisti Simone", "Solcan Valentino", "Tomasino Danilo", "Zhang Stiven"};
    private Button[] buttons_student=new Button[N_STUDENTS];
    private Boolean[] buttons_clicked=new Boolean[N_STUDENTS];
    public Button button_cart;
    boolean cart_full=false;

    public static ArrayList<String> list= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout scroll_layout=(LinearLayout) findViewById(R.id.scroll_layout2);
        button_cart=(Button) findViewById(R.id.button_cart);

        for (int i=0;i<N_STUDENTS;i++) {
            create_button(scroll_layout, i);
            buttons_clicked[i]=false;
        }
    }

    private void create_button(LinearLayout layout, int i) {
        Random random=new Random();
        int integer_portion=random.nextInt(3);
        if (integer_portion==0) integer_portion++;
        double stake=integer_portion+Math.random();
        buttons_student[i]=new Button(this);
        buttons_student[i].setId(i);
        buttons_student[i].setText(STUDENTS[i]+"    -->    "+String.format("%.2f", stake));
        buttons_student[i].setBackgroundColor(Color.rgb(66, 135, 245));

        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,10,0,2);
        buttons_student[i].setOnClickListener(btnclick);
        layout.addView(buttons_student[i], layoutParams);
    }

    View.OnClickListener btnclick = new View.OnClickListener() {


        @Override
        public void onClick(View view) {
            boolean trovato=false;

            if (list.contains(buttons_student[view.getId()].getText())) {
                buttons_student[view.getId()].setBackgroundColor(Color.rgb(66, 135, 245));
                //Toast.makeText(MainActivity.this, "Già aggiunto!", Toast.LENGTH_SHORT).show();
            } else {
                list.add((String) buttons_student[view.getId()].getText());
                buttons_student[view.getId()].setBackgroundColor(Color.rgb(227, 81, 41));
            }

            if (buttons_clicked[view.getId()]) {
                buttons_clicked[view.getId()]=false;
            } else {
                buttons_clicked[view.getId()]=true;
            }

            for(int i=0;i<buttons_clicked.length && !trovato;i++) {
                if (buttons_clicked[i]) {
                    cart_full=true;
                    trovato=true;
                }
            }

            if (!trovato) cart_full=false;

            if (cart_full) {
                //Orange
                button_cart.setBackgroundColor(Color.rgb(227, 81, 41));
            } else {
                //Blue
                button_cart.setBackgroundColor(Color.rgb(66, 135, 245));
            }

            //extras.putString(String.valueOf(view.getId()), (String) buttons_student[view.getId()].getText());
            //list.add(String.valueOf(view.getId()));
        }
    };

    public void cart_clicked(View view) {
        if (!cart_full) list.clear();

        Intent intent=new Intent(this, SecondaryActivity.class);
        startActivity(intent);
    }
}