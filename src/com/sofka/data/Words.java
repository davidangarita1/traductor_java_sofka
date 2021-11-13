package com.sofka.data;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Words {
    private File fileTxt = new File("archivo.txt");
    private BufferedReader br = null;
    private FileReader fr = null;
    private FileWriter fw = null;
    private BufferedWriter bw = null;

    public void Document() {
        if(!fileTxt.exists()) {
            try {
                fileTxt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, String> read() throws IOException {
        String[] text;
        Map<String, String> dic = new HashMap<>();

        fr = new FileReader(fileTxt);
        br = new BufferedReader(fr);

        String cont;
        while ((cont = br.readLine()) != null) {
            text = cont.split(",");
            dic.put(text[0], text[1]);
        }
        br.close();
        fr.close();

        return dic;
    }

    public void setData(String dic) throws IOException {
        try {
            fw = new FileWriter(fileTxt, true);
            bw = new BufferedWriter(fw);
            bw.write(dic);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.close();
            fw.close();
        }
    }

    public void getData(JTable table, Object[][] data) {
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"Español", "English"}
        ));
        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setMinWidth(200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
    }

    public void updateData(JTable table) throws IOException {
        Object[][] data = readData().toArray(new Object[0][]);
        getData(table, data);
    }

    public void seachData(String word, JTable table, int num) throws IOException {
        Object[][] data = readData().toArray(new Object[0][]);

        switch (num) {
            case 1:
                for (int i = 0; i < data.length; i++) {
                    if (data[i][0].equals(word)) {
                        Object[] row = {data[i][0], data[i][1]};
                        Object[][] newData = {row};
                        getData(table, newData);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < data.length; i++) {
                    if (data[i][1].equals(word)) {
                        Object[] row = {data[i][0], data[i][1]};
                        Object[][] newData = {row};
                        getData(table, newData);
                    }
                }
        }


    }

    public List<String[]> readData() throws IOException {
        int count = 0;
        String file = "archivo.txt";
        List<String[]> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line.split(","));
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }
        return content;
    }

    public String CapitalizeName(String name) {
        String result = "";
        String firstLetStr = name.substring(0, 1);
        String remLetStr = name.substring(1);

        firstLetStr = firstLetStr.toUpperCase();
        result = firstLetStr + remLetStr;

        return result;
    }
}
