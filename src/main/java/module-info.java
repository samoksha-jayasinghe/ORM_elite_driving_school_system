module com.example.orm_elite_driving_school_system {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.orm_elite_driving_school_system to javafx.fxml;
    exports com.example.orm_elite_driving_school_system;
}