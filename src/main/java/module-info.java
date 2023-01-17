module com.guille.mimongo.miprimermongo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;


    opens com.guille.mimongo.miprimermongo to javafx.fxml;
    exports com.guille.mimongo.miprimermongo;
}