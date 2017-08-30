package com.peacecorps.pcsa.circle_of_trust;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.peacecorps.pcsa.Constants;
import com.peacecorps.pcsa.R;

public class SMSReportDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        //adjusts the width of dialog
        Rect displayRectangle = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (displayRectangle.width() * 0.9);
        this.getWindow().setAttributes(params);

        //takes the message report from intent
        Intent intent = getIntent();
        String msgLog= intent.getStringExtra(Constants.SmsConstants.SMS_REPORT);
        TextView dialogTitle = (TextView) findViewById(R.id.title);
        TextView dialogContent = (TextView) findViewById(R.id.content);
        dialogTitle.setText(getResources().getString(R.string.log_title));
        dialogContent.setText(msgLog);
        TextView confirmButton = (TextView) findViewById(R.id.confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
