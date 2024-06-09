package com.aegon.SpringBootStudentCrud.CustomException;

public class NoContentError extends RuntimeException {
    public NoContentError(String s){
        super(s);
    }
}
