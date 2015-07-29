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
import java.awt.event.*;

import javax.swing.*;

/**
 * Panel de los botones del umbral de binarizaci�n
 */
public class PanelBotonesUmbral extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Aceptar
     */
    public final static String ACEPTAR = "aceptar";

    /**
     * Cancelar
     */
    public final static String CANCELAR = "cancelar";

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Bot�n Aceptar
     */
    private JButton botonAceptar;

    /**
     * Bot�n Cancelar
     */
    private JButton botonCancelar;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Di�logo al que pertenece el panel
     */
    private DialogoUmbralBinarizacion dialogo;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de botones del umbral de binarizaci�n
     * @param elDialogo Di�logo al que pertenece este panel. elDialogo != null.
     */
    public PanelBotonesUmbral( DialogoUmbralBinarizacion elDialogo )
    {
        //Guarda la referencia al di�logo
        dialogo = elDialogo;

        //Establece el distribuidor gr�fico
        setLayout( new GridLayout( 1, 2 ) );

        //Crea e inicializa los elementos de la interfaz
        botonAceptar = new JButton( "Aceptar" );
        botonAceptar.setActionCommand( ACEPTAR );
        botonAceptar.addActionListener( this );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );

        //Adiciona los elementos al panel
        add( botonAceptar );
        add( botonCancelar );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Ejecuta las acciones de los elementos de la interfaz
     * @param evento Evento que gener� la acci�n. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( ACEPTAR ) )
        {
            dialogo.aceptar( );
        }
        if( comando.equals( CANCELAR ) )
        {
            dialogo.cancelar( );
        }
    }
}