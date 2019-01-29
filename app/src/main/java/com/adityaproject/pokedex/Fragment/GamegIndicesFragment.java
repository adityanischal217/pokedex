package com.adityaproject.pokedex.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adityaproject.pokedex.Adapter.GameIndecisAdapter;
import com.adityaproject.pokedex.Adapter.MovesAdapter;
import com.adityaproject.pokedex.AppConstantKeys;
import com.adityaproject.pokedex.Model.GameIndex;
import com.adityaproject.pokedex.Model.Move;
import com.adityaproject.pokedex.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamegIndicesFragment extends Fragment {

    ArrayList<GameIndex> gameIndices;
    Context context;
    GameIndecisAdapter gameIndicesAdapter;
    @BindView(R.id.gameindices_rv)
    RecyclerView mGameindicesRv;

    public GamegIndicesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gameg_indices, container, false);
        ButterKnife.bind(this, view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Game Indices");
        init();
        return view;
    }

    private void init() {
        context = getContext();
        gameIndices = getArguments().getParcelableArrayList(AppConstantKeys.GAMEINDICES_ARRAYLIST_);
        setAdapter(gameIndices);
    }

    private void setAdapter(ArrayList<GameIndex> indices) {
        gameIndicesAdapter = new GameIndecisAdapter(context, indices);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mGameindicesRv.setLayoutManager(layoutManager);
        mGameindicesRv.setItemAnimator(new DefaultItemAnimator());
        mGameindicesRv.setAdapter(gameIndicesAdapter);
    }
}
