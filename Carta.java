
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author derly
 */
public class Carta { 
// Clase que representa una carta con sus atributclass Carta {
    // Atributos de la carta
    private int atk; // Ataque de la carta, entre 100 y 1000
    private int def; // Defensa de la carta, entre 100 y 1000
    private String elemento; // Elemento de la carta, entre Fuego, Viento, Rayo, Tierra y Agua
    private String habilidad; // Habilidad especial de la carta, que se activa al atacar o defender

    // Constructor de la carta
    public Carta(int atk, int def, String elemento, String habilidad) {
        this.atk = atk;
        this.def = def;
        this.elemento = elemento;
        this.habilidad = habilidad;
    }

    // Métodos para obtener los atributos de la carta
    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public String getElemento() {
        return elemento;
    }

    public String getHabilidad() {
        return habilidad;
    }

    // Método para mostrar la información de la carta
    public void mostrar() {
        System.out.println("ATK: " + atk + " | DEF: " + def + " | Elemento: " + elemento + " | Habilidad: " + habilidad);
    }
}

// Clase que representa un mazo de cartas
class Mazo {
    // Atributo que almacena las cartas del mazo
    private ArrayList<Carta> cartas;

    // Constructor del mazo
    public Mazo() {
        cartas = new ArrayList<Carta>();
    }

    // Método para agregar una carta al mazo
    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    // Método para obtener una carta del mazo por su índice
    public Carta getCarta(int indice) {
        return cartas.get(indice);
    }

    // Método para eliminar una carta del mazo por su índice
    public void eliminarCarta(int indice) {
        cartas.remove(indice);
    }

    // Método para obtener el tamaño del mazo
    public int getTamaño() {
        return cartas.size();
    }

    // Método para barajar el mazo
    public void barajar() {
        Collections.shuffle(cartas);
    }

    // Método para mostrar las cartas del mazo
    public void mostrar() {
        for (int i = 0; i < cartas.size(); i++) {
            System.out.print("Carta " + (i + 1) + ": ");
            cartas.get(i).mostrar();
        }
    }
}

// Clase que representa un jugador con su mazo y sus puntos de vida
class Jugador {
    // Atributos del jugador
    private Mazo mazo; // El mazo del jugador
    private int vida; // Los puntos de vida del jugador
    private boolean paralizado; // Indica si el jugador está paralizado por una habilidad

    // Constructor del jugador
    public Jugador(Mazo mazo) {
        this.mazo = mazo;
        this.vida = 1000; // Cada jugador empieza con 1000 puntos de vida
        this.paralizado = false; // Cada jugador empieza sin estar paralizado
    }

    // Métodos para obtener los atributos del jugador
    public Mazo getMazo() {
        return mazo;
    }

    public int getVida() {
        return vida;
    }

    public boolean getParalizado() {
        return paralizado;
    }

    // Método para restar puntos de vida al jugador
    public void restarVida(int daño) {
        vida -= daño;
    }

    // Método para sumar puntos de vida al jugador
    public void sumarVida(int cura) {
        vida += cura;
    }

    // Método para cambiar el estado de parálisis del jugador
    public void setParalizado(boolean estado) {
        paralizado = estado;
    }

    // Método para mostrar la información del jugador
    public void mostrar() {
        System.out.println("Vida: " + vida);
        System.out.println("Mazo:");
        mazo.mostrar();
    }
}

// Clase principal del juego
 class JuegoDeCartas {

    // Método para generar un número aleatorio entre un mínimo y un máximo
    public static int generarAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Método para generar un elemento aleatorio entre Fuego, Viento, Rayo, Tierra y Agua
    public static String generarElemento() {
        String[] elementos = {"Fuego", "Viento", "Rayo", "Tierra", "Agua"};
        int indice = generarAleatorio(0, 4);
        return elementos[indice];
    }

    // Método para generar una habilidad aleatoria entre varias opciones
    public static String generarHabilidad() {
        String[] habilidades = {"Doble ataque", "Escudo", "Curación", "Veneno", "Parálisis", "Robo de vida", "Inmunidad", "Reflejo", "Furia", "Sacrificio"};
        int indice = generarAleatorio(0, 9);
        return habilidades[indice];
    }

    // Método para generar una carta aleatoria con valores entre 100 y 1000 para atk y def, y un elemento y una habilidad aleatorios
    public static Carta generarCarta() {
        int atk = generarAleatorio(100, 1000);
        int def = generarAleatorio(100, 1000);
        String elemento = generarElemento();
        String habilidad = generarHabilidad();
        Carta carta = new Carta(atk, def, elemento, habilidad);
        return carta;
    }

    // Método para generar el mazo general con 10 cartas aleatorias
    public static Mazo generarMazoGeneral() {
        Mazo mazoGeneral = new Mazo();
        for (int i = 0; i < 10; i++) {
            Carta carta = generarCarta();
            mazoGeneral.agregarCarta(carta);
        }
        return mazoGeneral;
    }

    // Método para repartir las cartas del mazo general a los mazos de los jugadores
    public static void repartirCartas(Mazo mazoGeneral, Mazo mazoJugador1, Mazo mazoJugador2) {
        mazoGeneral.barajar(); // Se baraja el mazo general
        for (int i = 0; i < 10; i++) {
            Carta carta = mazoGeneral.getCarta(i); // Se obtiene la carta del mazo general
            if (i % 2 == 0) { // Si el índice es par, se agrega al mazo del jugador 1
                mazoJugador1.agregarCarta(carta);
            } else { // Si el índice es impar, se agrega al mazo del jugador 2
                mazoJugador2.agregarCarta(carta);
            }
        }
    }

    // Método para determinar si un elemento es más fuerte que otro, según el gráfico de elementos
    public static boolean esMasFuerte(String elemento1, String elemento2) {
        // Se asume que el elemento1 es más fuerte que el elemento2 por defecto
        boolean masFuerte = true;
        // Se verifica cada caso posible según el gráfico de elementos
        if (elemento1.equals("Fuego") && (elemento2.equals("Tierra") || elemento2.equals("Agua"))) {
            masFuerte = false;
        }
        if (elemento1.equals("Viento") && (elemento2.equals("Fuego") || elemento2.equals("Rayo"))) {
            masFuerte = false;
        }
        if (elemento1.equals("Rayo") && (elemento2.equals("Viento") || elemento2.equals("Tierra"))) {
            masFuerte = false;
        }
        if (elemento1.equals("Tierra") && (elemento2.equals("Rayo") || elemento2.equals("Agua"))) {
            masFuerte = false;
        }
        if (elemento1.equals("Agua") && (elemento2.equals("Fuego") || elemento2.equals("Viento"))) {
            masFuerte = false;
        }
        return masFuerte;
    }

    // Método para calcular el daño que recibe un jugador al ser atacado por otro jugador con una carta
    public static int calcularDaño(Jugador atacante, Jugador defensor, Carta cartaAtacante, Carta cartaDefensor) {
        // Se obtienen los atributos de las cartas
        int atkAtacante = cartaAtacante.getAtk();
        String carta = null;
        String elementoAtacante = carta