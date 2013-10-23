package com.poc.library.Service;


public class LoggingService {
	
	public Trace getTrace() {
        return new SystemTrace();
  }

}



