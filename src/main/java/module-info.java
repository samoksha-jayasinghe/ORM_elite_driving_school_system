module com.example.orm_elite_driving_school_system {    requires javafx.controls;
    //requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.sql;
    requires jakarta.persistence;
    requires java.desktop;
    requires javafx.graphics;
    requires jbcrypt;
    requires java.naming;
    requires javafx.base;



    opens com.example.orm_elite_driving_school_system to javafx.fxml;
    opens com.example.orm_elite_driving_school_system.config to jakarta.persistence;
    opens com.example.orm_elite_driving_school_system.entity to org.hibernate.orm.core;
    opens com.example.orm_elite_driving_school_system.controller to javafx.fxml;
    opens com.example.orm_elite_driving_school_system.dto.tm to javafx.base;
    exports com.example.orm_elite_driving_school_system;
    opens com.example.orm_elite_driving_school_system.dto to javafx.base;
}