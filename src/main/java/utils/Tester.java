package utils;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Tester {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

        User u1 = new User("user1", "test123");
        User u2 = new User("user2", "test123");


        Tenant t1 = new Tenant(u1, "12345678", "job1");
        Tenant t2 = new Tenant(u2, "23456789", "job2");

        Rental r1 = new Rental("01/01-2020", "01/01-2021", 25000, 2500, "Admin");
        Rental r2 = new Rental("02/01-2020", "02/01-2021", 50000, 5000, "Admin");

        House h1 = new House("Gadevej 1", "Bykøbing", 3);
        House h2 = new House("Vejgade 2", "Byrum", 4);

        em.getTransaction().begin();

        u1.setTenant(t1);
        u2.setTenant(t2);

        t1.addRental(r1);
        t2.addRental(r2);

        r1.setHouse(h1);
        r2.setHouse(h2);
//        u1.setTenant(t1);
//        u2.setTenant(t2);


        Role userRole = new Role("user");

        u1.addRole(userRole);
        u2.addRole(userRole);


        em.persist(userRole);
        em.persist(u1);
        em.persist(u2);
        em.persist(t1);
        em.persist(t2);
        em.persist(h1);
        em.persist(h2);
        em.persist(r1);
        em.persist(r2);

        em.getTransaction().commit();


        System.out.println("Tenant1 Info: " + t1);
        System.out.println("Tenant1 User: " + t1.getUser());
        System.out.println("Tenant1 Rentals: " + t1.getRentals());
        System.out.println("Tenant1 House: " + t1.getRentals().get(0).getHouse());

        System.out.println("");
        System.out.println("Tenant2 Info: " + t2);
        System.out.println("Tenant2 User: " + t2.getUser());
        System.out.println("Tenant2 Rentals: " + t2.getRentals());
        System.out.println("Tenant2 House: " + t2.getRentals().get(0).getHouse());


    }
}
