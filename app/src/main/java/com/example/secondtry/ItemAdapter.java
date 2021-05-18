package com.example.secondtry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Locale;

//класс адаптера, куда добавляем все данные для внесения во viewholder
public final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

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
        holder.itemView.setOnClickListener(
                view -> {
                    Intent intent = new Intent(context,ItemActivity.class);
                    intent.putExtra("index", index);
                    context.startActivity(intent);
                }
        );
        TextView name = holder.itemView.findViewById(R.id.name);
        TextView created = holder.itemView.findViewById(R.id.created);
        CheckBox done = holder.itemView.findViewById(R.id.done);
        Item item = Store.getStore().get(index);


        //Маша я тут сделал index+1 чтобы он выводил нумерацию не с нуля, если че
        // по идее не должно влиять ни на что, но посмотри, мало ли
        name.setText(String.format("%s %s", index+1, item.getName()));
        created.setText(format(item.getCreated()));
        //чтоб потом удалять надо будет добавлять в массив значений отмеченных
        done.setOnCheckedChangeListener((view, checked) -> item.setDone(checked));
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
