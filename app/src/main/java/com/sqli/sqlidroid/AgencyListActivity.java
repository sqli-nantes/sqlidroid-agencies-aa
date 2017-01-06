package com.sqli.sqlidroid;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@EActivity(R.layout.activity_agency_list)
@WindowFeature(Window.FEATURE_NO_TITLE)
public class AgencyListActivity extends Activity {

    @ViewById(R.id.agencylistview)
    protected ListView agencyView;

    @Bean
    protected AgencyRepository repo;

    @AfterViews
    protected void init() {
        Cursor agencies = repo.getAllAgencies();

        String[] from = {"name", "label"};
        int [] to = {R.id.titre, R.id.description};

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_agency_item, agencies, from, to );

        agencyView.setAdapter(adapter);

        // On se décharge de la gestion du cycle de vie du Cursor en appelant
        // startManagingCursor().  La destruction du cursor (l'appel au close()) sera
        // lié à la destruction de l'activity.
        startManagingCursor(agencies);
    }

    @ItemClick(R.id.agencylistview)
    protected void itemViewClicked(int position) {

        final Intent t = new Intent(this, AgencyDetailActivity_.class);
        t.putExtra("selectedAgencyId", (int)agencyView.getItemIdAtPosition(position));

        startActivity(t);
    }

}