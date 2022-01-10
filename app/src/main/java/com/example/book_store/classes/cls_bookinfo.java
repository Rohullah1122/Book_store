package com.example.book_store.classes;

public class cls_bookinfo {
    private String Bookid,Bookname,Bookauthor,BookImage,Bookrating,Bookoverview,Bookpages,bquantity,insertdate,bookprice,badminid;



    public cls_bookinfo(){

    }

    public cls_bookinfo(String bookid, String bookname, String bookauthor, String bookImage, String bookrating, String bookoverview, String bookpages, String bquantity, String insertdate, String bookprice,String badminid) {
        Bookid = bookid;
        Bookname = bookname;
        Bookauthor = bookauthor;
        BookImage = bookImage;
        Bookrating = bookrating;
        Bookoverview = bookoverview;
        Bookpages = bookpages;
        this.bquantity = bquantity;
        this.insertdate = insertdate;
        this.bookprice = bookprice;
        this.badminid = badminid;
    }

    public String getBadminid() {
        return badminid;
    }

    public void setBadminid(String badminid) {
        this.badminid = badminid;
    }

    public String getBookprice() {
        return bookprice;
    }

    public void setBookprice(String bookprice) {
        this.bookprice = bookprice;
    }

    public String getBookid() {
        return Bookid;
    }

    public void setBookid(String bookid) {
        Bookid = bookid;
    }

    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String bookname) {
        Bookname = bookname;
    }

    public String getBookauthor() {
        return Bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        Bookauthor = bookauthor;
    }


    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }

    public String getBookrating() {
        return Bookrating;
    }

    public void setBookrating(String bookrating) {
        Bookrating = bookrating;
    }

    public String getBookoverview() {
        return Bookoverview;
    }

    public void setBookoverview(String bookoverview) {
        Bookoverview = bookoverview;
    }

    public String getBookpages() {
        return Bookpages;
    }

    public void setBookpages(String bookpages) {
        Bookpages = bookpages;
    }

    public String getBquantity() {
        return bquantity;
    }

    public void setBquantity(String bquantity) {
        this.bquantity = bquantity;
    }

    public String getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(String insertdate) {
        this.insertdate = insertdate;
    }
}
