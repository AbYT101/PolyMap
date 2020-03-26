package com.supreme.ab.polymap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.here.sdk.mapviewlite.MapViewLite;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.DataViewHolder> {
    Context context;
    List list;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    public adapter(Context context, List list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclorsingle,null);

        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
         EventModel eventModel= (EventModel) list.get(position);
        holder.Name.setText(eventModel.getEventName());
        holder.gender.setText(eventModel.getPlace());
        holder.username.setText(eventModel.getDate());


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public View.OnLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView username, gender;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            Name =itemView.findViewById(R.id.fullN);
            username =itemView.findViewById(R.id.userNa);
            gender= itemView.findViewById(R.id.gender3);
            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                builder.setTitle("Show on Map");
                                                builder.setMessage("Do you want to show it on the map");
                                                builder.setIcon(R.drawable.ic__place_foreground);
                                                builder.setPositiveButton("show On Map", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        context.startActivity(new Intent(context, MainActivity.class));
                                                    }
                                                });
                                                builder.show();
                                            }
                                        });

                    itemView.setOnLongClickListener(getOnLongClickListener());

        }
    }
}
