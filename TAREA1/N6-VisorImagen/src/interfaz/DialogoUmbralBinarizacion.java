/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_visorImagen 
 * Autor: Katalina Marcos
 * Modificaci�n: Mario S�nchez - 28/06/2005
 * Modificaci�n: Pablo Barvo - 1-Sep-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package interfaz;

import java.awt.*;

import javax.swing.*;

/**
 * Di�logo para pedir el umbral para la binarizaci�n
 */
public class DialogoUmbralBinarizacion extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Panel del umbral
     */
    private PanelUmbral panelUmbral;

    /**
     * Panel de los botones
     */
    private PanelBotonesUmbral panelBotones;

    /**
     * Interfaz padre
     */
    private InterfazVisorImagen ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el di�logo para el umbral de la binarizaci�n
     * @param laVentana Ventana del di�logo
     */
    public DialogoUmbralBinarizacion( InterfazVisorImagen laVentana){
        //Establece el distribuidor gr�fico
        setLayout( new BorderLayout( ) );

        //Establece el tama�o de la ventana
        setPreferredSize( new Dimension( 190, 80 ) );

        //Asigna la referencia a la ventana del programa
        ventana = laVentana;

        //Crea, inicializa y adiciona el panel
        panelUmbral = new PanelUmbral( );

        //Sugiere como umbral el color promedio de toda la imagen
        Color promedio = ventana.colorPromedio( );
        double umbral = ( promedio.getBlue( ) + promedio.getGreen( ) + promedio.getRed( ) ) / 3;
        panelUmbral.asignarUmbral( umbral );
        add( panelUmbral, BorderLayout.CENTER );

        //Crea y adiciona el panel de botones
        panelBotones = new PanelBotonesUmbral( this );
        add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Umbral de binarizacion" );
        pack( );
        setResizable( false );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Procesa el aceptar del panel de botones
     */
    public void aceptar( )
    {
        double umbral = panelUmbral.darUmbral( );
        if( umbral != -1 )
            ventana.binarizarImagen( umbral );
        setVisible( false );
    }

    /**
     * Procesa el cancelar del panel de botones
     */
    public void cancelar( )
    {
        setVisible( false );
    }
}