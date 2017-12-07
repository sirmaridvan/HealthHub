package halmob.healthhub;

import android.app.ProgressDialog;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roger.catloadinglibrary.CatLoadingView;

public class BaseActivity extends AppCompatActivity {
    private CatLoadingView mView = new CatLoadingView();

    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    public void showProgressDialog() {

        //mView.show(getSupportFragmentManager(), "");
    }

    public void hideProgressDialog() {
        //mView.dismiss();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
