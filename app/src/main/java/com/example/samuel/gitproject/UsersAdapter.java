package com.example.samuel.gitproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Samuel on 12/09/2017.
 */

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.UsersViewHolder>  {
    ArrayList<Users> users = new ArrayList<>();
    Context ctx ;

    public UsersAdapter(ArrayList<Users> users, Context ctx) {
        this.users = users;
        this.ctx = ctx;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UsersViewHolder holder, final int position) {
        Picasso.with(ctx).load(users.get(position).getProfie_image()).networkPolicy(NetworkPolicy.OFFLINE).resize(100,100).centerCrop().placeholder(R.drawable.ic_photo_black_24dp).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

                Picasso.with(ctx).load(users.get(position).getProfie_image()).resize(100,100).centerCrop().placeholder(R.drawable.ic_photo_black_24dp).into(holder.imageView);
            }
        });
        holder.user_name.setText(users.get(position).getUsername());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(ctx,ProfileActivity.class);
                profileIntent.putExtra("username",users.get(position).getUsername());
                profileIntent.putExtra("profile_picture",users.get(position).getProfie_image());
                profileIntent.putExtra("profile_url",users.get(position).getProfile_url());
                ctx.startActivity(profileIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView user_name;
        LinearLayout layout;

        public UsersViewHolder(View itemView) {
            super(itemView);
             imageView = (CircleImageView) itemView.findViewById(R.id.user_profile_picture);
             user_name = (TextView) itemView.findViewById(R.id.user_name);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
        }

        public void setImage(final Context ctx, final String image_url){
            Picasso.with(ctx).load(image_url).networkPolicy(NetworkPolicy.OFFLINE).resize(100,100).centerCrop().placeholder(R.mipmap.ic_launcher_round).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(ctx).load(image_url).resize(100,100).centerCrop().placeholder(R.mipmap.ic_launcher_round).into(imageView);
                }
            });

        }
    }
}
