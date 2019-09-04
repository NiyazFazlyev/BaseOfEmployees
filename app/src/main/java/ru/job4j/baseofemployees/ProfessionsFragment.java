package ru.job4j.baseofemployees;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfessionsFragment extends Fragment {
    private static  OnProfessionClickListener callback;
    private final List<Profession> professions = Logic.createProfessions();
    private final RecyclerView.Adapter adapter = new ProfessionAdapter(this.professions);

    public interface OnProfessionClickListener {
        void onProfessionClicked(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_professions, container, false);
//        System.out.println(adapter.getItemCount());
        RecyclerView recycler = view.findViewById(R.id.professions);
//        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return view;
    }

    private static final class ProfessionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Profession> professions;

        public ProfessionAdapter(List <Profession> professions) {
            this.professions = professions;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.profession_info, parent, false)
            ) {
            };
        }


        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
            final Profession profession = this.professions.get(i);
            TextView info = holder.itemView.findViewById(R.id.name);
            info.setText(profession.getName());
            info.setOnClickListener(this::choseProfession);
        }

        @Override
        public int getItemCount() {
            return this.professions.size();
        }

        public void choseProfession(View view) {
            TextView textView = (TextView) view;
            callback.onProfessionClicked(textView.getText().toString());
        }

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnProfessionClickListener) context; // назначаем активити при присоединении фрагмента к активити
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // обнуляем ссылку при отсоединении фрагмента от активити
    }
}
