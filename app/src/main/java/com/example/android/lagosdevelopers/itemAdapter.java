package com.example.android.lagosdevelopers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lagosdevelopers.controller.DetailActivity;
import com.example.android.lagosdevelopers.model.item;
import com.squareup.picasso.Picasso;

import java.util.List;


public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder>{
    private List<item> items;
    private Context context;
    public itemAdapter(Context applicationContext, List<item> itemArrayList){
        this.context=applicationContext;
        this.items=itemArrayList;
    }


        @Override
         public  itemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_users,viewGroup,false);
            return new ViewHolder(view);
        }
        @Override
    public  void  onBindViewHolder(itemAdapter.ViewHolder viewHolder, int i){
           viewHolder.title.setText(items.get(i).getLogin());
            viewHolder.githublink1.setText(items.get(i).getHtmlUrl());

            Picasso.with(context).load(items.get(i).getAvatarUrl()).placeholder(R.drawable.load).into(viewHolder.imageView);

        }
        @Override
    public int getItemCount(){
            return items.size();
        }
        public  class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title, githublink1;
            private ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                title =  view.findViewById(R.id.title);
                githublink1 =  view.findViewById(R.id.githublink1);
                imageView =  view.findViewById(R.id.cover);


                //onItemClick
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        int pos = getAdapterPosition();
                        if (pos !=RecyclerView.NO_POSITION) {
                            item ClickedDataItem = items.get(pos);
                            Intent intent = new Intent(context, DetailActivity.class);
                            intent.putExtra("login", items.get(pos).getLogin());
                            intent.putExtra("html_url", items.get(pos).getHtmlUrl());
                            intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            Toast.makeText(view.getContext(),"you clicked"+ ClickedDataItem.getLogin(), Toast.LENGTH_LONG).show();
                        }

                }});
            }

        }
}

