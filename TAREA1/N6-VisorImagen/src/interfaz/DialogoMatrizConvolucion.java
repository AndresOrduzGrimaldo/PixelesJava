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
 * Di�logo para pedir la matriz de convoluci�n
 */
public class DialogoMatrizConvolucion extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Panel de la matriz
     */
    private PanelMatriz panelMatriz;

    /**
     * Panel de los botones
     */
    private PanelBotonesMatriz panelBotones;

    /**
     * Interfaz padre
     */
    private InterfazVisorImagen ventana;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el di�logo para los valores de la matriz de convoluci�n
     * @param laVentana Ventana de la interfaz de la cual hace parte este di�logo. laVentana != null.
     */
    public DialogoMatrizConvolucion( InterfazVisorImagen laVentana )
    {
        //Establece el distribuidor gr�fico
        setLayout( new BorderLayout( ) );

        //Almacena la referencia a la ventana a la cual pertenece el di�logo
        ventana = laVentana;

        //Crea y adiciona el panel de la imagen
        panelMatriz = new PanelMatriz( );
        add( panelMatriz, BorderLayout.CENTER );

        //Crea y adiciona el panel de botones
        panelBotones = new PanelBotonesMatriz( this );
        add( panelBotones, BorderLayout.SOUTH );

        setTitle( "Matriz de Convoluci�n" );
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
        double conv[][] = panelMatriz.darMatriz( );
        if( conv != null )
            ventana.aplicarOperadorConvolucion( conv );
        setVisible( false );
    }

    /**
     * Procesa el limpiar del panel de botones
     */
    public void limpiar( )
    {
        panelMatriz.limpiar( );
    }

    /**
     * Procesa el cancelar del panel de botones
     */
    public void cancelar( )
    {
        setVisible( false );
    }
}