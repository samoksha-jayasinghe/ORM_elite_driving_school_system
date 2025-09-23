package com.example.orm_elite_driving_school_system.config;

import com.example.orm_elite_driving_school_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Properties properties = new Properties();

        // load from classpath root: src/main/resources/property_file/hibernate.properties
        try (InputStream in = FactoryConfiguration.class.getResourceAsStream("/property_file/hibernate.properties")) {
            if (in == null) {
                throw new RuntimeException("Resource /property_file/hibernate.properties not found on classpath. Place it under src/main/resources/property_file/");
            }
            properties.load(in);

            Configuration configuration = new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Instructors.class)
                    .addAnnotatedClass(Lessons.class)
                    .addAnnotatedClass(Payments.class)
                    .addAnnotatedClass(Students.class)
                    .addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Hibernate SessionFactory", e);
        }

    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
