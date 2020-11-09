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


/**
 *
 * @author crossetti
 */
public class AccueilController implements Initializable {
    
    @FXML
    private Button btnPompier;
    
    @FXML
    private Button btnPolicier;
    
    @FXML
    private Button btnDeco;
   
    
    
    @FXML
    private void handleBtnPolicier(ActionEvent event) throws Exception{
        ProjetJFX.getBCMS().PSC_connection_request();
        ProjetJFX.changeScene("/projetjfx/fxml/Policier.fxml");
    }
    
    @FXML
    private void handleBtnPompier(ActionEvent event) throws Exception{
        ProjetJFX.getBCMS().FSC_connection_request();
        ProjetJFX.changeScene("/projetjfx/fxml/Pompier.fxml");
    }
    
    @FXML
    private void handleBtnDeco(ActionEvent event) throws Exception{
        ProjetJFX.changeScene("/projetjfx/fxml/Login.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
