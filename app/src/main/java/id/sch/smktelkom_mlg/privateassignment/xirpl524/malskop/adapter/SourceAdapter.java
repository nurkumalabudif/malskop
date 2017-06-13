package id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl524.malskop.model.Result;

/**
 * Created by MALBUF on 12/10/2016.
 */

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    ArrayList<Result> list;
    ISourceAdapter mISourceAdapter;
    Context context;

    public SourceAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mISourceAdapter = (SourceAdapter.ISourceAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = list.get(position);
        holder.tvTitle.setText(result.title);
        holder.tvDesc.setText(result.overview);
        holder.tvRelease.setText(result.release_date);
        holder.tvRating.setText(result.vote_average);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + result.poster_path)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface ISourceAdapter {
        void showArticles(String poster_path, String overview, String release_date, String title, String backdrop_path, String vote_average, String original_language, String popularity, String vote_count);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvDesc;
        TextView tvRelease;
        TextView tvRating;
        TextView tvPopularity;
        TextView tvVote;
        TextView tvLanguage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewOverview);
            tvRelease = (TextView) itemView.findViewById(R.id.textViewDate);
            tvRating = (TextView) itemView.findViewById(R.id.textViewRating);
            tvPopularity = (TextView) itemView.findViewById(R.id.VoteAverage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = list.get(getAdapterPosition());
                    mISourceAdapter.showArticles(result.poster_path, result.overview, result.release_date, result.title, result.backdrop_path, result.vote_average, result.original_language, result.popularity, result.vote_count);
                }
            });
        }
    }
}
