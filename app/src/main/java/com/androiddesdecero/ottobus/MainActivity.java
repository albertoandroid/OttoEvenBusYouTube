package com.androiddesdecero.ottobus;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction mFrgamentTransaction;
    private PrimerFragment mPrimerFragment;
    private SegundoFragment mSegundoFragment;

    private Button mBtMainActivity;
    private EditText mEtMainActivity;
    private TextView mTvMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confiView();
    }

    private void confiView(){
        mPrimerFragment = new PrimerFragment();
        mSegundoFragment = new SegundoFragment();
        mFrgamentTransaction = getSupportFragmentManager().beginTransaction();
        mFrgamentTransaction.add(R.id.frame2, mPrimerFragment);
        mFrgamentTransaction.add(R.id.frame3, mSegundoFragment);
        mFrgamentTransaction.commit();

        mTvMainActivity = findViewById(R.id.mainActivityTv);
        mEtMainActivity = findViewById(R.id.mainActivityEt);
        mBtMainActivity = findViewById(R.id.mainActivityBt);

        mBtMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusDeOtto.getBus().post(new MessageAtoF(mEtMainActivity.getText().toString()));
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        BusDeOtto.getBus().register(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        BusDeOtto.getBus().unregister(this);
    }

    @Subscribe
    public void receiveMessageFtoA(MessageFtoA message){
        mTvMainActivity.setText(message.getMessage());
    }

}
