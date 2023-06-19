package facades;

import dtos.UserDTO;
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
     *
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

    public static List<UserDTO> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users;
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            users = query.getResultList();
        } finally {
            em.close();
        }
        return UserDTO.getDTOs(users);
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

    public UserDTO getUserByUserName(String username) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

    public User addUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        User user = new User(username, password);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return user;
    }

    public UserDTO createUser(UserDTO userDTO) {
        EntityManager em = emf.createEntityManager();
        User user = new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getAddress(), userDTO.getPhone(), userDTO.getEmail(), userDTO.getBirthYear(), userDTO.getAccount());
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

    public UserDTO editUser(String username, UserDTO userDTO) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        try {
            em.getTransaction().begin();
            user.setAddress(userDTO.getAddress());
            user.setPhone(userDTO.getPhone());
            user.setEmail(userDTO.getEmail());
            user.setBirthYear(userDTO.getBirthYear());
            user.setAccount(userDTO.getAccount());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

    public UserDTO deleteUser(String username) {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

}
