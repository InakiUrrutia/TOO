/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjfx.controller;


import com.FranckBarbier.Java._BCMS.BCMS.Status;
import projetjfx.ProjetJFX;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import static projetjfx.ProjetJFX.fire_truck_required;

public class PompierController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btnRetour;
    
    @FXML
    private Button btnDeco;
    
    @FXML
    private Button btnValider;
    
    @FXML
    private Label nbVoituresPompier;
    
    @FXML
    private void handleBtnRetour(ActionEvent event) throws Exception{
        ProjetJFX.changeScene("/projetjfx/fxml/Accueil.fxml");
    }
    
    @FXML
    private void handleBtnDeco(ActionEvent event) throws Exception{
        ProjetJFX.changeScene("/projetjfx/fxml/Login.fxml");
    }
    
    @FXML
    private void handleBtnValider(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setHeaderText("Valider demande ?");
        alert.setContentText(fire_truck_list.getSelectionModel().getSelectedItems().stream().collect(Collectors.joining("\r\n")) + "\r\n" + list_route.getSelectionModel().getSelectedItems());

        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle(null);
        warning.setHeaderText("Un camion est bloqué");
        
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(null);
        info.setHeaderText("Les camions sont bien arrivés");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ProjetJFX.getBCMS().FSC_agrees_about_fire_truck_route();
            for( String s : fire_truck_list.getSelectionModel().getSelectedItems() ){
                System.out.println(s + " Dispatched");
                ProjetJFX.getBCMS().dispatch_fire_truck(s);
                ProjetJFX.getBCMS().fire_truck_dispatched(s);
            }
            for( String s : fire_truck_list.getSelectionModel().getSelectedItems() ){
                if("Fire truck #2".equals(s)){
                    System.out.println("Fire truck #2 blocked");
                    ProjetJFX.getBCMS().fire_truck_blocked(s);
                    ProjetJFX.getBCMS().block_fire_truck(s);
                    warning.setContentText(s);
                    warning.showAndWait();
                }
            }
            System.out.println("Arrivée des camions");
            for( String s : ProjetJFX.getBCMS().get_fire_trucks(Status.Dispatched) ){
                System.out.println(s + " Arrived");
                ProjetJFX.getBCMS().arrive_fire_truck(s);
                ProjetJFX.getBCMS().fire_truck_arrived(s);
            }
            info.setContentText(ProjetJFX.getBCMS().get_fire_trucks(Status.Arrived).stream().collect(Collectors.joining("\r\n")));
            info.showAndWait();
        } else {
            ProjetJFX.getBCMS().FSC_disagrees_about_fire_truck_route();
        }
    }
    
    @FXML
    private ListView<String> fire_truck_list;
    
    @FXML
    private ListView<String> list_route;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try{ 
        fire_truck_list.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                ObservableList<String> selectedItems =  fire_truck_list.getSelectionModel().getSelectedItems();
            }
        });
        
        list_route.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                ObservableList<String> selectedItems =  list_route.getSelectionModel().getSelectedItems();
            }
        });
        
        fire_truck_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        nbVoituresPompier.setText(Integer.toString(fire_truck_required));
        ObservableList<String> items = FXCollections.observableArrayList(
            ProjetJFX.getBCMS().get_fire_trucks()
        );
        fire_truck_list.setItems(items);
        items = FXCollections.observableArrayList(
            ProjetJFX.getBCMS().get_routes()
        );
        list_route.setItems(items);
      }
      catch(Exception e){
          System.out.println("");
      }
        
    }    
    
}
