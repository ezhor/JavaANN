package com.arensis.ann.main;

import com.arensis.ann.genetic.Noe;
import com.arensis.ann.neural.ExcepcionNeuronal;
import com.arensis.ann.neural.RedNeuronal;
import com.arensis.ann.processing.PostProcesadorColor;
import com.arensis.ann.processing.PostProcesadorColorEstricto;
import com.arensis.ann.trainning.EntrenadorConfig;
import com.arensis.ann.trainning.EntrenadorGenetico;
import com.arensis.ann.trainning.Entrenamiento;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Veronica implements InteligenciaArtificial{
    private JLabel label;

    public void nacer() throws Exception {
        EntrenadorGenetico entrenadorGenetico = getNuevoEntrenador();
        RedNeuronal<Integer> veronica;
        BufferedImage imagen;
        Noe noe = new Noe();

        iniciarVentana();
        for (int i = 1; i <= Integer.MAX_VALUE-1; i++) {
            System.out.print("Entrenando iteración "+i+"... ");
            entrenadorGenetico.entrenar(1);
            System.out.print("OK. ");
            veronica = entrenadorGenetico.getPoblacion().mejorIndividuo().getAlelo();
            System.out.print("Guardando alelo... ");
            noe.guardarAlelo(veronica, "D:\\Media\\Desktop\\Veronica.txt");
            System.out.print("OK. ");
            System.out.print("Generando imagen al "+entrenadorGenetico.getPoblacion().mejorIndividuo().getAptitud()+"% de precisión... ");
            imagen = generarImagen(100,100, veronica);
            try{
                ImageIO.write(imagen, "png", new File("D:\\Media\\Desktop\\veronica.png"));
            }catch (Exception e){
                System.err.println("Error al escribir fichero");
            }
            imagen = escalarImagen(imagen, 700, 700);
            label.setIcon(new ImageIcon(imagen));
            System.out.println("OK");
        }
    }

    private void iniciarVentana(){
        JFrame frame = new JFrame("Verónica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        this.label = new JLabel(new ImageIcon(), JLabel.CENTER);
        panel.add(label);
        frame.add(panel);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(800, 800));
    }

    private BufferedImage[] getImagenesBerd() throws IOException {
        File carpeta = new File("D:\\berd");
        BufferedImage[] imagenes = new BufferedImage[carpeta.listFiles().length];
        for (int i = 0; i < imagenes.length; i++) {
            imagenes[i] = ImageIO.read(carpeta.listFiles()[i]);
        }
        return imagenes;
    }

    private BufferedImage getImagenPequna() throws IOException {
        return ImageIO.read(new File("D:\\gato.jpg"));
    }

    private Entrenamiento<Integer>[] getEntrenamientoImagenPequena() throws IOException {
        BufferedImage imagen = getImagenPequna();
        Entrenamiento<Integer>[] entrenamientos = new Entrenamiento[imagen.getHeight()*imagen.getWidth()];
        getEntrenamientoImagen(imagen, entrenamientos);
        return  entrenamientos;
    }

    private void getEntrenamientoImagen(BufferedImage imagen, Entrenamiento[] entrenamientos) {
        double[] entradas;
        Integer salida;
        for (int i = 0; i < imagen.getWidth(); i++) {
            for (int j = 0; j < imagen.getHeight(); j++) {
                entradas = new double[2];
                entradas[0] = i;
                entradas[1] = j;
                salida = imagen.getRGB(i,j);
                entrenamientos[(i*imagen.getHeight())+j] = new Entrenamiento<>(entradas, salida);
            }
        }
    }

    private Entrenamiento[] getEntrenamientoPrimeraImagen() throws IOException {
        BufferedImage imagen = getImagenesBerd()[0];
        Entrenamiento[] entrenamientos = new Entrenamiento[imagen.getHeight()*imagen.getWidth()];
        getEntrenamientoImagen(imagen, entrenamientos);
        return  entrenamientos;
    }

    private EntrenadorGenetico getNuevoEntrenador() throws IOException {
        EntrenadorConfig config = new EntrenadorConfig();
        config.setNumeroNeuronasEntrada(2);
        config.setNumeroCapasOcultas(4);
        config.setNumeroNeuronasPorCapaOculta(3);
        config.setNumeroNeuronasSalida(1);
        config.setBias(2);
        config.setCache(false);
        config.setNumeroIndividuos(10);
        config.setPostProcesador(new PostProcesadorColorEstricto());
        config.setPorcentajeEntrenamientosAleatorios(100);
        return new EntrenadorGenetico<>(config, getEntrenamientoImagenPequena());
    }

    private BufferedImage generarImagen(int ancho, int alto, RedNeuronal<Integer> redNeuronal) throws ExcepcionNeuronal {
        BufferedImage imagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < alto; j++) {
                imagen.setRGB(i, j, redNeuronal.calcularSalidaProcesada((double)i,(double)j));
            }
        }
        return imagen;
    }

    public BufferedImage escalarImagen(BufferedImage imagen, int ancho, int alto) {
        BufferedImage imagenEscalada = new BufferedImage(ancho, alto, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(imagen, 0, 0, ancho, alto, null);
        g2d.dispose();
        return imagenEscalada;
    }
}
