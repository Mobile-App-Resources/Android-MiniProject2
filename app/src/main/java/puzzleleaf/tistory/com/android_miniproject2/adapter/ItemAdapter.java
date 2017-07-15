package puzzleleaf.tistory.com.android_miniproject2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import puzzleleaf.tistory.com.android_miniproject2.PanoramaDialog;
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

    private void itemBindInit(final ViewHolder holder, final int position) {
        //Glide.with(mInflater.getContext()).load(obj.get(position).getRes()).into(holder.myImage);
        //Glide 사용시 이미지가 가끔 누락되는 문제가 발생
        holder.myImage.setImageResource(obj.get(position).getRes());
        holder.myPrice.setText(obj.get(position).getContents());
        holder.myTitle.setText(obj.get(position).getTitle());
        holder.myCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUiEvent(position);
                checkUiUpdate(holder,position);
            }
        });
        //이미지 확대
        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mInflater.getContext(),PanoramaDialog.class);
                intent.putExtra("res",obj.get(position).getRes());
                mInflater.getContext().startActivity(intent);
            }
        });
    }


    //체크 데이터 변경
    private void clickUiEvent(int position){
        if(!obj.get(position).isChecked()) {
            obj.get(position).setChecked(true);
        }else{
            obj.get(position).setChecked(false);
        }
    }
    //체크박스 이미지 변경
    private void checkUiUpdate(ViewHolder holder, int position) {
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

        @BindView(R.id.cardImageView) KenBurnsView myImage;
        @BindView(R.id.cardTextView) TextView myPrice;
        @BindView(R.id.cardTextTitle) TextView myTitle;
        @BindView(R.id.cardCheckImage) ImageView myCheck;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}