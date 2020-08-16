package com.demo.newcharacter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<Character> characters;
    private OnNoteClickListener onNoteClickListener;

    public CharacterAdapter(ArrayList<Character> characters) {
        this.characters = characters;
    }

    interface OnNoteClickListener {                                                                 //слушаем щелчки по элементам
      void  onNoteClick(int position/*, String nameCharacter, String imgCharater,String classCharater,String raceCharater,int lvlCharater*/ );

        void onNoteLongClick(int position);//получаем номер позиции на которую нажали
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {                // в обучалке вместо parent стоит ViewGroup
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characters.get(position);
        //holder.textViewDescriptionCharacter.setText(character.getImgCharater()); // устанавливаем значения из массива персонажей в итем
        holder.textViewNameCharacter.setText(character.getNameCharacter());
        holder.textViewLvlCharacter.setText(String.format("%s", character.getLvlCharacter()));                    //тут Int
        holder.textViewRaceCharacter.setText(character.getRaceCharacter());
        holder.textViewClassCharacter.setText(character.getClassCharacter());
 /*      int raceId = character.getRaceCharater();                                                  устанавливаем рассу             //в 6 уроке на 16.00 это делается
        switch (raceId){
            case 1: holder.textViewRaceCharacter.setText("Человек");
            break;
            case 2: holder.textViewRaceCharacter.setText("Эльф");
            break;
            case 3: holder.textViewRaceCharacter.setText("Орк");
            break;
            case 4: holder.textViewRaceCharacter.setText("Гном");
            break;
            default: holder.textViewRaceCharacter.setText("Полурослик");
            break;
        }*/
    }

    @Override
    public int getItemCount() {                 //возвращает кол-во элементов в массиве
        return characters.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDescriptionCharacter;                              //картинка
        private TextView textViewNameCharacter;                                         //имя
        private TextView textViewLvlCharacter;                              //уровень
        private TextView textViewRaceCharacter;             //расса
        private TextView textViewClassCharacter;            //класс

        public CharacterViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewDescriptionCharacter = itemView.findViewById(R.id.textViewDescriptionCharacter);                //потому что мы в классе. ищем через род класс
            textViewClassCharacter = itemView.findViewById(R.id.textViewClassCharacter);
            textViewLvlCharacter = itemView.findViewById(R.id.textViewLvlCharacter);
            textViewNameCharacter = itemView.findViewById(R.id.textViewNameCharacter);
            textViewRaceCharacter = itemView.findViewById(R.id.textViewRaceCharacter);
            itemView.setOnClickListener(new View.OnClickListener() {                                //вызываем метод и передаём ананимный класс
                @Override
                public void onClick(View v) {
                        if (onNoteClickListener !=null) {                                              //проверяем не пустое ли место
                           Character character = characters.get(getAdapterPosition());
                           onNoteClickListener.onNoteClick(getAdapterPosition());
                                                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener !=null) {                                                //проверяем не пустое ли место
                        onNoteClickListener.onNoteLongClick(getAdapterPosition());              //вызываем метод и получаем позицию адептера
                    }
                    return true;                                                                    //если оставить false то сработает и короткий и длинный клик
                }
            });
        }
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
        notifyDataSetChanged();
    }

    public List<Character> getCharacters() {
        return characters;
    }
}
