package com.guille.mimongo.miprimermongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class mongoApp extends Application {

    final String FICHERO_CONFIGURACION = "conf.properties";
    static Properties configuracion = new Properties();
    static MongoClient clienteMongo;
    static MongoDatabase dataBaseMongo;

    @Override
    public void start(Stage stage) throws IOException {

        cargarConfiguracion(FICHERO_CONFIGURACION,configuracion);
        contectarBaseDeDatos(configuracion);

        FXMLLoader fxmlLoader = new FXMLLoader(mongoApp.class.getResource("peliculasView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,600);
        stage.setTitle("Información de peliculas");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void cargarConfiguracion(String ficheroConfiguracion, Properties config) {
        try {
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(ficheroConfiguracion);
            config.load(input);
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error al leer el fichero de configuración");
            alerta.setTitle("Error al leer el fichero de configuración");
            alerta.setContentText("ERROR: No se ha podido leer el contenido del fichero " + ficheroConfiguracion );
            alerta.showAndWait();
            System.exit(1);
        }
    }

    private void contectarBaseDeDatos(Properties config) {
        try {

            clienteMongo= MongoClients.create(config.getProperty("URL_MONGO"));
            dataBaseMongo = clienteMongo.getDatabase(config.getProperty("DATABASE"));
        } catch ( Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Error no se ha podido conectar a la base de datos");
            alerta.setTitle("Error no se ha podido conectar a la base de datos");
            alerta.setContentText("ERROR: No se ha podido conectar a la base de datos  " + config.getProperty("DATABASE") );
            alerta.showAndWait();
            System.exit(2);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}