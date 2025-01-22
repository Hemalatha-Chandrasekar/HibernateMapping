package com.hema.service;


import com.hema.model.Address;
import com.hema.model.Book;
import com.hema.model.Library;
import com.hema.model.Member;
import jdk.jshell.spi.SPIResolutionException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.List;

public class LibraryService {
    public static void insertLibraryAndMember() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();

      Library  citylibrary=new Library();
        citylibrary.setName("city Library");
        Library midtownlibrary=new Library();
        midtownlibrary.setName("midtown Library");
        session.persist(citylibrary);
        session.persist(midtownlibrary);

        Member member1=new Member();
        member1.setName("Alice");
        member1.setEmail("alice@gmail.com");
        member1.setLibrary(citylibrary);

        Member member2=new Member();
        member2.setName("Bob");
        member2.setEmail("bob@gmail.com");
        member2.setLibrary(citylibrary);

        Member member3=new Member();
        member3.setName("Charlie");
        member3.setEmail("charlie@gmail.com");
        member3.setLibrary(midtownlibrary);

        session.persist(member1);
        session.persist(member2);
        session.persist(member3);

        transaction.commit();

        session.close();
        sessionFactory.close();
        System.out.println("insert library successfully");
    }
    public static void retrieveLibraryAndMember() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        List<Member> members=session.createQuery("FROM Member",Member.class).getResultList();
        for(Member member:members) {
            System.out.println(member.getName() + "belings to" + member.getLibrary().getName());
        }
        session.close();
        sessionFactory.close();
    }
    public static void insertBookToLibrary() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Library  citylibrary=session.createQuery("FROM Library WHERE name = :name",Library.class)
                .setParameter("name","City Library")
                .uniqueResult();
        Library midtownLibrary=session.createQuery("from Library Where name=:name",Library.class)
                .setParameter("name","Midtown Library")
                .uniqueResult();
        if(citylibrary!=null){
            Book book1=new Book();
            book1.setTitle("Microsoft Aanalyst");
            Book book2=new Book();
            book2.setTitle("Google Aanalyst");
            citylibrary.getBooks().add(book1);
            citylibrary.getBooks().add(book2);

        }else{
            System.out.println("city Library not foound");

        }
        if(midtownLibrary.getName()!=null){
            Book book3=new Book();
            book3.setTitle(("Data Scientist"));

            Book book4=new Book();
            book4.setTitle(("Data Analyst"));
            midtownLibrary.getBooks().add(book3);
            midtownLibrary.getBooks().add(book4);
            session.persist(midtownLibrary);
        }else{
            System.out.println("midtown Library not foound");

        }
        transaction.commit();
        session.close();
        sessionFactory.close();
        System.out.println(" Books added to libraries successfully");
    }
    public static void accessBooks() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Library retrivedLibrary=session.get(Library.class, 1L);
        retrivedLibrary.getBooks().forEach(book->System.out.println(book.getTitle()));
    }
    public static void updateMembersWithAddress() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        List<Member> members=session.createQuery("FROM Member",Member.class).getResultList();
        if(members.isEmpty()){
            System.out.println(" No members not found");
        }else
        {
            for(Member member:members) {
                Address address=new Address();
                if(member.getAddress().equals("Alice")){
                    address.setStreet("123 Elm Street");
                }else if(member.getAddress().equals("Bob")){
                    address.setStreet("123 Bob Street");
                }else if(member.getAddress().equals("Charlie")){
                    address.setStreet("123 Charlie Street");
                }
                member.setAddress(address);
                session.update(member);
            }
        }
        transaction.commit();
        sessionFactory.close();
        System.out.println(" Members updated successfully");
    }
    public static void insertMembersANdBooks() {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session= (Session) sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        Book book1=new Book();
        book1.setTitle("Java Basic");

        Book book2=new Book();
        book2.setTitle("Spring Boot");

        Book book3=new Book();
        book3.setTitle("Spring MVC");

        Member alice=new Member();
        alice.setName("Alice");
        alice.setEmail("alice@gmail.com");

        Member bob=new Member();
        bob.setName("Bob");
        bob.setEmail("bob@gmail.com");

        alice.getBooks().add(book1);
        bob.getBooks().add(book2);

        session.persist(alice);
        session.persist(bob);
        transaction.commit();
        sessionFactory.close();
        System.out.println(" Books added to libraries successfully");

    }
}
