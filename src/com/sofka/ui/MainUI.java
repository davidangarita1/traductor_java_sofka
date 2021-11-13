package com.sofka.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import com.sofka.data.Words;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    public MainUI() throws IOException {
        w = new Words();
        Object[][] data = w.readData().toArray(new Object[0][]);
        createTable(data);
        optionsTable();
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void optionsTable() {
        w = new Words();
        add.addActionListener(new Esp(textES, textEN));
    }

    private void createTable(Object[][] data) throws IOException {
        showTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Español", "English"}
        ));
        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMinWidth(200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
    }
}

class Esp implements ActionListener {
    Words w;
    private JTextField ES;
    private JTextField EN;
    public Esp(JTextField itES, JTextField itEN){
        ES = itES;
        EN = itEN;
    }

    public void actionPerformed(ActionEvent e) {
        w = new Words();
        try {
            w.setData(ES.getText()+","+EN.getText());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}