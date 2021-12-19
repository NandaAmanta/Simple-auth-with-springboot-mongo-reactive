package com.learn.authentification.response;


public class Response {

    public Response(boolean ok,String statusCode, String message , Object data) {
        this.ok = ok;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;

    }

    public boolean ok ;
    public String statusCode;
    public String message;
    public Object data;

}
