package kz.almaty.satbayevuniversity.ui.umkd;

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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentUmkdBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;


public class UmkdFragment extends Fragment {
    private UmkdViewModel mViewModel;
    private FragmentUmkdBinding umkdFragmentBinding;
    private UmkdAdapter umkdAdapter;
    private View view;
    public Toolbar toolbar;
    private static final String TAG = "UmkdFragment";

    public static UmkdFragment newInstance() {
        return new UmkdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "Fragment2 onCreateView");

        umkdFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_umkd, container, false);
        view = umkdFragmentBinding.getRoot();
        toolbar = view.findViewById(R.id.umkd_toolbar);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if(i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP){
                ((HomeActivity)getActivity()).onNavigationItemSelected(((HomeActivity)getActivity()).navigationView.getMenu().getItem(0));
                ((HomeActivity)getActivity()).navigationView.getMenu().getItem(0).setChecked(true);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "Fragment2 onActivityCreated");
        mViewModel = ViewModelProviders.of(this).get(UmkdViewModel.class);
        umkdFragmentBinding.setUmkdViewModel(mViewModel);
        umkdFragmentBinding.umkdRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        umkdFragmentBinding.umkdRecyclerView.setHasFixedSize(true);
        umkdFragmentBinding.umkdRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        umkdAdapter = new UmkdAdapter(getActivity());
        umkdFragmentBinding.umkdRecyclerView.setAdapter(umkdAdapter);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("УМКД");

        toolbar.setNavigationOnClickListener(v -> {
            ((HomeActivity)getActivity()).OpenToggleNavMenu();
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel.getUmkd();

        mViewModel.getUmkdMutableLiveData().observe(this, umkds -> {
            umkdAdapter.setResponseUmkdList(umkds);
        });
    }
}
