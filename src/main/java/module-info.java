module com.example.orm_elite_driving_school_system {    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.sql;
    requires jakarta.persistence;



    opens com.example.orm_elite_driving_school_system to javafx.fxml;
    exports com.example.orm_elite_driving_school_system;
}