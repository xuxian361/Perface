package perface.sundy.com.perface.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.fragment.BaseFragment;

/**
 * Created by sundy on 15/7/6.
 */
public class MainGridViewAdapter extends RecyclerView.Adapter<MainGridViewAdapter.ViewHolder> {

    private String TAG = "MainGridViewAdapter";
    private Context mContext;
    private List mDataSet;
    private BaseFragment.OnListListener mCallback;

    public MainGridViewAdapter(Context context, List dataSet) {
        mContext = context;
        mDataSet = dataSet;
    }


    public MainGridViewAdapter(Context context, BaseFragment.OnListListener callback, List dataSet) {
        mContext = context;
        mDataSet = dataSet;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.photo_grid_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(mContext).load(R.mipmap.temp_1).into(holder.img_item);
        holder.img_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "------------>position = " + position);
//                mCallback.addContent(new PhotoDetailFragment();
            }
        });
        holder.txt_desc.setText("高三《7》班");
    }

    @Override
    public int getItemCount() {
//        if (mDataSet != null)
//            return mDataSet.size();
        return 19;
    }

    public void remove(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String text, int position) {
        mDataSet.add(position, text);
        notifyItemInserted(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_item;
        public TextView txt_desc;

        public ViewHolder(View itemView) {
            super(itemView);
            img_item = (ImageView) itemView.findViewById(R.id.img_item);
            txt_desc = (TextView) itemView.findViewById(R.id.txt_desc);
        }
    }
}