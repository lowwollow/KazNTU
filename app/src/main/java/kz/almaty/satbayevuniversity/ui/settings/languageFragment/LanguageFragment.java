package kz.almaty.satbayevuniversity.ui.settings.languageFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.ui.HomeActivity;

public class LanguageFragment extends Fragment implements LanguageAdapter.LanguageChangeListener{
    private static final String LOG_TAG = "LanguageFragment";
    private ArrayList<Language> languages = new ArrayList<>();
    private LanguageAdapter languageAdapter;
    private RecyclerView recyclerView;
    public Toolbar toolbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_language, container, false);
        recyclerView = view.findViewById(R.id.languageRecycler);
        toolbar = view.findViewById(R.id.languageToolbar);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if(i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP){
                if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                    getChildFragmentManager().popBackStackImmediate();
                }else{
                    return false;
                }
                return true;
            }
            return false;
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        languages.add(new Language("Русский","ru", 0));
        languages.add(new Language("Казахский", "kk", 1));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        languageAdapter = new LanguageAdapter(getActivity());
        recyclerView.setAdapter(languageAdapter);
        languageAdapter.setLanguageList(languages);
        languageAdapter.setLanguageChangeListener(this);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_file_icon);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.chooseLng);

        toolbar.setNavigationOnClickListener(v -> getFragmentManager().popBackStackImmediate());

    }

    @Override
    public void langChanged(Language language, int position) {
        ((HomeActivity)getActivity()).setLocale(language.getLanguageCode());
        ((HomeActivity)getActivity()).getMenuText();
        SharedPrefCache sharedPrefCache = new SharedPrefCache();
        sharedPrefCache.setLang(language, language.getLanguageCode(), getActivity());
        Snackbar.make(getView(), R.string.getLng , Snackbar.LENGTH_LONG).show();
    }

    private void log(String tag, String text){
        Log.d(tag, text);
    }
}





