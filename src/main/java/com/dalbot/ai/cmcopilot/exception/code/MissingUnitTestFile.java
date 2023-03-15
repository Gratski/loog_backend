package com.dalbot.ai.cmcopilot.exception.code;

public class MissingUnitTestFile extends RuntimeException {
    public MissingUnitTestFile(){}
    public MissingUnitTestFile(String msg){super(msg);}
    public MissingUnitTestFile(String msg, Throwable t){super(msg, t);}
}
