package com.e_13_internationalization;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 *
 * @author USER
 */
public class E_13_Internationalization extends Application {

    BorderPane borderPane = new BorderPane();

    @Override
    public void start(Stage stage) throws Exception {
        loadView(new Locale("en", "EN"));
        borderPane.setTop(createComboBox());
        stage.setScene(new Scene(borderPane));
        stage.setTitle("Internationalization");
        stage.show();
    }

    private ComboBox<Locale> createComboBox() {
        ComboBox<Locale> comboBox = new ComboBox<>();
        ObservableList<Locale> options = FXCollections.observableArrayList(Locale.ENGLISH, Locale.GERMAN);
        comboBox.setItems(options);
        comboBox.setConverter(new StringConverter<Locale>() {
            @Override
            public String toString(Locale object) {
                return object.getDisplayLanguage();
            }

            @Override
            public Locale fromString(String string) {
                return null;
            }
        });
        comboBox.setCellFactory(p -> new LanguageListCell());
        comboBox.getSelectionModel().selectFirst();

        comboBox.setOnAction(event -> loadView(comboBox.getSelectionModel().getSelectedItem()));
        return comboBox;
    }

    private void loadView(Locale locale) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            // Here, just the resource bundles name is mentioned. You can add support for more languages
            // by adding more properties-files with language-specific endings like
            // "E_13_Internationalization_fr.properties".
            fxmlLoader.setResources(ResourceBundle.getBundle("com/resources/E_13_Internationalization", locale));

            Pane pane = (BorderPane) fxmlLoader.load(this.getClass().getResource("/com/e_13_internationalization/E_13_Internationalization.fxml").openStream());
            borderPane.setCenter(pane);
        } catch (IOException ex) {
        }
    }

    class LanguageListCell extends ListCell<Locale> {

        @Override
        protected void updateItem(Locale item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getDisplayLanguage());
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
