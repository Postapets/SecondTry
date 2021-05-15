package com.example.secondtry;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.Nullable;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.add);
    }


    public void save(View view) {
        EditText edit = this.findViewById(R.id.name);
        Store.getStore().add(new Item(edit.getText().toString(), Calendar.getInstance()));
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
