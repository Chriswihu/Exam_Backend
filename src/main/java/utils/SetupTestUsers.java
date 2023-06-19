package utils;


import entities.Assignment;
import entities.DinnerEvent;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
        // CHANGE the three passwords below, before you uncomment and execute the code below
        // Also, either delete this file, when users are created or rename and add to .gitignore
        // Whatever you do DO NOT COMMIT and PUSH with the real passwords

        User user = new User("user", "test123");
        User admin = new User("admin", "test123");
        User both = new User("user_admin", "test123");
        User tu1 = new User("testUser1", "test123", "testAddress1", "testPhone1", "testEmail1", 1990, 0);
        User tu2 = new User("testUser2", "test123", "testAddress2", "testPhone2", "testEmail2", 1991, 0);
        User tu3 = new User("testUser3", "test123", "testAddress3", "testPhone3", "testEmail3", 1992, 0);
        User tu4 = new User("testUser4", "test123", "testAddress4", "testPhone4", "testEmail4", 1993, 0);
        User tu5 = new User("testUser5", "test123", "testAddress5", "testPhone5", "testEmail5", 1994, 0);
        User tu6 = new User("testUser6", "test123", "testAddress6", "testPhone6", "testEmail6", 1995, 0);

        Assignment ta1 = new Assignment("testAssignment1", "testDescription1", "testStatus1");
        Assignment ta2 = new Assignment("testAssignment2", "testDescription2", "testStatus2");

        ta1.addUser(tu1);
        ta1.addUser(tu2);
        ta2.addUser(tu1);
        ta2.addUser(tu2);
        ta1.addUser(tu3);
        ta2.addUser(tu4);
        ta1.addUser(tu5);
        ta2.addUser(tu6);

        DinnerEvent td1 = new DinnerEvent("testTime1", "testLocation1", "testDish1", 111);
        DinnerEvent td2 = new DinnerEvent("testTime2", "testLocation2", "testDish2", 222);

        td1.addAssignment(ta1);
        td2.addAssignment(ta2);

//        if (admin.getUserPass().equals("test") || user.getUserPass().equals("test") || both.getUserPass().equals("test"))
//            throw new UnsupportedOperationException("You have not changed the passwords");

        em.getTransaction().begin();


        Role userRole = new Role("user");
        Role adminRole = new Role("admin");
        user.addRole(userRole);
        admin.addRole(adminRole);
        both.addRole(userRole);
        both.addRole(adminRole);

        tu1.addRole(userRole);
        tu2.addRole(userRole);
        tu3.addRole(userRole);
        tu4.addRole(userRole);
        tu5.addRole(userRole);
        tu6.addRole(userRole);

        em.persist(userRole);
        em.persist(adminRole);
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.persist(ta1);
        em.persist(ta2);
        em.persist(td1);
        em.persist(td2);


        em.getTransaction().commit();
        System.out.println("TestAssignment1: " + ta1);
        System.out.println("TestAssignment1 Party: " + ta1.getUsers());
        System.out.println("TestAssignment2: " + ta2);
        System.out.println("TestAssignment2 Party: " + ta2.getUsers());
//        System.out.println("PW: " + user.getUserPass());
//        System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
//        System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
//        System.out.println("Created TEST Users");



        em.close();

    }

}
