package com.example.myfeeddata;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myfeeddata.Bean.ProductBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.myfeeddata.Bean.ProductBean.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeedStockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeedStockFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private ArrayList<ProductBean> mData;

    public FeedStockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FeedStockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedStockFragment newInstance(String param1, String param2) {
        FeedStockFragment fragment = new FeedStockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View _view = inflater.inflate(R.layout.fragment_feed_stock, container, false);

        mRecyclerView = (RecyclerView) _view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        return _view;
    }

    @Override
    public void onResume() {
        super.onResume();

        feedData();
    }

    private void feedData() {
        new FeedAsyn().execute(BASE_URL + "query.php");
    }

    private class CustomRecyclerView extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);

            return new ViewHolder(_view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            ProductBean _data = mData.get(position);

            holder.title.setText(_data.getName());

            Glide.with(getContext())
                    .load(_data.getImg_product())
                    .into(holder.imgYoutube);

            holder.edit.setTag(R.id.editBTN, _data);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgYoutube;
        Button edit;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.nameTV);
            imgYoutube = itemView.findViewById(R.id.imageProductIMV);
            edit = itemView.findViewById(R.id.editBTN);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), EditActivity.class);
                    intent.putExtra("product_bean", (ProductBean) edit.getTag(R.id.editBTN));
                    startActivity(intent);
                }
            });
        }
    }

    private class FeedAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();

                Request _request = new Request.Builder().url(strings[0]).get().build();

                Response _response = _OkHttpClient.newCall(_request).execute();

                String _result = _response.body().string();

                Gson _gson = new Gson();

                Type type = new TypeToken<List<ProductBean>>() {
                }.getType();

                mData = _gson.fromJson(_result, type);

                return "successfully";

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                mRecyclerView.setAdapter(new CustomRecyclerView());
            } else {
                Toast.makeText(getActivity(), "feed data failure", Toast.LENGTH_SHORT).show();
            }
        }
    }
}