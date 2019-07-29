package com.topica.respository;

import com.topica.model.EngtovieEntity;
import com.topica.model.UserEntity;
import com.topica.model.VietoengEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @Autowired
    SessionFactory sessionFactory;

    public List<UserEntity> getUser(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM UserEntity WHERE username = :username");
        query.setParameter("username", username);
        return query.getResultList();
    }

    public void saveEng(EngtovieEntity e) {
        Session session = sessionFactory.openSession();
        session.save(e);
    }

    public void saveVie(VietoengEntity v) {
        Session session = sessionFactory.openSession();
        session.save(v);
    }

    public List<EngtovieEntity> getallEng() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM EngtovieEntity ", EngtovieEntity.class).getResultList();
    }

    public List<VietoengEntity> getallVie() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM VietoengEntity ", VietoengEntity.class).getResultList();

    }

    public List<EngtovieEntity> searchWord(String word) {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM EngtovieEntity WHERE word = :word", EngtovieEntity.class).setParameter("word", word).getResultList();
    }

    public void deleteWord(int id) {
        Session session = sessionFactory.getCurrentSession();
        EngtovieEntity w = session.get(EngtovieEntity.class, id);
        System.out.println(w.getWord());
        session.delete(w);
    }
    public  EngtovieEntity getEng(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(EngtovieEntity.class, id);
    }
}
