package perface.sundy.com.perface.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import perface.sundy.com.perface.R;
import perface.sundy.com.perface.utils.ActivityController;

/**
 * Created by sundy on 15/6/22.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ActivityController.addActivity(this);
        aq = new AQuery(this);

        init();
    }

    private void init() {
        aq.id(R.id.btnBack).clicked(this);
        aq.id(R.id.relative_header).clicked(this);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.relative_header:
                View dialog_view = getLayoutInflater().inflate(R.layout.dialog_get_photo, null);
                final Dialog dialog = new Dialog(context, R.style.dialog);
                dialog.setContentView(dialog_view);
                AQuery aq1 = new AQuery(dialog_view);
                aq1.id(R.id.btn_album).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
                aq1.id(R.id.btn_take_photo).clicked(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                break;
        }
    }

}
