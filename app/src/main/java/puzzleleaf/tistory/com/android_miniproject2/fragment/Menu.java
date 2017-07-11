package puzzleleaf.tistory.com.android_miniproject2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import puzzleleaf.tistory.com.android_miniproject2.R;
import puzzleleaf.tistory.com.android_miniproject2.adapter.ItemAdapter;


public class Menu extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ItemAdapter itemAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);

        recyclerViewInit(view);

        ImageView image = (ImageView) getActivity().findViewById(R.id.viewChanger);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("qwe","QWEQWE");
            }
        });

        return view;
    }

    private void recyclerViewInit(View v)
    {
        recyclerView = (RecyclerView)v.findViewById(R.id.fragRecyclerView);
        //Manager init
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager = new LinearLayoutManager(getContext());
        //Adapter
        itemAdapter = new ItemAdapter(getContext());

    }


}