package com.example.secondtry;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.*;

import org.jetbrains.annotations.Nullable;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private TimePicker timePicker;
    private int itemHour;
    private int itemMinute;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_add_item);

        //тут некрасиво в лейауте отображается таймпикер, исправь плиз
        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true); // 24H Mode.
        timePicker.setHour(new Integer(00));
        timePicker.setMinute(new Integer(30));

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                itemHour = hourOfDay;
                itemMinute = minute;
                //это просто так, все время сохраняем в итем
//                Toast.makeText(getApplicationContext(), "onTimeChanged:"+timePicker.getHour()+":"+timePicker.getMinute(),
//                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void save(View view) {
        EditText edit = this.findViewById(R.id.name);
        EditText editDesc = this.findViewById(R.id.desc);
        Long editSeconds = new Long(itemHour * 3600 + itemMinute * 60);
        Store.getStore().add(
                new Item(edit.getText().toString(),
                        editDesc.getText().toString() ,
                        editSeconds,
                        Calendar.getInstance()));

        Toast.makeText(this, ""+editSeconds, Toast.LENGTH_SHORT);
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
