package com.codetech.oauth2.exceptions;

public class InvalidLoginResponse extends RuntimeException{
    private String username;
    private String password;
    private String status;

    public InvalidLoginResponse() {
        this.status ="Unauthorized";
        this.username = "Invalid Username";
        this.password = "Invalid Password";

    }





    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}



