module superMarket{
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;

    opens lk.ijse to javafx.fxml;
    opens lk.ijse.controller to javafx.fxml;
    opens lk.ijse.view.tdm to javafx.base;

    exports lk.ijse;
    exports lk.ijse.controller;
}