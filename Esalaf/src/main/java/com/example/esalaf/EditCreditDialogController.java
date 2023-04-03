package com.example.esalaf;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.exemple.model.Credit;
public class EditCreditDialogController {
    @FXML
    private TextField creditNameField;
    @FXML
    private TextField creditAmountField;

    private Stage dialogStage;
    private Credit credit;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;

        creditNameField.setText(String.valueOf(credit.getId_credit()));
        creditAmountField.setText(Double.toString(credit.getMontant()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            credit.setId_credit(Long.valueOf(creditNameField.getText()));
            credit.setMontant(Double.parseDouble(creditAmountField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (creditNameField.getText() == null || creditNameField.getText().length() == 0) {
            errorMessage += "Invalid credit name!\n";
        }
        if (creditAmountField.getText() == null || creditAmountField.getText().length() == 0) {
            errorMessage += "Invalid credit amount!\n";
        } else {
            try {
                Double.parseDouble(creditAmountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Invalid credit amount (must be a number)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
