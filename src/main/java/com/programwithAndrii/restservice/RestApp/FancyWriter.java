package com.programwithAndrii.restservice.RestApp;

public class FancyWriter implements TextWriter {

    @Override
    public String WriteText(String s) {
        return s + " fancy Text";
    }
}
