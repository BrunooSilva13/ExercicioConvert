package com.bolso.example.exercicioconvert;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Conversor extends Application {

    private double taxaCambioDolar = 0.2051913;
    private double taxaCambioPesoArgentino = 53.7923615;
    private double taxaCambioPesoChileno = 207.971;
    private double taxaCambioLibrasEsterlinas = 0.1583682;
    private double taxaCambioEuro = 0.1858218;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Moedas");

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));

        Label lblMoedaOrigem = new Label("Moeda de Origem:");
        ComboBox<String> comboBoxMoedasOrigem = new ComboBox<>();
        comboBoxMoedasOrigem.getItems().addAll("BRL", "USD", "ARS", "CLP", "GBP", "EUR");
        comboBoxMoedasOrigem.setValue("BRL");

        Label lblMoedaDestino = new Label("Moeda de Destino:");
        ComboBox<String> comboBoxMoedasDestino = new ComboBox<>();
        comboBoxMoedasDestino.getItems().addAll("USD", "ARS", "CLP", "GBP", "EUR");
        comboBoxMoedasDestino.setValue("USD");

        Label lblValor = new Label("Valor:");
        TextField txtValor = new TextField();

        Label lblResultado = new Label();

        Button btnConverter = new Button("Converter");
        btnConverter.setOnAction(e -> {
            try {
                double valor = Double.parseDouble(txtValor.getText());
                double resultado = 0;

                String moedaOrigem = comboBoxMoedasOrigem.getValue();
                String moedaDestino = comboBoxMoedasDestino.getValue();

                if (moedaOrigem.equals(moedaDestino)) {
                    // Mesma moeda, não precisa converter, resultado é igual ao valor digitado
                    resultado = valor;
                } else if (moedaDestino.equals("BRL")) {
                    resultado = converterDeOutraMoedaParaBRL(valor, moedaOrigem);
                } else {
                    // Caso envolvendo duas moedas diferentes (não envolvendo BRL)
                    double valorEmBRL = converterDeOutraMoedaParaBRL(valor, moedaOrigem);
                    resultado = converterParaBRL(valorEmBRL, moedaDestino);
                }

                lblResultado.setText("Resultado: " + resultado);
            } catch (NumberFormatException ex) {
                lblResultado.setText("Valor inválido");
            }
        });

        // Ouvinte para atualizar o ComboBox de moeda de origem quando o valor do TextField é alterado
        txtValor.textProperty().addListener((observable, oldValue, newValue) -> {
            comboBoxMoedasOrigem.setValue(newValue); // Atualiza o valor do ComboBox com o novo valor do TextField
        });

        // Adicionar os elementos ao GridPane
        root.add(lblMoedaOrigem, 0, 0);
        root.add(comboBoxMoedasOrigem, 1, 0);
        root.add(lblMoedaDestino, 0, 1);
        root.add(comboBoxMoedasDestino, 1, 1);
        root.add(lblValor, 0, 2);
        root.add(txtValor, 1, 2);
        root.add(btnConverter, 0, 3, 2, 1); // Span duas colunas para ocupar toda a largura
        root.add(lblResultado, 0, 4, 2, 1); // Span duas colunas para ocupar toda a largura

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método para converter o valor de outra moeda para BRL
    private double converterDeOutraMoedaParaBRL(double valor, String moedaOrigem) {
        switch (moedaOrigem) {
            case "USD":
                return valor / taxaCambioDolar;
            case "ARS":
                return valor / taxaCambioPesoArgentino;
            case "CLP":
                return valor / taxaCambioPesoChileno;
            case "GBP":
                return valor / taxaCambioLibrasEsterlinas;
            case "EUR":
                return valor / taxaCambioEuro;
            default:
                return valor; // Retorna o mesmo valor se a moeda de origem for BRL
        }
    }

    // Método para converter o valor de BRL para outra moeda
    private double converterParaBRL(double valor, String moedaDestino) {
        switch (moedaDestino) {
            case "USD":
                return valor * taxaCambioDolar;
            case "ARS":
                return valor * taxaCambioPesoArgentino;
            case "CLP":
                return valor * taxaCambioPesoChileno;
            case "GBP":
                return valor * taxaCambioLibrasEsterlinas;
            case "EUR":
                return valor * taxaCambioEuro;
            default:
                return valor; // Retorna o mesmo valor se a moeda de destino for BRL
        }
    }
}



