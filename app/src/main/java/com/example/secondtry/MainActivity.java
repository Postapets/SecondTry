package com.example.secondtry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private final RecyclerView.Adapter adapter = new ItemAdapter(this);

    protected ArrayList<String> taskNames = new ArrayList<String>();
    protected ArrayList<Long> taskTimes = new ArrayList<Long>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        taskNames.add("Task1 with 30 sec");
        taskTimes.add((long) 30);
        taskNames.add("Task2 with 2:10 min");
        taskTimes.add((long) 130);

    }

    //используется в main_activity.xml при нажатии кнопки добавить
    public void add(View view) {
        Intent intent = new Intent(this.getApplicationContext(), AddActivity.class);
        startActivity(intent);
    }

    public void delete(){
        //в store надо добавить метод для удаление элемента из items
        //а затем описать здесь вызов этого метода и глянуть как recycle view отображает это все

    }
    //используется в main_activity.xml при нажатии кнопки начать
    public void startTimer(View view){
        if (taskNames!=null && taskTimes!=null) {
            Intent intent = new Intent(this.getApplicationContext(), TimerActivity.class);
            intent.putExtra("itemsNames", taskNames);
            intent.putExtra("itemsTime", taskTimes);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Задачи не отмечены", Toast.LENGTH_SHORT).show();
        }
    }

}
