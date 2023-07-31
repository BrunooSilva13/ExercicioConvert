module com.example.exercicioconvert {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.exercicioconvert to javafx.fxml;
    exports com.example.exercicioconvert;
    exports com.bolso.example.exercicioconvert;
}