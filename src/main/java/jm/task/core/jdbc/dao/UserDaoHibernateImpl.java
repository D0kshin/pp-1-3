package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = """
                CREATE TABLE IF NOT EXISTS user (
                id INT NOT NULL AUTO_INCREMENT,
                name VARCHAR(45) NOT NULL,
                last_name VARCHAR(45) NOT NULL,
                age INT(3) NOT NULL,
                PRIMARY KEY (id));
                """;
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
        session.getTransaction().commit();
        sessionFactory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.save(new User(name, lastName, age));
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.delete(user);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM " + User.class.getSimpleName()).list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM " + User.class.getSimpleName()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
