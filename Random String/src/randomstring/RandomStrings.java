package randomstring;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

/**
 * This program muestra 25 copias de un mensaje.  El color y la
 * porsición de cada mensaje se calcula de forma aleatoria.  El
 * tipo de fuente se selecciona también de forma aleatoria de entre
 * cinco posibles. Los mensajes son mostrados en un fondo blanco.
 * Hay un boton para que el usuario haga clic y repinte los mensajes.
 */
public class RandomStrings extends Application {

    private final static String MESSAGE = "Hola JavaFX"; 

    private Font font1, font2, font3, font4, font5;  // The five fonts.
    
    private Canvas canvas;  // The canvas on which the strings are drawn.
    
    Font[] fonts; // Array de fuentes
    

    public static void main(String[] args) {
        launch(args);
    }
    

    public void start( Stage stage ) {
    	
    	
    	verFuentes();
    	
    	// Array que contine cinco fuentes
    	fonts = new Font[5];
    	
    	// Podemos crear un objeto con el métido estatico font de la clase Font
    	fonts[0] = 	Font.font("Times New Roman", FontWeight.BOLD, 20);
        fonts[1] =  Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 28);
        fonts[2] =  Font.font("Verdana", 32);
        fonts[3] =  Font.font(40);
        fonts[4] =  Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 60);
    	
        
        // Es un Node Node igual que Button, Label, Contenedores, etc.
        // No es un Parent -> no puede contener otros nodos. No puede ser root
        // del Scene, por tanto tiene que estar siempre dentro de un contenedor.
        
        canvas = new Canvas(500,300);
        draw();  // dibuja el contenido del canvas la primera vez.

        Button redrawButton = new Button("Redraw!");
        redrawButton.setOnAction( e -> draw() );

        // Contenedor StackPane coloca las cosas apiladas unas encima de otras
        // Coloca dentro el botón
        StackPane bottom = new StackPane(redrawButton);
        bottom.setPadding(new Insets(6,0,6,0));

        // Uso de css para aplicar estilos.

        //bottom.setStyle("-fx-background-color: gray; -fx-padding:5px;" + 
          //                  " -fx-border-color:black; -fx-border-width: 2px 0 0 0");
         
        //Crea un contenedor BorderPane y coloca el canvas en su parte central
        BorderPane root = new BorderPane(canvas);
        
        // Coloca en la parte inferior el contenedor stackpane con el botón.
        root.setBottom(bottom);
       
        // Aplica estilo: borde color negro y de 2px de grosor.
        root.setStyle("-fx-border-color:black; -fx-border-width: 2px");
        
        // Crea el Scene con el BorderPane como root
        stage.setScene( new Scene(root, Color.BLACK) );
        stage.setTitle("Random Strings");
        stage.setResizable(false);
        stage.show();
    }
    

    /**
     * El método draw() es el responsable de dibujar los mensajes en el canvas.
     * Dibuja 25 copias del mensaje, usando un color, fuente y posión aleatorios para  
     * cada string.
     */
    private void draw() {
    	
    	

    	// GraphicsContext contiene los métodos para pintar sobre el canvas.
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        // obtiene dimensiones del canvas.
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        
        g.setFill( Color.WHITE );  // llena el canvas con un fondo blanco
        g.fillRect(0, 0, width, height); // dimensiona y da forama rectanguar el canvas

        for (int i = 0; i < 25; i++) {

            // Pinta el string.  Primero, establece la fuente cogiendo
        	// un de forma aleatoria de entre las cinco guardadas en el array fonts

        	int fontNum = (int)(5*Math.random());
            g.setFont(fonts[fontNum]);
            

            // Establece el color con sistema HUE. Alternativa al sistema RGB, que determina el color con el tono, 
            //saturación y brillo.
            // hue (tono) alternativa a rgb. El tono es el color básico, que va desde el rojo al naranja pasando 
            //por todos los demás colores del arco iris.
            // Es un valor entre 0.0 y 360.0, segundo argumento es la saturación y tercer arg. es el brillo. Van de 0.0 a 1.0
            
            double hue = 360*Math.random();
            g.setFill( Color.hsb(hue, 1.0, 1.0) );

            // Establce posicón del string aleatoriamente.

            double x,y;
            x = -50 + Math.random()*(width+40);
            y = Math.random()*(height+20);

            // Pinta el mensaje

            g.fillText(MESSAGE,x,y);
            
            // Also stroke the outline of the strings with black
            
            g.setStroke(Color.BLACK);
            g.strokeText(MESSAGE,x,y);

        } // end for

    } // end draw()
    
    private void verFuentes() {
    	List<String> fontFamilies = Font.getFamilies();
    	List<String> fontNames    = Font.getFontNames();
    	
    	System.out.println("**********FAMILIAS DE FUENTE***********");
    	for(String f: fontFamilies)
    		System.out.println(f);
    	
    	System.out.println("**********NOMBRES DE FUENTE***********");

    	for(String f: fontNames)
    		System.out.println(f);
    }


}  // end class RandomStrings
