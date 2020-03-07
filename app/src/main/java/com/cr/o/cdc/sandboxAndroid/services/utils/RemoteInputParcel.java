package com.cr.o.cdc.sandboxAndroid.services.utils;

import android.os.Bundle;

import androidx.core.app.RemoteInput;

/**
 * Created by JJ on 05/08/15.
 */
class RemoteInputParcel {

    private final String label;
    private final String resultKey;
    private String[] choices = new String[0];
    private final boolean allowFreeFormInput;
    private final Bundle extras;


    RemoteInputParcel(RemoteInput input) {
        label = input.getLabel().toString();
        resultKey = input.getResultKey();
        charSequenceToStringArray(input.getChoices());
        allowFreeFormInput = input.getAllowFreeFormInput();
        extras = input.getExtras();
    }

    private void charSequenceToStringArray(CharSequence[] charSequence) {
        if (charSequence != null) {
            int size = charSequence.length;
            choices = new String[charSequence.length];
            for (int i = 0; i < size; i++)
                choices[i] = charSequence[i].toString();
        }
    }

    String getResultKey() {
        return resultKey;
    }

    String getLabel() {
        return label;
    }

    CharSequence[] getChoices() {
        return choices;
    }

    boolean isAllowFreeFormInput() {
        return allowFreeFormInput;
    }

    Bundle getExtras() {
        return extras;
    }
}
