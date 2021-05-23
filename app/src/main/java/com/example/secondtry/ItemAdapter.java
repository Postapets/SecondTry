package com.example.secondtry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Locale;

//класс адаптера, куда добавляем все данные для внесения во viewholder
public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;

    public ItemAdapter(Context context){
        this.context = context;
    }

    //при создании нового объекта viewholder добавляем массив items в него
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.items, parent, false)
        ) {};
    }

    //при присоединении viewholder к активности выводим данные внесенные пользователем
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        //вешаем событие на view, которое получили в onCreateViewHolder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.putExtra("index", index);
                        context.startActivity(intent);
                    }
                }
        );
        TextView name = holder.itemView.findViewById(R.id.name);
        TextView created = holder.itemView.findViewById(R.id.created);
        CheckBox itemChecked = holder.itemView.findViewById(R.id.checked);
        EditText timeDuration = holder.itemView.findViewById(R.id.timeDuration);
        Item item = Store.getStore().get(index);

        name.setText(String.format("%s %s", index+1, item.getName()));
        created.setText(format(item.getCreated()));

        //редактируем массив отмеченных элементов
        itemChecked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean checked) {
                if (checked){
                    Store.getStore().addChecked(index);
                    Toast.makeText(context, "checked "+Store.getStore().getChecked(index).toString(), Toast.LENGTH_SHORT).show();
                } else {
                    if (Store.getStore().getAllChecked().contains(index)){ Store.getStore().removeChecked(index); }
                }
            }
        });

        Long itemSeconds = item.getTimeAmount();
        timeDuration.setText(String.format(
                Locale.getDefault(),"%d:%d", itemSeconds % 60, itemSeconds / 60
                ));
    }

    //это наверное тож потом в другой класс запихнем
    //преобразование даты
    private String format(Calendar cal) {
        return String.format(
                Locale.getDefault(), "%02d.%02d.%d",
                cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)
        );
    }

    @Override
    public int getItemCount() {
        return Store.getStore().size();
    }

}
