package com.appfa.android.custom.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appfa.android.R;

public class CustomDialog extends AlertDialog.Builder {

    private final View.OnClickListener dismissDialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    private LinearLayout container;

    private CustomDialog(@NonNull Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        container = (LinearLayout) inflater.inflate(R.layout.dialog_layout, null);
    }

    private void setView() {
        setView(container);
    }

    private void image(int res) {
        ImageView imageView = container.findViewById(R.id.dialogImage);
        imageView.setImageResource(res);
        imageView.setVisibility(View.VISIBLE);
    }

    private void title(int res) {
        TextView textView = container.findViewById(R.id.dialogTitle);
        textView.setText(res);
        textView.setVisibility(View.VISIBLE);
    }

    private void message(int res) {
        TextView textView = container.findViewById(R.id.dialogMessage);
        textView.setText(res);
        textView.setVisibility(View.VISIBLE);
    }

    private void addButton(int textResId, View.OnClickListener listener) {
        Button button = getNewButton();
        button.setText(textResId);
        button.setOnClickListener(listener);
    }

    private Button getNewButton() {
        Button newButton = new Button(getContext());
        newButton.setBackgroundResource(0); // sin background

        return newButton;
    }

    public static class Builder {
        private final Context context;
        private int imageRes;
        private int titleRes;
        private int messageRes;

        /**
         * @param context
         */
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setImage(int resId) {
            imageRes = resId;
            return this;
        }

        public Builder setTitle(int resId) {
            titleRes = resId;
            return this;
        }

        public Builder setMessage(int resId) {
            messageRes = resId;
            return this;
        }

        public void show() {
            CustomDialog dialog = new CustomDialog(context);

            if (imageRes != 0) {
                dialog.image(imageRes);
            }

            if (titleRes != 0) {
                dialog.title(titleRes);
            }

            if (messageRes != 0) {
                dialog.message(messageRes);
            }

            dialog.setView();
            dialog.create().show();
        }
    }
}
