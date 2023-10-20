package com.example.servicediary.util;


import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
@UtilityClass
public final class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        return buildConfiguration().configure().buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Mentor.class);
        configuration.addAnnotatedClass(Student.class);
        return configuration;
    }
}
