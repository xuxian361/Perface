package perface.sundy.com.perface.adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
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
 * Created by sundy on 15/6/27.
 */
public class PhotoGridAdapter extends BaseAdapter {

    private Context context;
    private List list = new ArrayList();
    private LayoutInflater inflater;

    public PhotoGridAdapter() {
    }

    public PhotoGridAdapter(Context context, LayoutInflater inflater) {
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
            if (i == 10) {
                convertView = inflater.inflate(R.layout.upload_photo_img_commit, viewGroup, false);
                AQuery aq1 = new AQuery(convertView);
                holder = new ViewHolder();
                holder.img_add = aq1.id(R.id.img_add).getImageView();
            } else {
                convertView = inflater.inflate(R.layout.photo_item, viewGroup, false);
                AQuery aq1 = new AQuery(convertView);
                holder = new ViewHolder();
                holder.img_item = aq1.id(R.id.img_item).getImageView();
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (i == 10) {
            AQuery aq_img_item = new AQuery(holder.img_add);
            aq_img_item.clicked(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialog_view = inflater.inflate(R.layout.dialog_upload_photo, null);
                    final Dialog dialog = new Dialog(context, R.style.dialog);
                    dialog.setContentView(dialog_view);
                    AQuery aq1 = new AQuery(dialog_view);
                    aq1.id(R.id.btn_album).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.e("sundy", "------------>btn_album");

                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_take_photo).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.e("sundy", "------------>btn_take_photo");

                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    aq1.id(R.id.btn_cancel).clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        } else {
            AQuery aq_img_item = new AQuery(holder.img_item);
            aq_img_item.image(R.mipmap.temp_1);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView img_item, img_add;
    }

}
