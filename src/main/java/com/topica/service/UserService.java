package com.topica.service;

import com.topica.model.EngtovieEntity;
import com.topica.model.UserEntity;
import com.topica.model.VietoengEntity;
import com.topica.respository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    public int checkUser(String username, String password){
        int result = -1;
        if (!userDao.getUser(username).isEmpty()){
            UserEntity u = userDao.getUser(username).get(0);
            if(u.getPassword().equals(password)) {
                if(u.getPermission() == 1) {
                    result = 1;
                } else {
                    result = 0;
                }
            }
        }
        return result;
    }

    public void saveEng(EngtovieEntity e){
        userDao.saveEng(e);
    }

    public void saveVie(VietoengEntity v){
        userDao.saveVie(v);
    }

    public List<EngtovieEntity> listEng() {
        return userDao.getallEng();
    }
    public List<VietoengEntity> listVie() {
        return userDao.getallVie();
    }

    public EngtovieEntity searchEng(String name){
        EngtovieEntity word = new EngtovieEntity();
        List<EngtovieEntity> list = userDao.searchWord(name);
        if (!list.isEmpty()){
            word = list.get(0);
        }
        return word;
    }

    public void delete(int id) {
        userDao.deleteWord(id);
    }

    public EngtovieEntity getEng (int id) {
        return  userDao.getEng(id);
    }
}
