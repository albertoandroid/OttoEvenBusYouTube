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
public class PrimerFragment extends Fragment {

    private TextView mTvPrimerFragment;
    private EditText mEtPrimerFrgament;
    private Button mBtPrimerFragment;


    public PrimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_primer, container, false);

        configFragment(view);

        return view;
    }

    private void configFragment(View view){
        mTvPrimerFragment = view.findViewById(R.id.primer_fragment_tv);
        mBtPrimerFragment = view.findViewById(R.id.primer_fragment_bt);
        mEtPrimerFrgament = view.findViewById(R.id.primer_fragment_et);

        mBtPrimerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusDeOtto.getBus().post(new MessageFtoF(mEtPrimerFrgament.getText().toString()));
            }
        });
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
        mTvPrimerFragment.setText(message.getMessage());
    }

}
