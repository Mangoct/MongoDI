module com.guille.mimongo.miprimermongo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.guille.mimongo.miprimermongo to javafx.fxml;
    exports com.guille.mimongo.miprimermongo;
}