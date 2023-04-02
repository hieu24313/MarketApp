module com.nmh.marketapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.nmh.marketapp to javafx.fxml;
    exports com.nmh.marketapp;
    opens com.nmh.pojo to javafx.base;
    
}
