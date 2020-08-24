package kz.almaty.satbayevuniversity.ui.settings.languageFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {
    private int POSITION_1 = 0;
    private List<Language> languageList;
    private Context context;
    private LanguageChangeListener languageChangeListener;

    private SharedPrefCache sharedPrefCache = new SharedPrefCache();
    private Gson gson = new Gson();

    public LanguageAdapter(Context context) {
        this.context = context;
    }

    public void setLanguageChangeListener(LanguageChangeListener languageChangeListener) {
        this.languageChangeListener = languageChangeListener;
    }

    void setLanguageList(ArrayList<Language> languageList) {
        this.languageList = new ArrayList<>();
        this.languageList = languageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_language, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageAdapter.ViewHolder holder, int position) {
        Language language = languageList.get(position);
        holder.bind(language);
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.langaugeTv);
            imageView = view.findViewById(R.id.ic_check_blue);
        }

        void bind(final Language language) {
            if (!sharedPrefCache.getStr("language", context).isEmpty()) {
                String sharedString = sharedPrefCache.getStr("language",context);
                Gson gson = new Gson();
                try {
                    Language language1 = gson.fromJson(sharedString, Language.class);
                    POSITION_1 = language1.getPosition();

                } catch (IllegalStateException | JsonSyntaxException ignored){}
                getChecked(language);
            } else {
                getChecked(language);
            }
        }

        private void getChecked(Language language){
            if (POSITION_1 == getAdapterPosition()) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            textView.setText(language.getLanguage());

            itemView.setOnClickListener(view -> {
                imageView.setVisibility(View.VISIBLE);

                String json = gson.toJson(languageList.get(getAdapterPosition()));
                sharedPrefCache.setStr("language",json,context);
                System.out.println("shared: "+sharedPrefCache.getStr("language", context));
                if (POSITION_1 != getAdapterPosition()) {
                    notifyItemChanged(POSITION_1);
                    POSITION_1 = getAdapterPosition();
                }
                if (languageChangeListener !=null)
                    languageChangeListener.langChanged(language, language.getPosition());
            });
        }
    }
    interface LanguageChangeListener {
        void langChanged(Language language, int position);
    }
}