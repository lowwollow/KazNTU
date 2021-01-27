package kz.almaty.satbayevuniversity.ui.settings.complaintFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentComplaintBinding;

public class ComplaintFragment extends DialogFragment {
    private FragmentComplaintBinding comlaintFragmentBinding;
    private ComplaintViewModel mViewModel;
    private TextView dialogComplaintExit;

    public static ComplaintFragment newInstance() {
        return new ComplaintFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        comlaintFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_complaint, container, false);
        View view = comlaintFragmentBinding.getRoot();
        dialogComplaintExit = view.findViewById(R.id.dialogComplaintExit);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ComplaintViewModel.class);
        comlaintFragmentBinding.setViewModel(mViewModel);

        mViewModel.getComplaintMutableLiveData().observe(getViewLifecycleOwner(), integer -> {
            switch (integer){
                case 200:
                    Toast.makeText(getActivity(), R.string.thanks, Toast.LENGTH_SHORT).show();
                    System.out.println("####200OK closed");
                    dialogDismiss();
                    break;
                case 404:
                    Toast.makeText(getActivity(), "Not Found 404 Error", Toast.LENGTH_SHORT).show();
                    break;
                case 400:
                    Toast.makeText(getActivity(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    break;
                case 500:
                    Toast.makeText(getActivity(), "Внутренняя ошибка сервера", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        mViewModel.getMessageLD().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean){
                Toast.makeText(getActivity(), R.string.writeComplaint, Toast.LENGTH_SHORT).show();
            }
        });

        dialogComplaintExit.setOnClickListener(v -> {
            dialogDismiss();
        });
    }

    private void dialogDismiss(){
        Dialog dialog = getDialog();
        if(dialog != null) {
            dialog.dismiss();
        }
    }

    private void log(String tag, String text){
        Log.d(tag, text);
    }

}
