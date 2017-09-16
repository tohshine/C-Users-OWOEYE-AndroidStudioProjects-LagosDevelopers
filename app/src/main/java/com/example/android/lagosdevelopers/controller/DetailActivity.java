package com.example.android.lagosdevelopers.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.lagosdevelopers.R;

/**
 * Created by OWOEYE on 9/13/2017.
 */

public class DetailActivity extends AppCompatActivity {
    TextView Link , Username;
    Toolbar mActionToolBar;
    ImageView imageView;

    public  void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.profile_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        Username = (TextView)findViewById(R.id.username);
        Link = (TextView) findViewById(R.id.link);

        String username = getIntent().getExtras().getString("login");
        String avartar_url = getIntent().getExtras().getString("avatar_url");
        String link = getIntent().getExtras().getString("html_url");

        Link.setText(link);
        Linkify.addLinks(Link,Linkify.WEB_URLS);

        Username.setText(username);

        Glide.with(this)
                .load(avartar_url)
                .placeholder(R.drawable.load)
                .into(imageView);

        getSupportActionBar().setTitle("Detail ActionBar");

    }
    //creating share method
    public Intent createShareIntent(){
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("link");
        Intent intentShare = ShareCompat.IntentBuilder
                .from(this)
                .setText("check out the awesome developers @" + username + " ," + link )
                .getIntent();
        return intentShare;
    }
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.detail,menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);

        menuItem.setIntent(createShareIntent());
        return true;




    }
}
