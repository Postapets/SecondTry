package com.example.secondtry;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

class MainActivity2 extends AppCompatActivity {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        button=(Button) findViewById(R.id.enter_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(com.example.secondtry.MainActivity2.this,Log_in.class);
                startActivity(intent);

                Toast.makeText(com.example.secondtry.MainActivity2.this,"button is clicked",Toast.LENGTH_SHORT).show();
            }
        });



        textView=(TextView)findViewById(R.id.no_account);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.secondtry.MainActivity2.this,Sign_in.class);
                startActivity(intent);

                Toast.makeText(com.example.secondtry.MainActivity2.this,"Text is clicked",Toast.LENGTH_SHORT).show();
            }


        });

    }


}