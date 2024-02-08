module com.example.barchartanimated {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.barchartanimated to javafx.fxml;
    exports com.example.barchartanimated;
}