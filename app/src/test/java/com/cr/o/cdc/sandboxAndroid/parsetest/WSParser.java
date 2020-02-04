package com.cr.o.cdc.sandboxAndroid.parsetest;

/**
 * Created by sirkuryaki on 8/25/17.
 */

public interface WSParser<T> {

    T parserResponse(String response);
    String getRootNode();
}
