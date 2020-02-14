package com.cr.o.cdc.sandboxAndroid;


import com.cr.o.cdc.annotations.GraphQlRequest;

@GraphQlRequest(url = "url", name = "pokemon", nullable = true, inputs = {})
public class Pokemon {
    private String name;

    Pokemon(String name) {
        this.name = name;
    }
}
