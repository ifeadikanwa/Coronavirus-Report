package com.example.coronavirusreport;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;
//extend RecyclerView.Adapter<NewsAdapter.ViewHolder> to start creating the recycler adapter
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    //create private global variable for List<News> and OnNewsListener so you can access them from anywhere in the class
    private List<News> newsList;
    private OnNewsListener onNewsListener1;

    //create a constructor that takes in List<News> and OnNewsListener and save them to your global variable
    public NewsAdapter(List<News> news, OnNewsListener onNewsListener1){
        newsList = news;
        this.onNewsListener1 = onNewsListener1;
    }

    //we create an inner class ViewHolder that extends RecyclerView.ViewHolder
    // it also implements View.OnClickListener because we want to set n action to happen on click of any item view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //everything on our item view
        TextView headline;
        ImageView image;
        //every view has its own newslistener
        OnNewsListener onNewsListener2;

        //every view holder contains an itemview and our custom listener
        public ViewHolder(@NonNull View itemView, OnNewsListener onNewsListener) {
            super(itemView);

            headline = itemView.findViewById(R.id.headlineView);
            image = itemView.findViewById(R.id.imageView);
            onNewsListener2 = onNewsListener;

            //attaching an onclicklistener to each item view
            itemView.setOnClickListener(this);
        }

        //when the item view is clicke we wan to call our own custom interface that contains the action we want to perform
        @Override
        public void onClick(View view) {
            onNewsListener2.onNewsClick(getAdapterPosition());
        }
    }

    //OUR CUSTOM INTERFACE CREATED TO PERFORM AN ACTION ON CLICK
    //OUR MAIN ACTIVITY FOR THIS ADAPTER WILL IMPLEMENT THIS INTERFACE SO WE CAN DEFINE THE ACTION WE WANT TO TAKE THERE
    public interface OnNewsListener{
        void onNewsClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newsView = inflater.inflate(R.layout.news_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(newsView, onNewsListener1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = newsList.get(position);

        TextView textView = holder.headline;
        textView.setText(Html.fromHtml(news.getTitle()));

        ImageView imageView = holder.image;

        if(news.getImageUrl() == null || news.getImageUrl().isEmpty()){
            imageView.setImageResource(R.drawable.news_placeholder_resized);
        }
        else{
            Picasso.get()
                    .load(news.getImageUrl())
                    .resize(90,0)
                    .placeholder(R.drawable.news_placeholder_resized)
                    .error(R.drawable.news_placeholder_resized)
                    .into(imageView);
        }


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


}
