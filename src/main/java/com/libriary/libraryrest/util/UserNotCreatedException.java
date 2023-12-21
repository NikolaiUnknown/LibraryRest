package com.libriary.libraryrest.util;

public class UserNotCreatedException extends  RuntimeException{
    public UserNotCreatedException(String msg){
        super(msg);
    }
}
