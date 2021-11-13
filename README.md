# Diccionario de Palabras Español/Ingles - Sofka

## ¿Con qué tecnologías fue creado?
* Java 17

## ¿Cómo funciona?
* Descargue el proyecto y en su IDE de preferencia presione "Run".
* Escriba en alguno de los cuadros de texto, "Español" o "English" dependiendo que palabra desea buscar su significado y luego dele click en el botón "Buscar".
* Para agregar una nueva palabra con su definición escriba en ambos campos la palabra con su respectivo significado y presione click en "Agregar Palabra".
* Para actualizar la lista o resetear el filtro presione el botón "Ver Todo".

## ¿Cómo fue creado?
Para este ejercicio se utilizo la herramienta GUIform del IDE IntelliJ idea con el que se puede crear una interface grafica de un formulario ejecutable.

1) Se creo una clase llamada "Main" que es la clase principal y definimos lo siguiente:

```java
public class Main {
	// función que determina donde inicia el programa
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

	// Aqui creamos el entorno de JFrame que es el formulario
    private static void createGUI() throws IOException {
        MainUI ui = new MainUI();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
```

2) Se creo un "package" llamado "ui" donde creamos el formulario con la herramienta "GUI Form" presionando click derecho en el "ui"/New/Swing UI Designer/GUI Form, posteriormente nos pedira un nombre y escribiremos MainUI.

3) Aqui nos mostrara la interfaz grafica del formulario y a la derecha tendremos un menu con los diferentes componentes que podremos agregar como lo son "JPanel", "JButton", "JLabel", etc. En la carpeta "ui" fueron creados dos archivos MainUI.form y MainUI.java.

4) Para el archivo MainUI.java crearemos 4 clases:

### MainUI
Esta es la clase principal donde se crean los componentes del formulario.

```java
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
```

### Add
Esta clase controla la función para agregar nuevas palabras.

```java
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
```

### Search
Esta clase se encarga de filtrar la palabra que se escribe en los campos.

```java
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
```

### Reset
Esta clase restaura la tabla con todos los registros.

```java
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
```