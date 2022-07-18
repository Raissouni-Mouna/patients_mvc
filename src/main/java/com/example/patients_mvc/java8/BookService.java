package com.example.patients_mvc.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {

    public List<Book> getBooksinsort(){
        List<Book> books = new BookDAO().getBooks();
        Collections.sort(books,new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getName().compareTo(o1.getName()); }
        });
        //Collections.sort(books, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        //test
        return books;
    }



    public static void main(String[] args){
        System.out.println(new BookService().getBooksinsort());

    }

}

/*
 * class MyComparator implements Comparator<Book> {
 *
 * @Override public int compare(Book o1, Book o2) { return
 * o2.getName().compareTo(o1.getName()); }
 */
