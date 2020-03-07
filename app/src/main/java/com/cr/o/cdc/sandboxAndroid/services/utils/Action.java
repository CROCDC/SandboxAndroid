package com.cr.o.cdc.sandboxAndroid.services.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import java.util.ArrayList;

public class Action {

    private final PendingIntent p;
    private final ArrayList<RemoteInputParcel> remoteInputs = new ArrayList<>();

    Action(NotificationCompat.Action action) {
        this.p = action.actionIntent;
        if (action.getRemoteInputs() != null) {
            int size = action.getRemoteInputs().length;
            for (int i = 0; i < size; i++)
                remoteInputs.add(new RemoteInputParcel(action.getRemoteInputs()[i]));
        }
    }

    public void sendReply(Context context, String msg) throws PendingIntent.CanceledException {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        ArrayList<RemoteInput> actualInputs = new ArrayList<>();

        for (RemoteInputParcel input : remoteInputs) {
            Log.i("", "RemoteInput: " + input.getLabel());
            bundle.putCharSequence(input.getResultKey(), msg);
            RemoteInput.Builder builder = new RemoteInput.Builder(input.getResultKey());
            builder.setLabel(input.getLabel());
            builder.setChoices(input.getChoices());
            builder.setAllowFreeFormInput(input.isAllowFreeFormInput());
            builder.addExtras(input.getExtras());
            actualInputs.add(builder.build());
        }

        RemoteInput[] inputs = actualInputs.toArray(new RemoteInput[actualInputs.size()]);
        RemoteInput.addResultsToIntent(inputs, intent, bundle);
        p.send(context, 0, intent);
    }

}
