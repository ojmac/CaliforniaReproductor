package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.Cancion;

public class EditarPlayList extends JPanel {

    private ArrayList<Cancion> cancionesDisponibles;
    private ArrayList<Cancion> cancionesSeleccionadas;
    private JPanel panelIzquierdo, panelDerecho;
    private DefaultListModel<String> modelDisponibles, modelSeleccionadas;
    private JList<String> listaCancionesDisponibles, listaCancionesSeleccionadas;
    private JButton finalizarButton;
    private PlayLists playList;
    private VentanaPrincipal principal;
    private JTextField campoTexto;

    public EditarPlayList(PlayLists playList,VentanaPrincipal principal) {
        this.principal = principal;
        this.playList = playList;
        Cancion cancion = new Cancion(); 

        // Obtiene todas las canciones disponibles
        ArrayList<Cancion> todasLasCanciones = cancion.getCanciones();

        // Filtra las canciones que no están en la lista de seleccionadas
        cancionesSeleccionadas = playList.getCanciones();
        cancionesDisponibles = new ArrayList<>();
        for (Cancion c : todasLasCanciones) {
            if (!cancionesSeleccionadas.contains(c)) {
                cancionesDisponibles.add(c);
            }
        }

        // Crear los paneles
        crearPaneles();

        // Configurar el layout principal
        setLayout(new BorderLayout());
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearContenedorPaneles(), BorderLayout.CENTER);
        add(crearPanelBoton(), BorderLayout.SOUTH);
    
    }
    public JPanel crearPanelSuperior() {
    	JPanel panelSuperior = new JPanel();
        panelSuperior.setPreferredSize(new Dimension(600, 100));  // Establece las dimensiones del panel
        panelSuperior.setLayout(new BorderLayout());  // Usa BorderLayout para organizar los componentes

        // Crear el título
        JLabel titulo = new JLabel("Nombre de la Playlist");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);  // Centrar el texto del título
        titulo.setFont(new Font("Arial", Font.BOLD, 16));  // Establece una fuente más grande para el título
        panelSuperior.add(titulo, BorderLayout.NORTH);

        // Crear el área de texto
        campoTexto = new JTextField();
        campoTexto.setHorizontalAlignment(JTextField.CENTER);  // Centrar el texto dentro del JTextField
        campoTexto.setFont(new Font("Arial", Font.BOLD, 20));// Establece fuente en negrita y tamaño de letra más grande
    	campoTexto.setText(playList.getNombre());
        panelSuperior.add(campoTexto);	
    	return panelSuperior;
    }

    private void crearPaneles() {
        // Panel izquierdo (canciones disponibles)
        panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BorderLayout());

        modelDisponibles = new DefaultListModel<>();
        for (Cancion cancion : cancionesDisponibles) {
            if (!cancionesSeleccionadas.contains(cancion)) {
                modelDisponibles.addElement(cancion.getTitulo());
            }
        }

        listaCancionesDisponibles = new JList<>(modelDisponibles);
        listaCancionesDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCancionesDisponibles.setFont(new Font("Arial", Font.PLAIN, 14));
        listaCancionesDisponibles.setFixedCellHeight(30);

        // Añadir ListSelectionListener para mover de disponibles a seleccionadas
        listaCancionesDisponibles.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar dobles eventos
                    int selectedIndex = listaCancionesDisponibles.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Obtener la canción seleccionada
                        String cancionSeleccionada = modelDisponibles.getElementAt(selectedIndex);

                        // Mover la canción de disponibles a seleccionadas
                        modelDisponibles.remove(selectedIndex);
                        modelSeleccionadas.addElement(cancionSeleccionada);

                        // Añadirla a la lista de seleccionadas en el objeto Playlist
                        Cancion cancion = obtenerCancionPorTitulo(cancionSeleccionada);
                        cancionesSeleccionadas.add(cancion);
                        cancionesDisponibles.remove(cancion); // Eliminar de la lista disponible

                        System.out.println("Canción '" + cancionSeleccionada + "' movida a seleccionadas.");
                    }
                }
            }
        });

        JScrollPane scrollIzquierdo = new JScrollPane(listaCancionesDisponibles);
        panelIzquierdo.add(scrollIzquierdo, BorderLayout.CENTER);
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Canciones Disponibles"));

        // Panel derecho (canciones seleccionadas)
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BorderLayout());

        modelSeleccionadas = new DefaultListModel<>();
        for (Cancion cancion : cancionesSeleccionadas) {
            modelSeleccionadas.addElement(cancion.getTitulo());
        }

        listaCancionesSeleccionadas = new JList<>(modelSeleccionadas);
        listaCancionesSeleccionadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCancionesSeleccionadas.setFont(new Font("Arial", Font.PLAIN, 14));
        listaCancionesSeleccionadas.setFixedCellHeight(30);

        // Añadir ListSelectionListener para mover de seleccionadas a disponibles
        listaCancionesSeleccionadas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar dobles eventos
                    int selectedIndex = listaCancionesSeleccionadas.getSelectedIndex();
                    if (selectedIndex != -1) {
                        // Obtener la canción seleccionada
                        String cancionSeleccionada = modelSeleccionadas.getElementAt(selectedIndex);

                        // Mover la canción de seleccionadas a disponibles
                        modelSeleccionadas.remove(selectedIndex);
                        modelDisponibles.addElement(cancionSeleccionada);

                        // Eliminarla de la lista de seleccionadas en el objeto Playlist
                        cancionesSeleccionadas.removeIf(c -> c.getTitulo().equals(cancionSeleccionada));
                        Cancion cancion = obtenerCancionPorTitulo(cancionSeleccionada);
                        cancionesDisponibles.add(cancion); // Añadir a la lista disponible

                        System.out.println("Canción '" + cancionSeleccionada + "' movida a disponibles.");
                    }
                }
            }
        });

        JScrollPane scrollDerecho = new JScrollPane(listaCancionesSeleccionadas);
        panelDerecho.add(scrollDerecho, BorderLayout.CENTER);
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Canciones Seleccionadas"));
    }
    
    private Cancion obtenerCancionPorTitulo(String titulo){
        for (Cancion cancion : cancionesDisponibles) {
            if (cancion.getTitulo().equals(titulo)) {
                return cancion;
            }
        }
        for (Cancion cancion : cancionesSeleccionadas) {
            if (cancion.getTitulo().equals(titulo)) {
                return cancion;
            }
        }
        return null; // Retorna null si no se encuentra la canción
    }
    
    

    private JPanel crearContenedorPaneles() {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(1, 2));
        contenedor.add(panelIzquierdo);
        contenedor.add(panelDerecho);
        return contenedor;
    }

    private JPanel crearPanelBoton() {
        finalizarButton = new JButton("Finalizar");
        finalizarButton.setFont(new Font("Arial", Font.BOLD, 14));
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPlayList();
            }
        });
        JPanel panelBoton = new JPanel();
        panelBoton.add(finalizarButton);
        return panelBoton;
    }

    private void moverCancionASeleccionadas(Cancion cancionSeleccionada, int selectedIndex) {
        modelDisponibles.remove(selectedIndex);
        modelSeleccionadas.addElement(cancionSeleccionada.getTitulo());
        cancionesSeleccionadas.add(cancionSeleccionada);
        cancionesDisponibles.remove(selectedIndex);
    }

    private void finalizarPlayList() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de que deseas finalizar la playlist?",
            "Confirmar Playlist",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ArrayList<Cancion> playlist = obtenerPlaylist();
            playList.setCanciones(playlist);
            System.out.println("Playlist Finalizado:");
            for (Cancion cancion : playlist) {
                System.out.println(cancion.getTitulo());
            }
            playList.setNombre(this.campoTexto.getText()); 
            JOptionPane.showMessageDialog(this, "Playlist Finalizada.");
            VentanaCrearPlayList v = new VentanaCrearPlayList(principal);
            this.setVisible(false);
            principal.add(v);

        }
    }

    private ArrayList<Cancion> obtenerPlaylist() {
        return cancionesSeleccionadas;
    }
}