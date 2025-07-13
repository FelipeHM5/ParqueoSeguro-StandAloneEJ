package com.parqueoseguro.adminApp;

import com.parqueoseguro.adminApp.model.Usuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JavaFXApp extends Application {

    private TableView<Usuario> tableView = new TableView<>();
    private final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lista de Usuarios");

        // Columnas
        TableColumn<Usuario, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));

        TableColumn<Usuario, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        TableColumn<Usuario, String> colCorreo = new TableColumn<>("Correo");
        colCorreo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCorreo()));

        TableColumn<Usuario, String> colRol = new TableColumn<>("Rol");
        colRol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRol()));

        tableView.getColumns().addAll(colId, colNombre, colCorreo, colRol);
        tableView.setItems(usuarios);

      
        
        // Botón para recargar
        Button btnRecargar = new Button("Recargar");
        btnRecargar.setOnAction(e -> cargarUsuariosDesdeApi());

        VBox vbox = new VBox(10, tableView, btnRecargar); // separa tabla y botón con 10px de espacio
        vbox.setPadding(new javafx.geometry.Insets(10));
        Scene scene = new Scene(vbox, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Llamado a la API
        cargarUsuariosDesdeApi();
    }
    
    //Metodo de peticion HTTP a la API para obtener la lista de usuarios
    private void cargarUsuariosDesdeApi() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/usuarios"))
                    .GET()
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(this::procesarRespuesta)
                    .exceptionally(e -> {
                        e.printStackTrace();
                        return null;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Metodo para adapatar el JSON recibido a la lista Usuario
    private void procesarRespuesta(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Usuario> lista = mapper.readValue(json, new TypeReference<List<Usuario>>() {});
            javafx.application.Platform.runLater(() -> {
                usuarios.clear();
                usuarios.addAll(lista);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Metodo para lanzar la app JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}
