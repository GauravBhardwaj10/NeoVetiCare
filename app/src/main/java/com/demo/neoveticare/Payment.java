package com.demo.neoveticare;

public class Payment {

    String id, card, documentId, datetime;

    public Payment() {
    }

    public Payment(String id, String card, String documentId, String datetime) {
        this.id = id;
        this.card = card;
        this.documentId = documentId;
        this.datetime = datetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
