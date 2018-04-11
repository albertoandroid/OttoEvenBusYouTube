package com.androiddesdecero.ottobus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class SegundoFragment extends Fragment {

    private TextView mTvSegundoFragment;
    private EditText mEtSegundoFragment;
    private Button mBtSegundoFragment;

    public SegundoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_segundo, container, false);

        mTvSegundoFragment = view.findViewById(R.id.segundo_fragment_tv);

        mBtSegundoFragment = view.findViewById(R.id.segundo_fragment_bt);
        mEtSegundoFragment = view.findViewById(R.id.segundo_fragment_et);

        mBtSegundoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusDeOtto.getBus().post(new MessageFtoA(mEtSegundoFragment.getText().toString()));
            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        BusDeOtto.getBus().register(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        BusDeOtto.getBus().unregister(this);
    }

    @Subscribe
    public void receiveMessageFtoA(MessageAtoF message){
        mTvSegundoFragment.setText(message.getMessage());
    }

    @Subscribe
    public void receiveMessageFtoF(MessageFtoF message){
        mTvSegundoFragment.setText(message.getMessage());
    }

}
