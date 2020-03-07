package com.cr.o.cdc.sandboxAndroid.services.utils;

import android.app.Notification;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class NotificationUtils {

    private static final String[] REPLY_KEYWORDS = {"reply", "android.intent.extra.text"};

    public static Action getQuickReplyAction(Notification n) {
        NotificationCompat.Action action = null;
        if (Build.VERSION.SDK_INT >= 24)
            action = getQuickReplyActio(n);
        if (action == null)
            action = getQuickReplyActio(n);
        if (action == null)
            return null;
        return new Action(action);
    }

    private static NotificationCompat.Action getQuickReplyActio(Notification n) {
        for (int i = 0; i < NotificationCompat.getActionCount(n); i++) {
            NotificationCompat.Action action = NotificationCompat.getAction(n, i);
            if (action.getRemoteInputs() != null) {
                for (int x = 0; x < action.getRemoteInputs().length; x++) {
                    RemoteInput remoteInput = action.getRemoteInputs()[x];
                    if (isKnownReplyKey(remoteInput.getResultKey()))
                        return action;
                }
            }
        }
        return null;
    }

    private static boolean isKnownReplyKey(String resultKey) {
        if (TextUtils.isEmpty(resultKey))
            return false;

        resultKey = resultKey.toLowerCase();
        for (String keyword : REPLY_KEYWORDS)
            if (resultKey.contains(keyword))
                return true;

        return false;
    }

}
