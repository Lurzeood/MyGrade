package com.example.lurzeood.mygrade;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lurzeood on 2017/7/12 0012.
 */

public class SubjectListFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Subject> lists;

    public static SubjectListFragment newInstance(List<Subject> subjectLists) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("subjectlists", (ArrayList<? extends Parcelable>) subjectLists);

        SubjectListFragment fragment = new SubjectListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lists = getArguments().getParcelableArrayList("subjectlists");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subject_list_fragment,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.subject_list_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        SubjectListAdapter adapter = new SubjectListAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }


    private class SubjectListViewHolder extends RecyclerView.ViewHolder{

        private TextView subjectView;
        private TextView scoreView;

        public SubjectListViewHolder(View itemView) {
            super(itemView);
            subjectView = (TextView) itemView.findViewById(R.id.detail_subject_textview);
            scoreView = (TextView) itemView.findViewById(R.id.detail_score_textview);
        }
    }

    private class SubjectListAdapter extends RecyclerView.Adapter<SubjectListViewHolder>{


        @Override
        public SubjectListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.item_detail,parent,false);

            return new SubjectListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SubjectListViewHolder holder, int position) {
            Subject currentSubject = lists.get(position);
            currentSubject.printOut();
            holder.subjectView.setText(currentSubject.getSubject());
            holder.scoreView.setText(currentSubject.getScore());
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }
    }

}
