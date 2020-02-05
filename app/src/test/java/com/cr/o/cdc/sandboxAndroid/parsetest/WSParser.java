package com.cr.o.cdc.sandboxAndroid.parsetest;

public interface WSParser<T> {

    T parserResponse(String response);
    String getRootNode();
}
