package com.sofka.data;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sofka.ui.MainUI;

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
}
