package com.sqli.sqlidroid;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

import java.util.Locale;

@EActivity(R.layout.activity_agency_detail)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class AgencyDetailActivity extends Activity {

    @Bean
    protected AgencyRepository repo;

    @ViewById(R.id.detail_name)
    protected TextView agencyName;

    @ViewById(R.id.detail_label)
    protected TextView agencyLabel;

    @ViewById(R.id.detail_address)
    protected TextView agencyAddress;

    @ViewById(R.id.detail_phone)
    protected TextView agencyPhone;

    @ViewById(R.id.detail_call_btn)
    protected ImageButton callBtn;

    @ViewById(R.id.detail_map_btn)
    protected ImageButton mapBtn;

    private Agency currentAgency;

    @Extra
    protected int selectedAgencyId;

    @AfterViews
    protected void init() {
        currentAgency = Agency.fromCursor(repo.getAgencyById(selectedAgencyId));

        agencyName.setText(currentAgency.getName());
        agencyLabel.setText(currentAgency.getLabel());
        agencyAddress.setText(currentAgency.getAddress());
        agencyPhone.setText(currentAgency.getPhone());
    }

    @Click(R.id.detail_call_btn)
    void startCall() {
        Uri dialUri = Uri.parse("tel:" + currentAgency.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL, dialUri);

        startActivity(intent);
    }

    @Click(R.id.detail_map_btn)
    void startMap() {

        Locale.setDefault(Locale.US);

        // affiche un Label
        String str = "http://maps.google.com/maps?q=SQLI@" + currentAgency.getLatitude() + "," + currentAgency.getLongitude();

        Uri uri = Uri.parse(str);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }

}
