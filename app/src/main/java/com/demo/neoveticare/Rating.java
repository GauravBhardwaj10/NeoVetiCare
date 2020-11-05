package com.demo.neoveticare;

public class Rating {

    String id, message, star, email,  givenUserEmail;

    public Rating() {
    }

    public Rating(String id, String message, String star, String email, String givenUserEmail) {
        this.id = id;
        this.message = message;
        this.star = star;
        this.email = email;
        this.givenUserEmail = givenUserEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenUserEmail() {
        return givenUserEmail;
    }

    public void setGivenUserEmail(String givenUserEmail) {
        this.givenUserEmail = givenUserEmail;
    }
}
