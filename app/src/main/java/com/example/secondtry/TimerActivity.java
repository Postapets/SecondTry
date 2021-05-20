package com.example.secondtry;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//класс активности таймера, должен быть рабочий
public class TimerActivity extends AppCompatActivity {

    public TextView timerText;
    public ProgressBar timerBar;
    public Button timerStopButton;
    private Long timeSum = Long.valueOf(0);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerText = findViewById(R.id.timerText);
        timerBar = findViewById(R.id.timerBar);
        timerStopButton = findViewById(R.id.timerEndButton);

        //вытаскиваем аргументы из MainActivity
        Bundle arguments = getIntent().getExtras();

        ArrayList<String> itemNames = (ArrayList<String>) arguments.get("itemsNames");
        ArrayList<Long> itemTimes = (ArrayList<Long>) arguments.get("itemsTime");

        //создаем общую переменную со временем для рассчитывания progressBar
        //этого можно не делать, если сделаем прогресс бар на каждую активность отдельно
//        for(Long itemTime: itemTimes){
//            timeSum += itemTime;
//        }

        //рассчет коэффициента для прогрессбара
        //Double coefficient = 100 / (double) timeSum * 1.0;
        double coefficient = 100/ (double) itemTimes.get(0) * 1.0;

        CountDownTimer newTimer = startTimer(itemTimes.get(0),itemNames.get(0), coefficient);

        timerStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopTimer(newTimer);
            }
        });



        //ТАЙМЕР ПОКА НЕ РАБОТАЕТ


//
//        for (int i=0;i<itemTimes.size();i++){
//            int finalI = i;
//            new CountDownTimer(itemTimes.get(finalI)*1000, 1000){
//                @Override
//                public void onTick(long l) {
//                    l /= 1000;
//                    timerText.setText(""+itemNames.get(finalI)+"\n"+l);
//
//                    timerBar.setProgress((int)(l*coefficient));
//                }
//
//                @Override
//                public void onFinish() {
//
//                }
//            }.start();
//        }

    }

    private CountDownTimer startTimer(long timeAmount, String taskName, double coefficient){
        CountDownTimer newTimer = new CountDownTimer(timeAmount, 1000){
            @Override
            public void onTick(long l) {
                l /= 1000;
                int currentMinute = (int) (l / 60);
                int currentSecond = (int) (l % 60);
                timerText.setText(""+taskName+"\n"+currentMinute+":"+currentSecond);

                timerBar.setProgress((int)(l*coefficient));
            }

            @Override
            public void onFinish() {

            }
        }.start();
        return newTimer;
    }

    private void stopTimer(CountDownTimer timer){
        timer.cancel();
    }
}
