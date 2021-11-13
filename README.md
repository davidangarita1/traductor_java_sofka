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

Se creo una clase llamada "Main" que es la clase principal y definimos lo siguiente:

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