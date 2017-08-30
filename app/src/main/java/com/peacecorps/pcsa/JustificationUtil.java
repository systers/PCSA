package com.peacecorps.pcsa;

/**
 * Created by ashu on 3/3/17.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.hyphen.DefaultHyphenator;
import com.bluejamesbond.text.style.TextAlignment;

public final class JustificationUtil {
    private static Context context;

    public JustificationUtil(Context context) {
        this.context = context;
    }

    public static DocumentView addDocumentView(CharSequence article, int type, boolean isOtherStaffContent,Context mContext, Activity activity) {
        final DocumentView documentView;
        if(mContext!=null){
             documentView= new DocumentView(mContext, type);
        }else {
            documentView = new DocumentView(activity, type);
        }
        documentView.getDocumentLayoutParams().setTextColor(context.getResources().getColor(R.color.primary_text_default_material_dark));
        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
        if (isOtherStaffContent) {
            documentView.getDocumentLayoutParams().setTextSize(16f);
        } else {
            documentView.getDocumentLayoutParams().setTextSize(17f);
        }
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setInsetPaddingLeft(10);
        documentView.getDocumentLayoutParams().setInsetPaddingRight(10);
        documentView.getDocumentLayoutParams().setAntialias(true);
        documentView.getDocumentLayoutParams().setInsetPaddingTop(10);
        documentView.getDocumentLayoutParams().setInsetPaddingBottom(10);
        documentView.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
                getInstance(DefaultHyphenator.HyphenPattern.PT));
        documentView.getDocumentLayoutParams().setHyphenated(true);
        documentView.setText(Html.fromHtml(article.toString()));
        return documentView;
    }

//    public static DocumentView addDocumentViewC(CharSequence article, int type, boolean isOtherStaffContent, Context activity) {
//        final DocumentView documentView = new DocumentView(activity, type);
//        documentView.getDocumentLayoutParams().setTextColor(context.getResources().getColor(R.color.primary_text_default_material_dark));
//        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
//        if (isOtherStaffContent) {
//            documentView.getDocumentLayoutParams().setTextSize(16f);
//        } else {
//            documentView.getDocumentLayoutParams().setTextSize(17f);
//        }
//        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
//        documentView.getDocumentLayoutParams().setInsetPaddingLeft(10);
//        documentView.getDocumentLayoutParams().setInsetPaddingRight(10);
//        documentView.getDocumentLayoutParams().setAntialias(true);
//        documentView.getDocumentLayoutParams().setInsetPaddingTop(10);
//        documentView.getDocumentLayoutParams().setInsetPaddingBottom(10);
//        documentView.getDocumentLayoutParams().setHyphenator(DefaultHyphenator.
//                getInstance(DefaultHyphenator.HyphenPattern.PT));
//        documentView.getDocumentLayoutParams().setHyphenated(true);
//        documentView.setText(Html.fromHtml(article.toString()));
//        return documentView;
//    }

//    public static DocumentView addDocumentView(CharSequence article, int type,boolean isOtherStaffContent,Activity activity) {
//        return addDocumentView(article, type, false,activity);
//    }
}
