package com.appfa.android.custom.ui.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appfa.android.R;

public class CustomDialog extends AlertDialog.Builder {

    private static final int ROTATE_ANIMATION_DURATION = 3000;
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

    private void animatedImage(int res) {
        ImageView imageView = container.findViewById(R.id.dialogImage);
        imageView.setImageResource(res);

        // setting animation
        imageView.clearAnimation();

        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(ROTATE_ANIMATION_DURATION);
        rotate.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(rotate);

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
        private CustomDialog dialog;
        private AlertDialog builder;
        private int imageRes;
        private int titleRes;
        private int messageRes;

        /**
         * @param context
         */
        public Builder(Context context) {
            dialog = new CustomDialog(context);
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

        public void cancel() {
            if(builder != null) {
                builder.cancel();
            }
        }

        public void showLoading() {
            dialog.setCancelable(false);
            dialog.animatedImage(R.mipmap.ic_loading_dialog);

            if (titleRes != 0) {
                dialog.title(titleRes);
            }

            if (messageRes != 0) {
                dialog.message(messageRes);
            }

            dialog.setView();
            builder = dialog.create();
            builder.show();
        }

        public void show() {
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
            builder = dialog.create();
            builder.show();
        }
    }
}
