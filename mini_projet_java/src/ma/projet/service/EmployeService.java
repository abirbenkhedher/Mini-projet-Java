package ma.projet.service;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import ma.projet.beans.Employe;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class EmployeService implements IDao<Employe> {
    @FXML
    private TextField idField ;

    @FXML
    private TextField prenomField ;

    @FXML
    private TextField nomField ;

    @FXML
    private TextField titreField ;

    @FXML
    private TextField affectationField ;

    @FXML
    private TextField niveauField ;

    @FXML
    private TextField salaireField ;

    @FXML
    private Button insertButton ;

    @FXML
    private Button updateButton ;

    @FXML
    private Button deleteButton ;

    @FXML
    private TableView<Employe> TableView ;

    @FXML
    private TableColumn<Employe, Integer> idColumn ;

    @FXML
    private TableColumn<Employe, String> prenomColumn ;

    @FXML
    private TableColumn<Employe, String> nomColumn ;

    @FXML
    private TableColumn<Employe, String> titreColumn ;

    @FXML
    private TableColumn<Employe, Integer> affectationColumn ;

    @FXML
    private TableColumn<Employe, Integer> niveauColumn ;

    @FXML
    private TableColumn<Employe, Integer> salaireColumn ;
    private ChoiceBox<Object> comboBox;

    @FXML
    public void insertButton(){
        try {
            String req =  "INSERT INTO employe (id_emp, prenom, nom, titre,Niveau,salaire,affectation) "
                    + "VALUES (NULL, '"+prenomField.getText()+"', '"
                    +nomField.getText()+"', '"+titreField.getText()+"', "
                    + "'"+niveauField.getText()+"', "
                    +"'"+salaireField.getText()+"','"+affectationField.getText()+"')";

            //�tape 3 : Cr�ation d'un Stalement
            Statement st = Connexion.getCn().createStatement();
            //�tape 4 : Ex�cution de la requ�te
            if (st.executeUpdate(req) == 1) {
                System.out.println("INSERT employe");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        showEmployes();
    }

    @FXML
    private void deleteButton() {
        try {
            String req = "DELETE FROM employe WHERE id_emp="+idField.getText()+"";
            Statement st = Connexion.getCn().createStatement();
            //�tape 4 : Ex�cution de la requ�te
            if (st.executeUpdate(req) == 1) {
                System.out.println(" employe est supprimer");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        showEmployes();
    }

    @FXML
    private void updateButton() {
        try {
            String req = "UPDATE `employe` SET `prenom` = '"
                    +prenomField.getText()+"', `nom` = '"
                    +nomField.getText()+"',`titre` = '"
                    +titreField.getText()+"', `Niveau` = '"
                    +niveauField.getText()+"',`salaire` = '"
                    +salaireField.getText()+"', `affectation` = '"
                    +affectationField.getText()+"' WHERE `employe`.`id_emp` = "+ idField.getText();

            Statement st = Connexion.getCn().createStatement();
            if (st.executeUpdate(req) == 1) {
                System.out.println(prenomField.getText()+" est modifier");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        showEmployes();
    }

    public ObservableList<Employe> getEmployeList() {
        ObservableList<Employe> EmployeList = FXCollections.observableArrayList() ;

        ResultSet rs ;
        try {
            String req = "select * from employe ";
            Statement st = Connexion.getCn().createStatement();
            rs = st.executeQuery(req);
            while(rs.next()) {
                Employe employer = new Employe(rs.getInt("id_emp"),
                        rs.getString("nom"),rs.getString("prenom"),
                        rs.getString("titre"),rs.getInt("affectation"),
                        rs.getString("niveau"),rs.getDouble("salaire"));
                EmployeList.add(employer);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur SQL select");
            ex.printStackTrace();
        }
        return  EmployeList;
    }

    @FXML
    private URL location;
    @FXML
    private ResourceBundle resources;
    public void initialize() {
        TableView.setEditable(true);
        showEmployes();
    }

    public void showEmployes() {
        ObservableList<Employe> list = getEmployeList();
        System.out.println();
        idColumn.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("id"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("prenom"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("nom"));
        titreColumn.setCellValueFactory(new PropertyValueFactory<Employe,String>("titre"));
        affectationColumn.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("affectation"));
        niveauColumn.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("niveau"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("salaire"));

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        titreColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableView.setItems(list);
    }


//    public void showButton(ActionEvent actionEvent) {
//        showEmployes();
//    }


//    public void pressButton(ActionEvent event){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
}

