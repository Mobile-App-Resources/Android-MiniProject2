package puzzleleaf.tistory.com.android_miniproject2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import puzzleleaf.tistory.com.android_miniproject2.R;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<String> obj;

    public ItemAdapter(Context context) {
        obj = new ArrayList<>();
        obj.add("qwe");
        obj.add("qwe");
        obj.add("qwe");
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.myImage.setImageResource(R.drawable.sample6);
    }


    //RecyclerView에서 게시물 순서 꼬이는 문제 해결을 위한 코드
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return obj.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView myImage;
        private TextView mytext;


        public ViewHolder(View itemView) {
            super(itemView);
            myImage = (ImageView)itemView.findViewById(R.id.cardImageView);
            mytext = (TextView)itemView.findViewById(R.id.cardTextView);

        }

    }
}