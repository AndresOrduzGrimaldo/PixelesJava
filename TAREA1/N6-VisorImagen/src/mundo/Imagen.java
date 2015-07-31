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

package mundo;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.imageio.*;

/**
 * Imagen de mapa de colores
 */
public class Imagen
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Ancho m�ximo de la imagen
     */
    public static final int ANCHO_MAXIMO = 400;

    /**
     * Alto m�ximo de la imagen
     */
    public static final int ALTO_MAXIMO = 300;

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Matriz de colores de la imagen
     */
    private Color bitmap[][];

    /**
     * Ancho de la imagen
     */
    private int ancho;

    /**
     * Alto de la imagen
     */
    private int alto;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una imagen a partir de la ruta del archivo donde esta la imagen original en BMP. La imagen numera los p�xeles desde la esquina superior izquierda de la imagen con
     * (0,0). La coordenada X ve de 0 hasta el ancho-1 y la coordenada Y va de 0 a el alto-1 Si la imagen es de ancho mayor al ANCHO_MAXIMO o con altura mayor a ALTO_MAXIMO,
     * la imagen se recorta hasta los l�mites.
     * @param archivo Nombre y ruta del archivo. archivo != null.
     * @throws IOException Error al leer el archivo
     */
    public Imagen( String archivo ) throws IOException
    {
        bitmap = new Color[ALTO_MAXIMO][ANCHO_MAXIMO];
        cargarImagen( archivo );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna el color de un p�xel seg�n su ubicaci�n en la imagen
     * @param x Coordenada horizontal
     * @param y Coordenada vertical
     * @return el color del p�xel de coordenadas (x,y) o null en caso de que las coordenadas no est�n sobrepasen los l�mites de la imagen.
     */
    public Color darColorPixel( int x, int y )
    {
        if( x >= ancho || y >= alto )
            return null;
        else
            return bitmap[ y ][ x ];
    }

    /**
     * Retorna el alto en p�xeles de la imagen
     * @return alto
     */
    public int darAlto( )
    {
        return alto;
    }

    /**
     * Retorna el ancho en p�xeles de la imagen
     * @return ancho
     */
    public int darAncho( )
    {
        return ancho;
    }

    /**
     * Carga la imagen que se encuentra en el archivo
     * @param nombreArchivo - nombre y ruta del archivo
     * @throws IOException Error al cargar la imagen
     */
    private void cargarImagen( String nombreArchivo ) throws IOException
    {
        File archivo = new File( nombreArchivo );
        BufferedImage bmp;

        try
        {
            bmp = ImageIO.read( archivo );
        }
        catch( IOException e )
        {
            throw new IOException( "No se encuentra la imagen" );
        }

        if( bmp.getWidth( ) < ANCHO_MAXIMO )
            ancho = bmp.getWidth( );
        else
            ancho = ANCHO_MAXIMO;

        if( bmp.getHeight( ) < ALTO_MAXIMO )
            alto = bmp.getHeight( );
        else
            alto = ALTO_MAXIMO;

        for( int i = 0; i < alto; i++ )
            for( int j = 0; j < ancho; j++ )
            {
                bitmap[ i ][ j ] = new Color( bmp.getRGB( j, i ) );
            }
    }

    /**
     * Retorna el mapa de bits como una BufferdImage
     * @return imagen como objeto BufferedImage
     */
    public BufferedImage darImagenBuffer( )
    {
        BufferedImage imagen = new BufferedImage( ancho, alto, BufferedImage.TYPE_INT_RGB );
        for( int i = 0; i < alto; i++ )
            for( int j = 0; j < ancho; j++ )
            {
                imagen.setRGB( j, i, bitmap[ i ][ j ].getRGB( ) );
            }
        return imagen;
    }

    /**
     * Negativo de la imagen: El negativo se calcula cambiando cada componente RGB, tomando el valor absoluto de restarle al componente 255.
     */
    public void convertirNegativo( )
    {
        //Recorre la matriz y calcula los componentes del nuevo color
        // para asignar un nuevo color  a un pixel puedes hacer lo siguiente
        //donde r, g, b son enteros que representan los colores de un pixel
        //bitmap[ i ][ j ] = new Color( r, g, b );
        
        int r=0,g=0,b=0;
        int ra=0,ga=0,ba=0;
        
        for (int i=0;i<255;i++){
            for(int j =0;j<255;j++){
//                System.out.println(i);
//                System.out.println(j);
                if(bitmap[i][j]==null){
                  bitmap[i][j] = new Color(0,0,0);  
                }
                 r =bitmap[i][j].getRed();
                 g =bitmap[i][j].getGreen();
                 b =bitmap[i][j].getBlue();
                 ra = 255- r;
                 ga = 255- g;
                 ba = 255- b;
                
                                
                bitmap[i][j] = new Color (ra,ga,ba);
                
            }
        }
    }

    /**
     * Reflejar imagen: Consiste en intercambiar las columnas enteras de la imagen, de las finales a la iniciales
     */
    public void reflejarImagen( )
    {
        int r=0,g=0,b=0;
        
        for (int i=0;i<192;i++){
            for(int j =0;j<130;j++){
//                System.out.println(i);
//                System.out.println(j);
                if(bitmap[i][j]==null){
                  bitmap[i][j] = new Color(0,0,0);  
                }
                
                 r =bitmap[i][j].getRed();
                 g =bitmap[i][j].getGreen();
                 b =bitmap[i][j].getBlue();
                                
                bitmap[i][j] = new Color(bitmap[i][254-j].getRed(),bitmap[i][254-j].getGreen(),bitmap[i][254-j].getBlue());
                bitmap[i][254-j] = new Color(r,g,b);
            }
        }
        
    }

    /**
     * Binarizaci�n: Consiste en llevar cada p�xel de una imagen a negro o blanco. Para ello se requiere un umbral: si el color del p�xel est� por encima o igual se lleva a
     * blanco y si est� por debajo se lleva a negro.
     * @param umbral Umbral para la binarizaci�n.
     */
    public void binarizarImagen( double umbral )
    {
        //Recorre la matriz de la imagen. Aquellos puntos con color menor o
        // igual al umbral los lleva a blanco y los mayores al negro.
        //Para pintar un pixel de negro o blanco puedes utilizar lo siguiente. Ej:
        //bitmap[ i ][ j ] = Color.BLACK;
        //bitmap[ i ][ j ] = Color.WHITE;
        
        int r=0,g=0,b=0;
        int rr=0,qq=0,bb=0;
        
        for (int i=0;i<255;i++){
            for(int j =0;j<255;j++){
//                System.out.println(i);
//                System.out.println(j);
                if(bitmap[i][j]==null){
                  bitmap[i][j] = new Color(0,0,0);  
                }
                 r =bitmap[i][j].getRed();
                 g =bitmap[i][j].getGreen();
                 b =bitmap[i][j].getBlue();
                 
                 rr = (r+g+b)/3;
                 if(rr<umbral){
                     bitmap[i][j] = new Color(0,0,0);
                 }else{
                     bitmap[i][j] = new Color(255,255,255);
                 }
            }
                 
            
        }
        
    }

    /**
     * Pixelamiento: Consiste en dividir la imagen en peque�as regiones de p�xeles y para cada una de esas regiones cambiar el color de los p�xeles al color promedio de dicha
     * regi�n. En este ejemplo, la regi�n se dimensiona con los divisores m�s peque�os del ancho y el alto de la imagen
     */
    public void pixelarImagen( ){
    
        
        //Los p�xeles son divisores de las dimensiones de la imagen
        int anchoPixel = menorDivisorMayorAUno( ancho );
        int altoPixel = menorDivisorMayorAUno( alto );
        
        Color aux;
        //Recorre la matriz por regiones para modificarla
        for (int i =0;i<200;i+=anchoPixel){
            for (int k =0; k<200;k+=altoPixel){
                
                // MEJORAR SACAR EL COLOR PROMEDIO
                aux = this.colorPromedio(i, k, i+anchoPixel, k+altoPixel);
                for (int x =i; x<anchoPixel+i;x++){
                    for(int y=k;y<altoPixel+k;y++){
                        
                        bitmap[x][y]=aux;
                    }
                }
            }
        }
        
                
    }

    /**
     * Escala de grises: Para ello promedia los componentes de cada p�xel y crea un nuevo color donde cada componente (RGB) tiene el valor de dicho promedio
     */
    public void convertirAGrises( )
    {
        int r=0,g=0,b=0;
        int rr=0,qq=0,bb=0;
        
        for (int i=0;i<255;i++){
            for(int j =0;j<255;j++){
//                System.out.println(i);
//                System.out.println(j);
                if(bitmap[i][j]==null){
                  bitmap[i][j] = new Color(0,0,0);  
                }
                 r =bitmap[i][j].getRed();
                 g =bitmap[i][j].getGreen();
                 b =bitmap[i][j].getBlue();
                 
                 rr = (r+g+b)/3;
                 bitmap[i][j] = new Color(rr,rr,rr);
            }
        }
    }

    /**
     * Convoluci�n: Opera la imagen con la matriz de convoluci�n dada por el usuario
     * @param convolucion Matriz cuadrada de dimensi�n impar. convolucion != null.
     * @param dimension Dimensi�n de la matriz de convoluci�n. dimension es v�lido para el contenido de la matriz.
     */
    public void aplicarOperadorConvolucion( double[][] convolucion, int dimension )
    {
        //Obtiene una copia de la imagen original, pero con un marco
        //de p�xeles negros para operar f�cilmente las esquinas de la imagen
        //con la matriz de convoluci�n
        Color copiaBorde[][] = copiarConBorde( dimension / 2 );

        //Calcula la suma de los factores de convoluci�n
        double sumaConvolucion = 0;
        for( int i = 0; i < dimension; i++ )
            for( int j = 0; j < dimension; j++ )
                sumaConvolucion += convolucion[ i ][ j ];

        //Recorre la matriz de p�xeles para cambiar la imagen
        for( int i = 0; i < alto; i++ )
            for( int j = 0; j < ancho; j++ )
            {
                //Para cada p�xel realiza el c�lculo recorriendo la matriz de convoluci�n
                double sumaRed = 0;
                double sumaGreen = 0;
                double sumaBlue = 0;

                //La divisi�n se hace en la mayor�a de los casos (excepto en los bordes)
                //Restando sobre la suma de los factores de convoluci�n
                double divisor = sumaConvolucion;

                //La suma se hace con los p�xeles de la imagen original
                for( int k = -dimension / 2; k <= dimension / 2; k++ )
                    for( int l = -dimension / 2; l <= dimension / 2; l++ )
                    {
                        sumaRed += convolucion[ k + dimension / 2 ][ l + dimension / 2 ] * copiaBorde[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getRed( );
                        sumaGreen += convolucion[ k + dimension / 2 ][ l + dimension / 2 ] * copiaBorde[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getGreen( );
                        sumaBlue += convolucion[ k + dimension / 2 ][ l + dimension / 2 ] * copiaBorde[ i + k + dimension / 2 ][ j + l + dimension / 2 ].getBlue( );

                        //Si es un p�xel del borde no cuenta para el divisor
                        if( i + l < 0 || i + l > alto || j + k < 0 || j + k > ancho )
                            divisor -= convolucion[ k + dimension / 2 ][ l + dimension / 2 ];
                    }

                if( divisor > 0 )
                {
                    sumaRed /= divisor;
                    sumaGreen /= divisor;
                    sumaBlue /= divisor;

                    if( sumaRed > 255 )
                        sumaRed = 255;
                    else if( sumaRed < 0 )
                        sumaRed = 0;

                    if( sumaGreen > 255 )
                        sumaGreen = 255;
                    else if( sumaGreen < 0 )
                        sumaGreen = 0;

                    if( sumaBlue > 255 )
                        sumaBlue = 255;
                    else if( sumaBlue < 0 )
                        sumaBlue = 0;

                    //Cambia el p�xel en la imagen
                    bitmap[ i ][ j ] = new Color( ( int )sumaRed, ( int )sumaGreen, ( int )sumaBlue );
                }
                else
                {
                    if( sumaRed > 255 )
                        sumaRed = 255;
                    else if( sumaRed < 0 )
                        sumaRed = 0;

                    if( sumaGreen > 255 )
                        sumaGreen = 255;
                    else if( sumaGreen < 0 )
                        sumaGreen = 0;

                    if( sumaBlue > 255 )
                        sumaBlue = 255;
                    else if( sumaBlue < 0 )
                        sumaBlue = 0;

                    //Cambia el p�xel en la imagen
                    bitmap[ i ][ j ] = new Color( ( int )sumaRed, ( int )sumaGreen, ( int )sumaBlue );
                }
            }
    }

    /**
     * Retorna el color promedio de la imagen
     * @return color promedio de toda la imagen
     */
    public Color colorPromedio( )
    {   // este metodo quedo talcual y como venia
        
        return colorPromedio( 0, 0, ancho - 1, alto - 1 );
    }

    /**
     * Busca el color promedio de la regi�n de la imagen El color promedio es formado por los promedios de rojos, verdes y azules de cada p�xel
     * @param xInicial Coordenada x del p�xel de inicio.
     * @param yInicial Coordenada y del p�xel de inicio.
     * @param xFinal Coordenada x del p�xel final.
     * @param yFinal Coordenada y del p�xel final.
     * @return Color promedio de la regi�n.
     */
    private Color colorPromedio( int xInicial, int yInicial, int xFinal, int yFinal )
    {
        int valorMedioRojo = 0, valorMedioVerde = 0, valorMedioAzul = 0;
        int totalPixeles = ( xFinal - xInicial + 1 ) * ( yFinal - yInicial + 1 );

        //Recorre la regi�n para promediar los componentes de los colores
        for( int i = yInicial; i <= yFinal; i++ )
            for( int j = xInicial; j <= xFinal; j++ )
            {
                valorMedioRojo += bitmap[ i ][ j ].getRed( );
                valorMedioVerde += bitmap[ i ][ j ].getGreen( );
                valorMedioAzul += bitmap[ i ][ j ].getBlue( );
            }

        valorMedioRojo /= totalPixeles;
        valorMedioVerde /= totalPixeles;
        valorMedioAzul /= totalPixeles;
        return new Color( valorMedioRojo, valorMedioVerde, valorMedioAzul );
    }

    /**
     * Calcula el menor divisor del numero dado que sea mayor a 1.
     * @param numero al que se le buscaro el divisor.
     * @return menor divisor mayor a uno del numero
     */
    private int menorDivisorMayorAUno( int numero )
    {   
        //hacer aqui el metodo
        return 1;
    }

    /**
     * Cambia el color de los p�xeles de la regi�n al dado como par�metro
     * @param color Color de la nueva regi�n
     * @param xInicial Coordenada x del p�xel de inicio
     * @param yInicial Coordenada y del p�xel de inicio
     * @param xFinal Coordenada x del p�xel final
     * @param yFinal Coordenada y del p�xel final
     */
    private void cambiarColorRegion( Color color, int xInicial, int yInicial, int xFinal, int yFinal )
    {
        for( int i = yInicial; i <= yFinal && i < alto; i++ )
            for( int j = xInicial; j <= xFinal && j < ancho; j++ )
            {
                bitmap[ i ][ j ] = color;
            }
    }

    /**
     * Crea una copia de la imagen pero le adiciona un borde de p�xeles de color negro, esto con el fin de poder operar con m�s facilidad la matriz de convoluci�n con las
     * esquinas de la imagen, y sin alterar el resultado de los bordes
     * @param borde ancho en p�xeles del borde (sobre un lado)
     * @return copia de la imagen (mapa de colores)
     */
    private Color[][] copiarConBorde( int borde )
    {
        //Crea una copia de la imagen original que incluye un marco de p�xeles negros
        Color[][] copia = new Color[alto + 2 * borde][ancho + 2 * borde];

        
        return copia;
    }

    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo de extension 1
     * @return Respuesta 1
     */
    public String metodo1( )
    {
        
        int valorRojo = 0, valorVerde = 0, valorAzul = 0;
        
        Hashtable<Integer,Color> contenedor=new Hashtable<Integer,Color>();
        
        contenedor.put(1,bitmap[0][0]);
        
        for( int i = 0; i <= 192; i++ ){
            for( int j =1; j <= 130; j++ )
            {
                if(bitmap[i][j]==null){
                    bitmap[i][j]= new Color(0,0,0);
                }
                valorRojo = bitmap[ i ][ j ].getRed( );
                valorVerde = bitmap[ i ][ j ].getGreen( );
                valorAzul = bitmap[ i ][ j ].getBlue( );
        
                Enumeration k = contenedor.keys();
                int clave;
                Color valor;
                while(k.hasMoreElements()){
                    clave=(int)k.nextElement();
                    Enumeration e = contenedor.elements();
                    while(e.hasMoreElements()){
                        valor = (Color)e.nextElement();
                        
                        if(valor.getRed()==valorRojo&& valor.getBlue()==valorAzul&&valor.getGreen()==valorVerde){
                            contenedor.put(clave+1,valor);
                            contenedor.remove(clave, valor);
                        }
                    }
                }
                
                contenedor.put(1,bitmap[i][j]);
            }
        }
        Color moda = contenedor.get(contenedor.size()-1);
        for (int i=0;i<192;i++){
            for(int j =0;j<130;j++){
                bitmap[i][j]= moda;
            }
        }
        return moda.toString();
    }

    /**
     * M�todo de extensi�n 2
     * @return Respuesta 2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}