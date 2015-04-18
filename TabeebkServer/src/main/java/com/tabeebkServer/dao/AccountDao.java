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
import com.tabeebkServer.pojo.User;

/**
 *
 * @author HMA
 */
public class AccountDao {

    //===== session per dao
    static SessionFactory fact = new Configuration().configure("config\\hibernate.cfg.xml").buildSessionFactory();
    static Session session = fact.openSession();

    public static Account checkLogin(Account account) {
        Query q = session.createQuery("from Account a where a.username = :accName and a.password = :pass")
                .setString("accName", account.getUsername()).setString("pass", account.getPassword());
        List result = q.list();
        
        if (result.isEmpty()) {
            return null;
        } else {
            account = (Account) result.get(0);
            return account;
        }
    }
    public static void main(String[] args) {
        //========validate login================
//        Account a=new Account();
//        a.setUsername("alico570");
//        a.setPassword("123");
//        a=checkLogin(a);
//        if(a!=null){
//            System.out.println("Login sucessfuly");
//            System.out.println("Account id: "+a.getId().getAccountId());
//            System.out.println("Account type: "+a.getId().getAccountTypeId());
//            System.out.println("Account Type String: "+a.getAccounttype().getAccountTypeName());
//        }
//        else
//            System.out.println("Faillllllllllllllled");
    }
}
