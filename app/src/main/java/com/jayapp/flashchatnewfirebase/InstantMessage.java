package com.jayapp.flashchatnewfirebase;

/**
 * Created by yjj781265 on 7/7/2017.
 */

public class InstantMessage {
    private String message,author;

    public InstantMessage(){

    }
    public InstantMessage(String message,String author){
        this.message = message;
        this.author = author;

    }


    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
