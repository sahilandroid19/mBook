package com.hfad.mbook;


import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class mBookAdapter extends RecyclerView.Adapter<mBookAdapter.ViewHolder> {
    private String[] name;
    private int[] type;
    private Listener listener;

    public interface Listener {
        public void onClick(int position);
    }

    public void setListener(Listener listener1) {
        this.listener = listener1;
    }

    public mBookAdapter(String[] names, int[] types) {
        this.name = names;
        this.type = types;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    @Override
    public mBookAdapter.ViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(group.getContext()).inflate(R.layout.card_view, group, false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        CardView cardView = viewHolder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageView);
        imageView.setImageResource(type[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.textView);
        textView.setText(name[position]);
        //responding to clicks
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null)
                    listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
