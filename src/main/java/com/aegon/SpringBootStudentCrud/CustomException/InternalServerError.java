package com.aegon.SpringBootStudentCrud.CustomException;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String s){
        super(s);
    }
}
