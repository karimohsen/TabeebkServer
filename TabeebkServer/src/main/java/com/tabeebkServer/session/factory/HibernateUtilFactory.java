/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tabeebkServer.session.factory;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Karim
 */
public class HibernateUtilFactory {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .setProperty(Environment.USE_QUERY_CACHE, Boolean.FALSE.toString())
                    .setProperty(Environment.USE_SECOND_LEVEL_CACHE, Boolean.FALSE.toString())
//                    .setProperty(Environment.CACHE_REGION_FACTORY, org.hibernate.cache.impl.NoCachingRegionFactory.class.getName())
//                    .setProperty(Environment.CACHE_PROVIDER_CONFIG, org.hibernate.cache.NoCacheProvider.class.getName())
                    .configure("config/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
