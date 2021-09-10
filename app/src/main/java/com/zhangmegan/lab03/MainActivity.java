package com.zhangmegan.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View.OnClickListener listener;
    TextView tLeft, tRight;
    Button bLeft, bRight;
    SeekBar seekBar;
//    SharedPreferences sP;
//    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tLeft = findViewById(R.id.topLeft);
        tRight = findViewById(R.id.topRight);
        bLeft = findViewById(R.id.bottomLeft);
        bRight = findViewById(R.id.bottomRight);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView v = (TextView)(view);
                int count = Integer.parseInt(v.getText().toString())+1;
                v.setText(getString(R.string.display, count));

            }
        };
        tLeft.setOnClickListener(listener);
        tRight.setOnClickListener(listener);
        bLeft.setOnClickListener(listener);
        bRight.setOnClickListener(listener);

//        sP = getSharedPreferences("preferences", MODE_PRIVATE);
//        editor = sP.edit();

        tLeft.setText("0");
        tRight.setText("0");
        bLeft.setText("0");

        seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tLeft.setTextSize(i);
                tRight.setTextSize(i);
                bLeft.setTextSize(i);
                bRight.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    protected void onPause() {
        super.onPause();

        SharedPreferences sP = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sP.edit();

        editor.putString("topL", tLeft.getText().toString());
        editor.putString("topR", tRight.getText().toString());
        editor.putString("botL", bLeft.getText().toString());
        editor.putString("botR", bRight.getText().toString());

        editor.apply();
    }

    protected void onResume() {
        super.onResume();

        SharedPreferences sP = getSharedPreferences("preferences", MODE_PRIVATE);

        tLeft.setText(sP.getString("topL", "0"));
        tRight.setText(sP.getString("topR", "0"));
        bLeft.setText(sP.getString("botL", "0"));
        bRight.setText(sP.getString("botR", "0"));


    }

}
