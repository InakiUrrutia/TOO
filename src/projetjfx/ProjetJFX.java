/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.FranckBarbier.Java._BCMS.BCMS;
import com.FranckBarbier.Java._BCMS.BCMS.Status;
import com.pauware.pauware_engine._Exception.Statechart_exception;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author crossetti
 */
public class ProjetJFX extends Application {
    
    private static BCMS _myBCMS;
    
    static{
        try{
            _myBCMS = new BCMS();
            
        }   
        catch (Statechart_exception ex) {
            Logger.getLogger(ProjetJFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
        
    private static Parent root;
    private static Scene scene;
    private static Stage mainStage;
    /*private static java.util.List<String> firetrucks;
    private static java.util.List<String> policevehicles;
    private static java.util.List<String> routes;*/
    public static int police_vehicle_required;
    public static int fire_truck_required;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/projetjfx/fxml/Login.fxml"));
        
        scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        mainStage = stage;
        
        
            
        _myBCMS.start();
        _myBCMS.state_fire_truck_number(4);
        _myBCMS.state_police_vehicle_number(3);
            
        _myBCMS.set_number_of_fire_truck_required(1);
        _myBCMS.set_number_of_police_vehicle_required(3);
            
        /*firetrucks  = _myBCMS.get_fire_trucks();
        policevehicles = _myBCMS.get_police_vehicles();
        routes = _myBCMS.get_routes();*/
            
        police_vehicle_required =  _myBCMS.get_number_of_police_vehicle_required();
        fire_truck_required = _myBCMS.get_number_of_fire_truck_required();
        
        
    }
    
    
    public static void changeScene(String fxml) throws Exception{
        root = (Parent) FXMLLoader.load(ProjetJFX.class.getResource(fxml));
        scene = mainStage.getScene();
        if (scene == null) {
            scene = new Scene(root, 700, 450);
            mainStage.setScene(scene);
        } else {
            mainStage.getScene().setRoot(root);
        }
        mainStage.sizeToScene();
    }
    
    public static BCMS getBCMS(){
        return _myBCMS;
    }
    
    /*public static java.util.List<String> getFiretruckList(){
        return firetrucks;
    }
    
    public static java.util.List<String> getFiretruckList(Status statut){
        return firetrucks;
    }
    
    public static java.util.List<String> getPolicevehicleList(){
        return policevehicles;
    }
    
    public static java.util.List<String> getPolicevehicleList(Status statut){
        return policevehicles;
    }
    
    public static java.util.List<String> getRoutes(){
        return routes;
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
