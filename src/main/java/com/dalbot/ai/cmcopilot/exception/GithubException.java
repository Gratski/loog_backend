package com.dalbot.ai.cmcopilot.exception;

public class GithubException extends RuntimeException {
    public GithubException(){}
    public GithubException(String msg){
        super(msg);
    }
    public GithubException(String msg, Throwable t){
        super(msg, t);
    }
}
