package com.example.apptravelvn;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TravelLocationAdapter extends  RecyclerView.Adapter<TravelLocationAdapter.TravelLocationViewholder> {


    private List<TravelLocation> travelLocations;

    public TravelLocationAdapter(List<TravelLocation> travelLocations) {
        this.travelLocations = travelLocations;
    }

    @NonNull
    @Override
    public TravelLocationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TravelLocationViewholder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_location,
                        parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TravelLocationViewholder holder, int position) {
        holder.setLocationData(travelLocations.get(position));
    }

    @Override
    public int getItemCount() {
        return travelLocations.size();
    }

    static class TravelLocationViewholder extends RecyclerView.ViewHolder{

        private KenBurnsView kbvLocation;
        private TextView txtTitle, txtLocation,txtStarRanting;
        public TravelLocationViewholder(@NonNull View itemView) {
            super(itemView);
            kbvLocation=itemView.findViewById(R.id.kbvLocation);

            txtTitle= itemView.findViewById(R.id.textTitle);
            txtLocation=itemView.findViewById(R.id.textLocation);
            txtStarRanting=itemView.findViewById(R.id.textStarRating);

        }
        
        void setLocationData(TravelLocation  travelLocation){
            Picasso.get().load(travelLocation.imageURL).into(kbvLocation);

            txtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent= new Intent(itemView.getContext(), ChiTietDiaDiemActivity.class);
                    TravelLocation travel= travelLocation;
                    intent.putExtra("travel",travel);
                    itemView.getContext().startActivity(intent);
                }
            });
            txtTitle.setText(travelLocation.title);
            txtLocation.setText(travelLocation.location);
            txtStarRanting.setText(String.valueOf(travelLocation.starRating));
        }
    }

}
