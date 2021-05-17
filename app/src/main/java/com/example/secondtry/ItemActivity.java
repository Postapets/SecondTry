package com.example.secondtry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//класс для отображения данных о конкретном iteme
public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        int index = getIntent().getIntExtra("index", 0);
        Item item = Store.getStore().get(index);
        TextView name = findViewById(R.id.name);
        name.setText(item.getName());
        TextView desc = findViewById(R.id.desc);
        desc.setText(item.getDesc());
    }
}
