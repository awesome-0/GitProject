package com.example.samuel.gitproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private TextView userName,userUrl;
    private ImageView userImage;
    private Button shareBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userName = (TextView) findViewById(R.id.user_name);
        userUrl = (TextView) findViewById(R.id.user_url);
        userImage = (ImageView) findViewById(R.id.user_picture);
        shareBtn = (Button) findViewById(R.id.share_btn);


        if(getIntent() != null){
            final String username = getIntent().getStringExtra("username");
            final String user_photo = getIntent().getStringExtra("profile_picture");
            final String user_url = getIntent().getStringExtra("profile_url");

            userName.setText(username);
            setTitle("User Profile");
            userUrl.setText(user_url);
            userUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent seeUser = new Intent(Intent.ACTION_VIEW);
                    seeUser.setData(Uri.parse(user_url));
                    startActivity(seeUser);
                }
            });
            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareMessage(username,user_photo,user_url);
                }
            });
            Picasso.with(ProfileActivity.this).load(user_photo).networkPolicy(NetworkPolicy.OFFLINE).fit()
                    .centerCrop().placeholder(R.drawable.ic_photo_black_24dp).into(userImage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(ProfileActivity.this).load(user_photo).fit().centerCrop().placeholder(R.drawable.ic_photo_black_24dp)
                            .into(userImage);
                }
            });




        }
    }

    private void shareMessage(String username, String user_photo, String user_url) {

        String mimeType = "text/plain";

        String title = "Share User Details";
        String textToShare = "Check out this awesome developer @" + username + " , " + user_url;

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(title)
                .setText(textToShare)
                .startChooser();

    }


}
