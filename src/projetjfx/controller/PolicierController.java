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
import static projetjfx.ProjetJFX.police_vehicle_required;

public class PolicierController implements Initializable {

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
    private Label nbVoituresPolice;
    
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
        alert.setContentText(police_vehicle_list.getSelectionModel().getSelectedItems().stream().collect(Collectors.joining("\r\n")) + "\r\n" + list_route.getSelectionModel().getSelectedItems());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            ProjetJFX.getBCMS().FSC_agrees_about_police_vehicle_route();
            for( String s : police_vehicle_list.getSelectionModel().getSelectedItems() ){
                System.out.println(s + " Dispatched");
                ProjetJFX.getBCMS().dispatch_police_vehicle(s);
                ProjetJFX.getBCMS().police_vehicle_dispatched(s);
            }
            System.out.println("Arrivée des véhicules");
            for( String s : ProjetJFX.getBCMS().get_police_vehicles(Status.Dispatched) ){
                System.out.println(s + " Arrived");
                ProjetJFX.getBCMS().arrive_police_vehicle(s);
                ProjetJFX.getBCMS().police_vehicle_arrived(s);
            }
        } else {
            ProjetJFX.getBCMS().FSC_disagrees_about_police_vehicle_route();
          }
    }
    
    @FXML
    private ListView<String> police_vehicle_list;
    
    @FXML
    private ListView<String> list_route;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      try{  
        police_vehicle_list.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                ObservableList<String> selectedItems =  police_vehicle_list.getSelectionModel().getSelectedItems();
            }
        });
        
        list_route.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event){
                ObservableList<String> selectedItems =  list_route.getSelectionModel().getSelectedItems();
            }
        });
        
        police_vehicle_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        nbVoituresPolice.setText(Integer.toString(police_vehicle_required));
        ObservableList<String> items = FXCollections.observableArrayList(
            ProjetJFX.getBCMS().get_police_vehicles()
        );
        police_vehicle_list.setItems(items);
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
