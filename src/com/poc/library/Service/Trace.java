package com.poc.library.Service;

public interface Trace {
    // turn on and off debugging
    public void setDebug( boolean debug );
    // write out a debug message
    public void debug(String Tag, String message );
    // write out an error message
    public void error(String Tag, String message );
}