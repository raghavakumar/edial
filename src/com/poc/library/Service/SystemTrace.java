package com.poc.library.Service;

import android.util.Log;

public class SystemTrace implements Trace {
    private boolean debug;
    public void setDebug( boolean debug ) {
          this.debug = debug;
    }
    public void debug(String Tag,  String message ) {
          if( debug ) {  // only print if debug is true
                Log.d(Tag, message);
          }
    }
    public void error(String Tag, String message ) {
          // always print out errors
    	Log.e("error", message);
    }
}
