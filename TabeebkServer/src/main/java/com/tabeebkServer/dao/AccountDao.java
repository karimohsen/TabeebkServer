/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.tabeebkServer.pojo.Account;

/**
 *
 * @author HMA
 */
public class AccountDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public Account checkLogin(Account account) {
        
        Query q = session.createQuery("from Account a where a.username = :accName and a.password = :pass")
                .setParameter("accName", account.getUsername()).setParameter("pass", account.getPassword());
        List result = q.list();
        if (result.isEmpty()) {
            return null;
        } else {
            account = (Account) result.get(0);
            return account;
        }
    }

    public void changePassword(String pass, Account acc) {
        session.beginTransaction();
        acc.setPassword(pass);
        session.update(acc);
        session.getTransaction().commit();

    }
}
