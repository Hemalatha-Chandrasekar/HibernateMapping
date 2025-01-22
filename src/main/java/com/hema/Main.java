package com.hema;

import com.hema.model.Library;
import com.hema.service.LibraryService;
import jakarta.persistence.Table;
import org.hibernate.Transaction;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // LibraryService.insertLibraryAndMember();
        // LibraryService.retrieveLibraryAndMember();
        // LibraryService.insertBookToLibrary();
        //LibraryService.accessBooks();
       // LibraryService.updateMembersWithAddress();
        LibraryService.insertMembersANdBooks();
    }
}