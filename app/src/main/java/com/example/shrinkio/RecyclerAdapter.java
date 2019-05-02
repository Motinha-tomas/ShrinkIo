package com.example.shrinkio;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    

    private String[] profileNames = {"Tomas",
            "João",
            "Ziribi",
            "Maria",
            "Manel",
            "Robiru",
            "Salsicha",
            "Manuel"};

    private String[] bio = {"Item one details",
            "Item two details",
            "Item three details",
            "Item four details",
            "Item file details",
            "Item six details",
            "Item seven details",
            "Item eight details"};


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView profileNames;
        TextView bio;

        ViewHolder(View itemView) {
            super( itemView );
            profileNames = itemView.findViewById( R.id.profileName );
            bio = itemView.findViewById( R.id.Bio );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make( v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG )
                            .setAction( "Action", null ).show();

                }
            } );
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from( viewGroup.getContext() )
                .inflate( R.layout.cardview, viewGroup, false );
        return new ViewHolder( v );
    }

    @Override
     public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.profileNames.setText( profileNames[i]);
        viewHolder.bio.setText( bio[i] );
    }


    @Override
    public int getItemCount() {
        return profileNames.length;
    }
}