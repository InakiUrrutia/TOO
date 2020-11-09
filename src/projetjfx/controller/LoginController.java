/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjfx.controller;

import projetjfx.ProjetJFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Inaki
 */
public class LoginController implements Initializable {

    @FXML
    private Button btnValider;
    @FXML
    private Button btnQuitter;
    
    @FXML
    private void handleBtnQuit(ActionEvent event){
        try{
            ProjetJFX.getBCMS().close();
            System.exit(0);
        }
        catch(Exception e){
            System.out.println("Le close a bug");
        }
    }
    
    @FXML
    private void handleBtnValider(ActionEvent event) throws Exception{
        ProjetJFX.changeScene("/projetjfx/fxml/Accueil.fxml");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
