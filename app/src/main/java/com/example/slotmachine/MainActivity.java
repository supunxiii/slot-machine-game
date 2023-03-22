package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    // initialize credit and winnings to zero
    int credit = 0;
    int winnings = 0;

    // initializing fruit values to zero
    int fruit_1=0;
    int fruit_2=0;
    int fruit_3=0;


    // create a list to store fruit images

    List fruits = Arrays.asList(
            R.drawable.apple, R.drawable.banana,
            R.drawable.bar, R.drawable.cherries,
            R.drawable.grapes, R.drawable.lemon,
            R.drawable.melon, R.drawable.orange
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to buttons
        Button btnSpin = findViewById(R.id.btnSpin);
        Button btnCredit = findViewById(R.id.btnCredit);
        Button btnCollect = findViewById(R.id.btnCollect);

        // get references to text views
        TextView txtWinnings = findViewById(R.id.txtWinnings);
        TextView txtCredit = findViewById(R.id.txtCredit);

        // disable the spin and collect buttons on the first load
        btnSpin.setEnabled(false);
        btnCollect.setEnabled(false);

        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // increment credit by 1
                credit++;
                // set btnSpin enabled afterwards
                btnSpin.setEnabled(true);
                // display credit on screen and casting the value to a string
                txtCredit.setText(String.valueOf(credit));
            }
        });
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinViews();
                // reduce value by 1
                credit--;
                txtCredit.setText(String.valueOf(credit));
                // checks if the credit is 0; if it is disable the spin button
                if(credit==0){
                    btnSpin.setEnabled(false);
                }
                // if credit is more than 0
                else{
                    spinViews();
                    if(fruit_1==fruit_2 && fruit_2==fruit_3){
                        winnings = winnings+10;
                        btnCollect.setEnabled(true);
                    }
                    else if (fruit_2 == fruit_3){
                        winnings = winnings+5;
                        btnCollect.setEnabled(true);
                    }
                    txtWinnings.setText(String.valueOf(winnings));
                }
            }
        });
        btnCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You have collected "+winnings+" winnings", Toast.LENGTH_LONG).show();
                winnings=0;
                txtWinnings.setText(String.valueOf(winnings));
                btnCollect.setEnabled(false);

            }
        });

        spinViews();
    }
    // function to spin views
    public void spinViews(){
        // creating a new object from Random class
        Random rand_int = new Random();
        // specifying upperbound as we have 8 images
        int upperbound = 8;
        // assigning random integers for 3 fruits (which are integer type)
        fruit_1 = rand_int.nextInt(upperbound);
        fruit_2 = rand_int.nextInt(upperbound);
        fruit_3 = rand_int.nextInt(upperbound);

        // getting references for the imageViews
        ImageView imgReel1=findViewById(R.id.imgReel1);
        ImageView imgReel2=findViewById(R.id.imgReel2);
        ImageView imgReel3=findViewById(R.id.imgReel3);

        // assigning random images from the list
        imgReel1.setImageResource((Integer) fruits.get(fruit_1));
        imgReel2.setImageResource((Integer) fruits.get(fruit_2));
        imgReel3.setImageResource((Integer) fruits.get(fruit_3));

    }

}