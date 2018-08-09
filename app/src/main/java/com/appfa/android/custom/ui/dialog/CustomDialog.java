package com.appfa.android.custom.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appfa.android.R;

public class CustomDialog extends Dialog {

    private final View.OnClickListener dismissDialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private TextView message;
    private Button primaryButton;
    private Button secondaryButton;
    private Button optionalButton;

    private CustomDialog(@NonNull Context context, final int layout) {
        super(context);
        setContentView(layout);
        setUpDialogViews();
    }

    private void setUpDialogViews() {
        message = findViewById(R.id.dialogText);
        primaryButton = findViewById(R.id.primaryButton);
        secondaryButton = findViewById(R.id.secondaryButton);
        optionalButton = findViewById(R.id.optionalButton);
    }

    private void setMessage(@NonNull final int textResId) {
        message.setText(textResId);
    }

    private void showOnlyDismiss(@NonNull final int textResId) {
        primaryButton.setText(textResId);
        primaryButton.setOnClickListener(dismissDialogClickListener);
        primaryButton.setVisibility(View.VISIBLE);
    }

    public static class Builder {
        private final Context context;
        private final int layout;
        private int textResId = R.string.error_default;
        private int dismissTextResId = R.string.ok_default_button;
        private boolean showOnlyDismiss;
        private int primaryButtonText;
        private int secondaryButtonText;
        private int optionalButtonText;
        private OnClickListener primaryClickListener;
        private OnClickListener secondaryClickListener;
        private OnClickListener optionalClickListener;

        public Builder(Context context, int layout) {
            this.context = context;
            this.layout = layout;
        }

        public Builder textResId(@NonNull final int textResId) {
            this.textResId = textResId;
            return this;
        }

        public Builder showOnlyDismissButtonWithDefaultButton() {
            showOnlyDismiss = true;
            return this;
        }

        public Builder primaryButton(@NonNull final int textResId,
                                     @NonNull final OnClickListener listener) {
            primaryButtonText = textResId;
            primaryClickListener = listener;
            return this;
        }

        public Builder secondaryButton(@NonNull final int textResId,
                                       @NonNull final OnClickListener listener) {
            secondaryButtonText = textResId;
            secondaryClickListener = listener;
            return this;
        }

        public Builder optionalButton(@NonNull final int textResId,
                                      @NonNull final OnClickListener listener) {
            optionalButtonText = textResId;
            optionalClickListener = listener;
            return this;
        }

        public void show() {
            CustomDialog customDialog = new CustomDialog(context, layout);
            customDialog.setMessage(textResId);

            if (showOnlyDismiss) {
                customDialog.showOnlyDismiss(dismissTextResId);
            }

            customDialog.show();
        }
    }
}
