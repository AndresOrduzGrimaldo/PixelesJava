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
 * Panel donde se entra el valor del umbral para la binarizaci�n
 */
public class PanelUmbral extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Campo para indicar el umbral
     */
    private JTextField txtUmbral;

    /**
     * Etiqueta umbral
     */
    private JLabel labUmbral;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel para recibir el umbral de binarizaci�n
     */
    public PanelUmbral( )
    {
        //Establece el distribuidor gr�fico
        setLayout( new GridLayout( 1, 2 ) );

        //Crea, inicializa los elementos de la interfaz y adiciona los componentes gr�ficos
        txtUmbral = new JTextField( );
        txtUmbral.setForeground( Color.BLUE );
        labUmbral = new JLabel( "Umbral:" );
        labUmbral.setHorizontalAlignment( JLabel.CENTER );
        add( labUmbral );
        add( txtUmbral );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna el valor del umbral
     * @return umbral Retorna el valor dado por el usuario, o -1 si no es un valor v�lido
     */
    public double darUmbral( )
    {
        try
        {
            return Double.parseDouble( txtUmbral.getText( ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Umbral inv�lido: " + txtUmbral.getText( ), "Umbral de Binariaaci�nMatriz de Convoluci�n", JOptionPane.ERROR_MESSAGE );
            txtUmbral.setText( "0" );
            return -1;
        }
    }

    /**
     * Asigna un nuevo umbral al respectivo campo de texto
     * @param nuevoUmbral
     */
    public void asignarUmbral( double nuevoUmbral )
    {
        txtUmbral.setText( nuevoUmbral + "" );
    }
}