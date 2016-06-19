package nguyengiap.vietitpro.tudienanhviet.com.ui.materialsearch.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import nguyengiap.vietitpro.tudienanhviet.com.R;


public abstract class DialogDeleteItemList extends Dialog {
    TextView cancel_button, confirm_button;
    TextView content_text;
    String message;
    Activity activity;
    public abstract void eventDeleteList();

    public DialogDeleteItemList(Activity context, String message) {
        super(context, R.style.alert_dialog);
        this.message = message;
        this.activity=context;
        setCancelable(true);
        setCanceledOnTouchOutside(false);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setupapp);

        cancel_button = (TextView) findViewById(R.id.cancel_button);
        confirm_button = (TextView) findViewById(R.id.confirm_button);
        content_text = (TextView) findViewById(R.id.content_text);
        content_text.setText(message);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventDeleteList();
                dismiss();
            }
        });
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    public void ShowDialog(){
        show();
    }
}