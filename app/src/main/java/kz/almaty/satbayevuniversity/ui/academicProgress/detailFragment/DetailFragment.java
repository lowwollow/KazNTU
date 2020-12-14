package kz.almaty.satbayevuniversity.ui.academicProgress.detailFragment;


import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kz.almaty.satbayevuniversity.databinding.FragmentDetailBinding;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.academic.DatesItem;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends DialogFragment {
    private FragmentDetailBinding fragmentDetailBinding;
    private List<DatesItem> datesItems = new ArrayList<>();
    private DetailAdapter detailAdapter;
    private ImageView imageView;
    private View view;
    private DetailViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        view = fragmentDetailBinding.getRoot();
        imageView = view.findViewById(R.id.close_img);
        Dialog dialog = getDialog();
        imageView.setOnClickListener((View v) -> {
            if(dialog != null) {
                dialog.dismiss();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        fragmentDetailBinding.setDetailViewModel(new DetailViewModel());
        int numberOfColumns = 4;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            datesItems = (List<DatesItem>) bundle.getSerializable("detail");
        }

        fragmentDetailBinding.detailRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        fragmentDetailBinding.detailRecyclerView.setHasFixedSize(true);
        detailAdapter = new DetailAdapter(datesItems, getActivity());
        fragmentDetailBinding.detailRecyclerView.setAdapter(detailAdapter);
        detailAdapter.setDatesItems((ArrayList<DatesItem>) datesItems);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        view = null;
        super.onDestroyView();
    }
}