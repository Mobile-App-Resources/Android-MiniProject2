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
import puzzleleaf.tistory.com.android_miniproject2.object.ItemObject;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ArrayList<ItemObject> obj;

    public ItemAdapter(Context context, ArrayList<ItemObject> obj) {

        this.mInflater = LayoutInflater.from(context);
        this.obj =obj;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        itemBindInit(holder,position);
        checkUiUpdate(holder,position);
    }


    private void itemBindInit(final ViewHolder holder, final int position)
    {
        holder.myImage.setImageResource(obj.get(position).getRes());
        holder.myPrice.setText(obj.get(position).getContents());
        holder.myTitle.setText(obj.get(position).getTitle());
        holder.myCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUiEvent(holder,position);
                checkUiUpdate(holder,position);
            }
        });
    }

    //체크 데이터 변경
    private void clickUiEvent(ViewHolder holder, int position){
        if(!obj.get(position).isChecked()) {
            obj.get(position).setChecked(true);
        }else{
            obj.get(position).setChecked(false);
        }
    }
    //체크박스 이미지 변경
    private void checkUiUpdate(ViewHolder holder, int position)
    {
        //Check UI
        if(obj.get(position).isChecked()){
            holder.myCheck.setImageResource(R.drawable.ic_check_p);
        }
        else{
            holder.myCheck.setImageResource(R.drawable.ic_check_n);
        }
    }

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
        private TextView myPrice;
        private TextView myTitle;
        private ImageView myCheck;

        public ViewHolder(View itemView) {
            super(itemView);
            myImage = (ImageView)itemView.findViewById(R.id.cardImageView);
            myPrice = (TextView)itemView.findViewById(R.id.cardTextView);
            myTitle = (TextView)itemView.findViewById(R.id.cardTextTitle);
            myCheck = (ImageView)itemView.findViewById(R.id.cardCheckImage);

        }

    }
}