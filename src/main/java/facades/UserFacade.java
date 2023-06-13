package facades;

import dtos.UserDto;
import entities.Role;
//import entities.Tenant;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import security.errorhandling.AuthenticationException;

import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getUser(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, username);
            return user;
        } finally {
            em.close();
        }
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User addUser(String username, String password, String phone, String job) {
        EntityManager em = emf.createEntityManager();
        Role userRole = new Role("user");
        User user = new User(username, password, phone, job);
        user.addRole(userRole);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return user;
    }

    public UserDto create(UserDto ud) {
        User user = new User(ud.getUserName(), ud.getUserPass(), ud.getPhone(), ud.getJob());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new UserDto(user);
    }

    public UserDto delete(String username) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new UserDto(user);
    }

    public List<UserDto> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        return UserDto.getDtos(users);
    }


    public User deleteUser(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return user;
    }

    public User editUser(Long id, String userName, String userPass, String phone, String job) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        user.setUserName(userName);
        user.setUserPass(userPass);
        user.setPhone(phone);
        user.setJob(job);
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return user;
    }
}
