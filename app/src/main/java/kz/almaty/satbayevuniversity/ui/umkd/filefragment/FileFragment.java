package kz.almaty.satbayevuniversity.ui.umkd.filefragment;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FileFragmentBinding;


public class FileFragment extends Fragment {
    private FileViewModel mViewModel;
    private FileFragmentBinding fileFragmentBinding;
    private FileAdapter fileAdapter;
    private Toolbar toolbar;

    public static FileFragment newInstance() {
        return new FileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FileViewModel.class);
        mViewModel.getFiles();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.file_fragment, container, false);
        View view = fileFragmentBinding.getRoot();
        toolbar = view.findViewById(R.id.toolbarFile);
        getToolbar();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                    getChildFragmentManager().popBackStackImmediate();
                } else {
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

        fileFragmentBinding.setFile(mViewModel);
        fileFragmentBinding.fileRecyclerView.setHasFixedSize(true);
        fileFragmentBinding.fileRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fileFragmentBinding.fileRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        fileAdapter = new FileAdapter(getActivity());
        fileFragmentBinding.fileRecyclerView.setAdapter(fileAdapter);
        toolbar.setNavigationOnClickListener(v -> getFragmentManager().popBackStackImmediate());
        updateFile();
    }

    private void updateFile() {
        mViewModel.getFileMutableLiveData().observe(getViewLifecycleOwner(), files -> {
            fileAdapter.setFilelList(files);
            System.out.println("#####files: " + files);
        });
    }

    private void getToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_file_icon);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.files);
    }

    @Override
    public void onDestroyView() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onDestroyView();
    }

    private void log(String tag, String text) {
        Log.d(tag, text);
    }
}
