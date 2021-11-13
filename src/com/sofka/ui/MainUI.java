package com.sofka.ui;

import javax.swing.*;
import com.sofka.data.Words;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

public class MainUI {
    Words w;
    private JPanel rootPanel;
    private JTable showTable;
    private JButton search;
    private JButton add;
    private JLabel wordES;
    private JTextField textES;
    private JLabel anyText;
    private JTextField textEN;
    private JLabel wordEN;
    private JButton allDic;

    public MainUI() throws IOException {
        createTable();
        optionsTable();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void optionsTable() throws IOException {
        add.addActionListener(new Add(textES, textEN, showTable));
        search.addActionListener(new Search(textES, textEN, showTable));
        allDic.addActionListener(new Reset(showTable));
    }

    private void createTable() throws IOException {
        w = new Words();
        w.updateData(showTable);
    }
}

class Add implements ActionListener {
    Words w;
    private JTextField ES;
    private JTextField EN;
    private JTable showTableX;

    public Add(JTextField itES, JTextField itEN, JTable jt){
        ES = itES;
        EN = itEN;
        showTableX = jt;
    }

    public void actionPerformed(ActionEvent e) {
        w = new Words();
        String ESP = ES.getText().replaceAll(" ", "").toLowerCase(Locale.ROOT);
        String ENG = EN.getText().replaceAll(" ", "").toLowerCase(Locale.ROOT);
        try {
            if (ESP.length()!=0 && ENG.length()!=0) {
                w.setData(w.CapitalizeName(ESP)+","+w.CapitalizeName(ENG));
                w.updateData(showTableX);
                JOptionPane.showMessageDialog(null, "Palabra Agregada Correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Search implements ActionListener {
    Words w;
    private JTextField ES;
    private JTextField EN;
    private JTable showTableX;

    public Search(JTextField itES, JTextField itEN, JTable jt){
        ES = itES;
        EN = itEN;
        showTableX = jt;
    }

    public void actionPerformed(ActionEvent e) {
        w = new Words();
        String ESP = ES.getText().replaceAll(" ", "").toLowerCase(Locale.ROOT);
        String ENG = EN.getText().replaceAll(" ", "").toLowerCase(Locale.ROOT);
        try {
            if (ESP.length()!=0 || ENG.length()!=0) {
                if(ESP.length()>0 && ENG.length() >0) {
                    JOptionPane.showMessageDialog(null, "Elige solo una opción para buscar");
                } else {
                    if(ESP.length()!=0) {
                        w.seachData(w.CapitalizeName(ESP), showTableX, 1);
                    }
                    if(ENG.length()!=0) {
                        w.seachData(w.CapitalizeName(ENG), showTableX, 2);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debes rellenar algun campo para buscar");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Reset implements ActionListener {
    Words w;
    private JTable showTableX;

    public Reset(JTable jt){
        showTableX = jt;
    }

    public void actionPerformed(ActionEvent e) {
        w = new Words();
        try {
            w.updateData(showTableX);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}