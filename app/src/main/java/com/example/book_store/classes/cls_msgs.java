package com.example.book_store.classes;

public class cls_msgs {

    private  String messagesID,senderID,imageUrl,msgs;
    long date;
    private String Feelings;

    public String getFeelings() {
        return Feelings;
    }

    public void setFeelings(String feelings) {
        Feelings = feelings;
    }

    public String getMsgs() {
        return msgs;
    }

    public void setMsgs(String msgs) {
        this.msgs = msgs;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public cls_msgs(){}

    public cls_msgs(String msgs,String senderID,long date){
        this.msgs = msgs;
        this.senderID = senderID;
        this.date = date;


    }





    public String getMessagesID() {
        return messagesID;
    }

    public void setMessagesID(String messagesID) {
        this.messagesID = messagesID;
    }


    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
