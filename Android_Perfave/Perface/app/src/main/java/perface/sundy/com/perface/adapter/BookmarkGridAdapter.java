package perface.sundy.com.perface.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.androidquery.AQuery;

import java.util.ArrayList;
import java.util.List;

import perface.sundy.com.perface.R;

/**
 * Created by sundy on 15/6/28.
 */
public class BookmarkGridAdapter extends BaseAdapter {

    private Context context;
    private List list = new ArrayList();
    private LayoutInflater inflater;

    public BookmarkGridAdapter() {
    }

    public BookmarkGridAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
//        if (list != null)
//            return list.size() + 1;
        return 11;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.photo_grid_item, viewGroup, false);
            AQuery aq1 = new AQuery(convertView);
            holder = new ViewHolder();
            holder.img_item = aq1.id(R.id.img_item).getImageView();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AQuery aq_img_item = new AQuery(holder.img_item);
        aq_img_item.image(R.mipmap.temp_1);

        return convertView;
    }

    class ViewHolder {
        ImageView img_item;
    }

}
