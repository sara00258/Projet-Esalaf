package com.example.esalaf;

import com.exemple.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.Node;
import java.util.Optional;

import com.exemple.model.CreditDAO;
import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
import com.exemple.model.Credit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    public TextField customerNameTextField;
    public TextField amountTextField;

    @FXML
    private TextField nom;
    @FXML
    private TextField tele;
    @FXML
    private TableView<Produit> produitTab;
    @FXML
    private TableView<Produit> produitTable;
    @FXML
    private TableView<Credit> creditTableView;
    @FXML
    private TableView<Credit> myTableView;

    @FXML
    private TableColumn<Credit, Long> creditId;
    @FXML
    private TableColumn<Credit, Double> creditAmount;
    @FXML
    private TextField ProductNameTextField;
    @FXML
    private TextField clientNameTextField;
    @FXML
    private TextField clientPhoneTextField;
    @FXML
    private TableColumn<Client, Integer> clientNameColumn;
    @FXML
    private TableColumn<Produit, Integer> col_product_id;
    @FXML
    private TableColumn<Produit, String> col_product_name;
    @FXML
    private TableColumn<Produit, Double> col_product_price;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableColumn<Credit, Long> col1;

    @FXML
    private TableColumn<Credit, Double> col2;
    @FXML
    private TableColumn<Client, Integer> clientPhoneColumn;
    @FXML
    private TextField priceTextField;
    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private Button ok;
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<Produit, Integer> productIdColumn;

    @FXML
    private TableColumn<Produit, String> productNameColumn;

    @FXML
    private TableColumn<Produit, Double> priceColumn;

    @FXML
    private TextField nomTextField;
    @FXML
    private TextField teleTextField;
    @FXML
    private TableView<Client> clientTable;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField credit;

    @FXML
    private TextField produit;

    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, Long> col_id;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_tele;

    @FXML
    private TableView<Credit> creditTab;


    @FXML
    private TableColumn<Produit, Long> produitId;

    @FXML
    private TableColumn<Produit, String> produitName;

    @FXML
    private TableColumn<Produit, Double> produitPrice;
    private ClientDAO clientDAO;
    private CreditDAO creditDAO;
    private ProduitDAO produitDAO;
    private ProductService productService;
    private HelloController CreditDao;
    private CreditDAO creditDao;

    public HelloController() {
        // constructor code
    }
    public HelloController(CreditDAO creditDao) {
        this.creditDAO = creditDao; // inject the CreditDao object
    }
    public HelloController(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    public TableView<Credit> createCreditsTableView() {
        TableView<Credit> tableView = new TableView<>();

        // Set up columns and data for the table view
        TableColumn<Credit, Long> idColumn = new TableColumn<>("ID");
        TableColumn<Credit, String> customerColumn = new TableColumn<>("Customer");
        TableColumn<Credit, String> productColumn = new TableColumn<>("Product");
        TableColumn<Credit, Double> amountColumn = new TableColumn<>("Amount");


// Set cell value factories for the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Get the selection model of the table view
        TableView.TableViewSelectionModel<Credit> selectionModel = tableView.getSelectionModel();

        // Add a listener to the selection model to get the selected item
        selectionModel.selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Do something with the selected item
                System.out.println("Selected item: " + newSelection.toString());
            }
        });

        return tableView;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Welcome to E-Salaf");
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        col1.setCellValueFactory(new PropertyValueFactory<Credit, Long>("id_credit"));
        col2.setCellValueFactory(new PropertyValueFactory<Credit, Double>("montant"));

        col_product_id.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_produit"));
        col_product_name.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        col_product_price.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prix"));
        creditTab = new TableView<>();
        myTableView = createCreditsTableView();

        

        UpdateTable();
        UpdateCreditTable();
        UpdateProduitTable();
    }



    public void addCredit(Long id_credit, Double montant) {
        String DB_URL = "jdbc:mysql://localhost:3306/esalaf";
        String DB_USER = "root";
        String DB_PASSWORD= "";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO credit (id_credit, montant) VALUES (?, ?)");
            statement.setLong(1, id_credit);
            statement.setDouble(2, montant);
            statement.executeUpdate();
        } catch (SQLException e) {
            // handle the exception
            e.printStackTrace();
        }
    }

    public HelloController(ClientDAO clientDAO, CreditDAO creditDAO, ProduitDAO produitDAO, ProductService productService) {
        this.clientDAO = clientDAO;
        this.creditDAO = creditDAO;
        this.produitDAO = produitDAO;
        this.productService = productService;
    }

    @FXML
    protected void onSaveButtonClick() {
        Client cli = new Client(0L, nom.getText(), tele.getText());

        try {
            ClientDAO clidao = new ClientDAO();
            clidao.save(cli);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
    }


    @FXML
    protected void onSaveProduitClick() {
        Produit prd = new Produit(null, produit.getText(), Double.parseDouble(produit.getText()), 0);

        try {
            ProduitDAO prddao = new ProduitDAO();
            prddao.save(prd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateProduitTable();
    }


    public void UpdateTable() {
// Create a new TableView only if mytab is null
        if (mytab == null) {
            mytab = new TableView<>();
        }
// Populate the table with data
        ObservableList<Client> data = getDataClients();
        mytab.setItems(data);
    }

    public static ObservableList<Client> getDataClients() {
        ClientDAO clidao = null;

        try {
            clidao = new ClientDAO();
            List<Client> clients = clidao.getAll();
            return FXCollections.observableArrayList(clients);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateCreditTable() {
        myTableView = createCreditsTableView();
        col1.setCellValueFactory(new PropertyValueFactory<Credit, Long>("id_credit"));
        col2.setCellValueFactory(new PropertyValueFactory<Credit, Double>("montant"));

        myTableView.setItems(getDataCredits());
    }

    public static ObservableList<Credit> getDataCredits() {
        CreditDAO crddao = null;

        ObservableList<Credit> listfx = FXCollections.observableArrayList();
        try {
            crddao = new CreditDAO();
            listfx.addAll(crddao.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }


    public void UpdateProduitTable() {
        produitId.setCellValueFactory(new PropertyValueFactory<Produit, Long>("id_produit"));
        produitName.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
        produitPrice.setCellValueFactory(new PropertyValueFactory<Produit, Double>("prixUnitaire"));
        produitTab.setItems(getDataProduits());
    }
    @FXML
    protected void creditTableView() {
        creditId.setCellValueFactory(new PropertyValueFactory<Credit, Long>("id_credit"));
        creditAmount.setCellValueFactory(new PropertyValueFactory<Credit, Double>("montant"));
        try {
            creditTab.setItems(getCreditList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<Credit> getCreditList() throws SQLException {
        ObservableList<Credit> credits = FXCollections.observableArrayList();
        String dbUrl = "jdbc:mysql://localhost:3306/esalaf";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credit");
            var result = statement.executeQuery();
            while (result.next()) {
                Long id_credit = result.getLong("id_credit");
                Double montant = result.getDouble("montant");
                Credit credit = new Credit(id_credit, montant);
                credits.add(credit);
            }
        }
        return credits;
    }

    public static ObservableList<Produit> getDataProduits() {
        ProduitDAO prddao = null;
        ObservableList<Produit> listfx = FXCollections.observableArrayList();
        try {
            prddao = new ProduitDAO();
            listfx.addAll(prddao.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }



    @FXML
    public void onLogoutClick() {
        // Ajouter le code pour la déconnexion de l'utilisateur
        System.out.println("User logged out");
    }


    public void addClientButtonClicked(ActionEvent actionEvent) {
        String nom = nomTextField.getText();
        String tele = teleTextField.getText();

        // Validate the data
        if (nom == null || nom.trim().isEmpty()) {
            showAlert("Error", "Nom cannot be empty.");
            return;
        }

        if (tele == null || tele.trim().isEmpty()) {
            showAlert("Error", "Telephone cannot be empty.");
            return;
        }

        // Create a new client object
        Client newClient = new Client(nom, tele);

        // Add the new client to the table
        clientTable.getItems().add(newClient);

        // Clear the form fields
        nomTextField.clear();
        teleTextField.clear();

        // Show a success message
        showAlert("Success", "Client added successfully.");
    }

    private void showAlert(String error, String s) {
    }

    @FXML
    public void onCreditMenuClick(ActionEvent event) {
        // Call method to manage credit
        manageCredit();
    }

    // Method to manage credit
    @FXML
    private void manageCredit() {
        try {
            // Load the FXML file for the credit management view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreditManagement.fxml"));
            Parent root = loader.load();

            // Create a new stage for the credit management view
            Stage stage = new Stage();
            stage.setTitle("Manage Credit");
            stage.setScene(new Scene(root));

            // Set the stage to be modal so that it blocks input to other windows
            stage.initModality(Modality.APPLICATION_MODAL);

            // Set the stage to be resizable (optional)
            stage.setResizable(true);

            // Show the credit management view
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onProductsMenuClick(ActionEvent event) throws IOException, SQLException {
        // Call method to manage products
        manageProducts();
    }

    // Method to manage products
    public void manageProducts() throws IOException, SQLException {
        // Load the products view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("products.fxml"));
        Parent root = loader.load();
        ProductService productService = new ProductService(new ProduitDAO());

        // Get the products controller and set any necessary parameters
        Produit productsController = loader.getController();
        productsController.setProductService(productService); // Inject the product service

        // Create a new stage and show the products view
        Stage productsStage = new Stage();
        productsStage.setTitle("Manage Products");
        productsStage.setScene(new Scene(root));
        productsStage.show();
    }

    public void onClientsMenuClick(ActionEvent event) {
        // Call method to manage clients
        manageClients();
    }

    public void manageClients() {
        // Create a new stage for managing clients
        Stage stage = new Stage();
        stage.setTitle("Manage Clients");

        // Create a grid pane to hold the client management UI
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Add UI components for managing clients, such as labels, text fields, and buttons

        // Create a scene for the client management UI and set it on the stage
        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);

        // Show the stage for managing clients
        stage.show();
    }
    @FXML
    protected void onAddProductButtonClick(ActionEvent event) throws SQLException {
        String name = produitName.getText();
        String priceStr = produitPrice.getText();
        double price = Double.parseDouble(priceStr);

        // Create a new product
        Produit produit = new Produit( name, price);

        // Add the new product to the database
        ProduitDAO produitDAO = new ProduitDAO();
        produitDAO.save(produit);

        // Update the table
        UpdateProduitTable();
    }

    @FXML
    private void onEditProductButtonClick(ActionEvent event) {
        // Get the selected product from the table
        Produit selectedProduct = produitTab.getSelectionModel().getSelectedItem();

        // If no product is selected, show an error message and return
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to edit.");
            alert.showAndWait();
            return;
        }

        // Display a dialog to edit the selected product
        Dialog<Produit> dialog = new Dialog<>();
        dialog.setTitle("Edit Product");
        dialog.setHeaderText(null);

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Create the dialog content
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField(selectedProduct.getNom());
        TextField priceField = new TextField(Double.toString(selectedProduct.getPrix()));

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceField, 1, 1);

        // Enable/disable the save button depending on whether a name is entered
        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setDisable(true);

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the name field by default
        Platform.runLater(nameField::requestFocus);

        // Convert the result to a product object when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                return new Produit(selectedProduct.getId_produit(), name, price);
            }
            return null;
        });

        Optional<Produit> result = dialog.showAndWait();

        // If the user clicked the save button, update the product in the table
        result.ifPresent(product -> {
            int selectedIndex = produitTab.getSelectionModel().getSelectedIndex();
            produitList.set(selectedIndex, product);
            produitTab.getSelectionModel().select(selectedIndex);
        });
    }

    private ObservableList<Produit> produitList;

    public void initialize() {
        produitList = FXCollections.observableArrayList();
        // add some sample products to the list
        produitList.add(new Produit("Product 1", 10.0));
        produitList.add(new Produit("Product 2", 20.0));
        produitList.add(new Produit("Product 3", 30.0));

        // set the products to the table
        produitTable.setItems(produitList);
    }

    @FXML
    private void onDeleteProductButtonClick(ActionEvent event) {
        // Get the selected product from the table
        Produit selectedProduct = produitTable.getSelectionModel().getSelectedItem();

        // Show a confirmation dialog to ensure the user wants to delete the product
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the selected product?");
        alert.setContentText(selectedProduct.getNom());
        ObservableList<Produit> productData = FXCollections.observableArrayList();

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, delete the product
            productData.remove(selectedProduct);
        } else {
            // User clicked Cancel or closed the dialog, do nothing
            System.out.println("Deletion cancelled.");
        }
    }


    @FXML
    private void onAddCreditButtonClick() throws SQLException {
        String customerName = customerNameTextField.getText();
        String amountString = amountTextField.getText();
        double montant = Double.parseDouble(amountString);

        Long customerId = creditTableView.getSelectionModel().getSelectedItem().getId_credit();


        // Create a Credit object with the provided data and the retrieved customer id
        Credit credit = new Credit(customerId, montant);
        CreditDAO creditDao = new CreditDAO();
        creditDao.addCredit(credit.getId_credit(), credit.getMontant());


        // Refresh the table view to display the new credit
        ObservableList<Credit> creditList = creditTableView.getItems();
        if (creditList != null) {
            creditList.add(credit);
        } else {
            ObservableList<Credit> newCreditList = FXCollections.observableArrayList();
            newCreditList.add(credit);
            creditTableView.setItems(newCreditList);
        }

        // Clear the input fields
        customerNameTextField.clear();
        amountTextField.clear();
    }

        @FXML
        private void onEditCreditButtonClick(ActionEvent event) {
        Credit selectedCredit = creditTab.getSelectionModel().getSelectedItem();
        if (selectedCredit != null) {
            // Show the credit edit dialog
            // No credit selected, show an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Credit Selected");
            alert.setContentText("Please select a credit in the table.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCreditDialog.fxml"));
            Parent root = loader.load();
            EditCreditDialogController controller = loader.getController();
            controller.setCredit(selectedCredit);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit Credit");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Refresh the credit table after the edit dialog is closed
            creditTab.setItems(getDataCredits());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void onDeleteCreditButtonClick(ActionEvent event) {
        Credit selectedCredit = creditTab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete the selected credit?");
        alert.setContentText(String.valueOf(selectedCredit.getId_credit()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, delete the credit
            try {
                CreditDAO crddao = new CreditDAO();
                crddao.delete(selectedCredit);

                // Refresh the credit table after the delete operation
                creditTab.setItems(getDataCredits());
            } catch (SQLException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Unable to delete credit");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

}