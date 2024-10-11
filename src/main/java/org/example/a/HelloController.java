package org.example.a;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
/**
 * Controlador de la interfaz de usuario para la aplicación Hello.
 * Este controlador gestiona la interacción del usuario con los componentes de la interfaz
 * y la lógica detrás de las acciones del usuario.
 */
public class HelloController implements Initializable {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private CheckBox checkBoxPregunta;


    @FXML
    private ComboBox<String> comboBoxEdad;

    @FXML
    private ListView<String> listViewCual;

    @FXML
    private RadioButton rbHombre;

    @FXML
    private RadioButton rbMujer;

    @FXML
    private RadioButton rbOtro;

    @FXML
    private Slider sliderCine;

    //  private double valorSliderCine;

    @FXML
    private Slider sliderCompras;

    //   private double valorSliderCompras;

    @FXML
    private Slider sliderTele;

    //  private double valorSliderTele;

    @FXML
    private TextField txtHermanos;

    @FXML
    private TextField txtProfesion;

    @FXML
    private ToggleGroup grpSexo;

    // Métodos de control de entrada

    /**
     * Verifica si una cadena dada representa un número.
     *
     * @param cadena La cadena a verificar.
     * @return true si la cadena es un número, false en caso contrario.
     */
    private boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    /**
     * Maneja el evento de clic en el botón "Aceptar".
     * Muestra un diálogo con los datos del usuario, o un mensaje de error si hay entradas inválidas.
     *
     * @param event El evento de acción.
     */
    @FXML
    void mostrarDialgo(ActionEvent event) {


        String profesion = txtProfesion.getText(); // Obtener el valor del campo profesión
        String hermanos = txtHermanos.getText();
        String edad = comboBoxEdad.getSelectionModel().getSelectedItem();
        String sports;
        
        if (profesion.isEmpty() || esNumero(profesion) || hermanos.isEmpty() || !esNumero(hermanos) || (checkBoxPregunta.isSelected() && selectedSports.isEmpty())){

            if (profesion.isEmpty() || esNumero(profesion)) {
                profesion = "Hay que rellenar el campo profesion";
            } else {
                profesion = "";
            }
            if (hermanos.isEmpty() || !esNumero(hermanos)) {
                hermanos = "Hay que rellenar el campo numero de hermanos";
            } else {
                hermanos = "";
            }
            if (checkBoxPregunta.isSelected() && selectedSports.isEmpty()) {
                sports = "Tienes que indicar los deportes que practicas";
            } else {
                sports = "";
            }

            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("TUS DATOS");
            alertaError.setHeaderText(null);
            alertaError.setContentText(profesion + "\n" + hermanos + "\n" + sports);
            alertaError.showAndWait();

        } else {

            // control de sexo , asigno a una variable los valores cuando el radio button is selected
            String textoSexo;
            if (rbHombre.isSelected()) {
                textoSexo = " Hombre";
            } else if (rbMujer.isSelected()) {
                textoSexo =  " Mujer";
            } else {
                textoSexo = " Otro";
            }

            // control del checkbox sobre si practica algun deporte
            String valores = " ";
            if (checkBoxPregunta.isSelected()) {
                if (!selectedSports.isEmpty()) {
                    for (String valor : selectedSports) {
                        valores = valores + valor + "\n";
                    }
                } else {
                    valores = "Tienes que indicar los deportes que practicas";
                }
            }

            // Sliders value
            double valorCompras = sliderCompras.getValue();
            double valorTele = sliderTele.getValue();
            double valorCine = sliderCine.getValue();

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String valorFormateadoCompras = decimalFormat.format(valorCompras);
            String valorFormateadoTele = decimalFormat.format(valorTele);
            String valorFormateadoCine = decimalFormat.format(valorCine);

            // Definicion de la ventana INFORMATION
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Tus datos:");
            alerta.setHeaderText(null);
            alerta.setContentText(" Profesión: " + profesion+"\n Nº de hermanos: " + hermanos+"\n edad : "+ edad +"\n sexo"
                    + textoSexo+ "\n Deportes que practicas: \n"+valores.toString()+"Grado de aficción a las compras : "+valorFormateadoCompras+
                    "\n Grado de aficción a ver televisión : " + valorFormateadoTele + "\n Grado de aficción ir al cine : " + valorFormateadoCine);
            alerta.showAndWait();
            selectedSports.clear();

        }
    }


    ArrayList<String> selectedSports = new ArrayList<String>();
    /**
     * Maneja el evento de selección de un deporte en la lista.
     * Agrega el deporte seleccionado a la lista de deportes.
     *
     * @param event El evento de clic del ratón.
     */

    @FXML
    void seleccionarDeporte(MouseEvent event) {
        String deporteSeleccionado = listViewCual.getSelectionModel().getSelectedItem().toString();
        if (!selectedSports.contains(deporteSeleccionado)) {
            selectedSports.add(deporteSeleccionado);
        }
    }
    /**
     * Maneja el evento de selección de edad en el comboBox.
     *
     * @param event El evento de acción.
     */
    @FXML
    void seleccionarEdad(ActionEvent event) {
        String EdadSeleccionado = comboBoxEdad.getSelectionModel().getSelectedItem().toString();

    }
    /**
     * Inicializa los componentes de la interfaz de usuario al cargar la vista.
     * Establece los elementos para el ComboBox de edad y el ListView de deportes.
     *
     * @param url La URL de la localización de recursos.
     * @param rb  El conjunto de recursos para la interfaz de usuario.
     */
    public void initialize(URL url, ResourceBundle rb) {

        // Configuración del ComboBox de edad

        ObservableList<String> listaComboBox = FXCollections.observableArrayList("Menores de 18","Enrtre 18 y 30","Entre 31 y 50","Entre 51 y 70","Mayores de 70");
        comboBoxEdad.setItems(listaComboBox);
        // Configuración del ListView de deportes
        ObservableList<String> listaViewList = FXCollections.observableArrayList("Tenis","Fútbal","Baloncesto","Natación","Ciclismo","otro");
        listViewCual.setItems(listaViewList);
        listViewCual.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Configuración del botón "Cancelar" para cerrar la aplicación
        btnCancelar.setOnAction(e -> Platform.exit());
    }

    public Button getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(Button btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public RadioButton getRbOtro() {
        return rbOtro;
    }

    public void setRbOtro(RadioButton rbOtro) {
        this.rbOtro = rbOtro;
    }

    public ToggleGroup getGrpSexo() {
        return grpSexo;
    }

    public void setGrpSexo(ToggleGroup grpSexo) {
        this.grpSexo = grpSexo;
    }
}