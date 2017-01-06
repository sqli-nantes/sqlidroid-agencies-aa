package com.sqli.sqlidroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@EActivity(R.layout.activity_main)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.imageMap)
    protected ImageView mapButton;

    @Click(R.id.imageMap)
    protected void mapButtonClicked() {
        // lance une nouvelle activity
        final Intent t = new Intent(this, AgencyListActivity_.class);
        startActivity(t);
    }

}
