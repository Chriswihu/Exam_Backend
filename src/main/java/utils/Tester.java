package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Tester {

    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

        User user = new User("user", "test123", "12345678", "userJob");
        User admin = new User("admin", "test123", "23456789", "adminJob");
        User both = new User("user_admin", "test123", "34567890", "bothJob");

        User u1 = new User("user1", "test123","12345678", "job1");
        User u2 = new User("user2", "test123","23456789", "job2");
        User u3 = new User("user3", "test123", "34567890", "job3");
        User u4 = new User("user4", "test123", "45678901", "job4");

//        Tenant t1 = new Tenant(u1.getUserName(), );
//        Tenant t2 = new Tenant(u2.getUserName(), );
//        Tenant t3 = new Tenant(u3.getUserName());
//        Tenant t4 = new Tenant(u4.getUserName());

        Rental r1 = new Rental("01/01-2020", "01/01-2021", 25000, 2500, "Admin");
        Rental r2 = new Rental("02/01-2020", "02/01-2021", 50000, 5000, "Admin");
        Rental r3 = new Rental("03/01-2020", "03/01-2021", 75000, 7500, "Admin");
        Rental r4 = new Rental("04/01-2020", "04/01-2021", 100000, 10000, "Admin");
        Rental r5 = new Rental("05/01-2020", "05/01-2021", 125000, 12500, "Admin");
        Rental r6 = new Rental("06/01-2020", "06/01-2021", 150000, 15000, "Admin");

        House h1 = new House("Gadevej 1", "Bykøbing", 3);
        House h2 = new House("Vejgade 2", "Byrum", 4);

        Role userRole = new Role("user");
        Role AdminRole = new Role("admin");

        em.getTransaction().begin();

//        u1.setTenant(t1);
//        u2.setTenant(t2);
//        u3.setTenant(t3);
//        u4.setTenant(t4);
//
//        t1.addRental(r1);
//        t1.addRental(r4);
//        t2.addRental(r2);
//        t2.addRental(r3);
//        t3.addRental(r5);
//        t3.addRental(r6);

        u1.addRental(r1);
        u1.addRental(r5);
        u2.addRental(r2);
        u2.addRental(r6);
        u3.addRental(r3);
        u4.addRental(r4);

        h1.addRental(r1);
        h1.addRental(r2);
        h1.addRental(r3);
        h2.addRental(r4);
//        h2.addRental(r5);
//        h2.addRental(r6);


//        r1.setHouse(h1);
//        r2.setHouse(h2);
//        r3.setHouse(h1);
//        r4.setHouse(h1);

        u1.addRole(userRole);
        u2.addRole(userRole);
        u3.addRole(userRole);
        u4.addRole(userRole);

        user.addRole(userRole);
        admin.addRole(AdminRole);
        both.addRole(userRole);
        both.addRole(AdminRole);

//        em.persist(userRole);
//        em.persist(t1);
//        em.persist(t2);
//        em.persist(h1);
//        em.persist(h2);
//        em.persist(r1);
//        em.persist(r2);

        em.persist(u1);
        em.persist(u2);
        em.persist(u3);
        em.persist(u4);

        em.persist(user);
        em.persist(admin);
        em.persist(both);

        em.getTransaction().commit();

        System.out.println("User1 Info: " + u1);
        System.out.println("User1 Rentals: " + u1.getRentals());
        System.out.println("");
        System.out.println("User2 Info: " + u2);
        System.out.println("User2 Rentals: " + u2.getRentals());
        System.out.println("");
        System.out.println("User3 Info: " + u3);
        System.out.println("User3 Rentals: " + u3.getRentals());
        System.out.println("");
        System.out.println("User4 Info: " + u4);
        System.out.println("User4 Rentals: " + u4.getRentals());

//        System.out.println("Tenant1 Info: " + t1);
//        System.out.println("Tenant1 User: " + t1.getUser());
//        System.out.println("Tenant1 Rentals: " + t1.getRentals());
//
//        System.out.println("");
//        System.out.println("Tenant2 Info: " + t2);
//        System.out.println("Tenant2 User: " + t2.getUser());
//        System.out.println("Tenant2 Rentals: " + t2.getRentals());
//
//        System.out.println("");
//        System.out.println("Tenant3 Info: " + t3);
//        System.out.println("Tenant3 User: " + t3.getUser());
//        System.out.println("Tenant3 Rentals: " + t3.getRentals());
//
//        System.out.println("");
//        System.out.println("Tenant4 Info: " + t4);
//        System.out.println("Tenant4 User: " + t4.getUser());
//        System.out.println("Tenant4 Rentals: " + t4.getRentals());




    }
}
