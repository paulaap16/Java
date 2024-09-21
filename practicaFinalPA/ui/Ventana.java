package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame
{
    private final static Color MORADO = new Color(145, 145, 233); //para el fondo
    private final static Color AMARILLO = new Color(252, 237, 108); //para el fondo
    private Tablero tablero;   
    private JButton crear, limpiar, resolver, comprobar;
    private JLabel titulo;
    
    
    public Ventana()
    {
        super("Sudoku");
        
        JPanel fondo = new JPanel();
        JPanel tit = new JPanel(new FlowLayout(FlowLayout.CENTER));

        tablero = new Tablero();

        crear = new JButton("NUEVO");
        crear.setSize(70, 50);
        crear.setFont(new Font("Bertram Let", Font.BOLD, 14));
        crear.setBackground(new Color(202, 202, 202));
        crear.setForeground(Color.BLACK);
        crear.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
                    tablero.borrarTodo();
					tablero.generarSudoku();
				}
			});

        limpiar = new JButton("LIMPIAR");
        limpiar.setSize(70, 50);
        limpiar.setFont(new Font("Bertram Let", Font.BOLD, 14));
        limpiar.setBackground(new Color(202, 202, 202));
        limpiar.setForeground(Color.BLACK);
        limpiar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
                    tablero.limpiarSudoku();
				}
			});

        resolver = new JButton("RESOLVER");
        resolver.setSize(70, 50);
        resolver.setFont(new Font("Bertram Let", Font.BOLD, 14));
        resolver.setBackground(new Color(202, 202, 202));
        resolver.setForeground(Color.BLACK);
        resolver.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
                    tablero.resolverSudoku();
					
				}
			});

        comprobar = new JButton("COMPROBAR");
        comprobar.setSize(70, 50);
        comprobar.setFont(new Font("Bertram Let", Font.BOLD, 14));
        comprobar.setBackground(new Color(202, 202, 202));
        comprobar.setForeground(Color.BLACK);
        comprobar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
                    tablero.comprobarSudoku();
					
				}
			});

        titulo = new JLabel("SUDOKU - ICAI");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.BLACK);

        tit.add(titulo, BorderLayout.CENTER);

        JCheckBox ayuda = new JCheckBox("AYUDA");
        ayuda.setFont(new Font("Arial", Font.BOLD, 14));
        ayuda.setBackground(new Color(202, 202, 202));
        ayuda.setForeground(Color.BLACK);
        ayuda.addItemListener(new ItemListener() 
        {
             @Override
             public void itemStateChanged(ItemEvent e) 
             {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                {
                    System.out.println("Opción 1 seleccionada");
                    tablero.corrector();
                 } 
                 else 
                 {
                    System.out.println("Opción 1 deseleccionada");
                 }
            }
            
        });

        fondo.add(crear);
        crear.setBounds(35,20,100,30);
        fondo.add(limpiar);
        limpiar.setBounds(145,20,100,30);
        fondo.add(resolver);
        resolver.setBounds(252,20,115,30);
        fondo.add(comprobar);
        comprobar.setBounds(377,20,135,30);
        fondo.add(ayuda);
        ayuda.setBounds(230, 455, 80, 30);
    
        fondo.add(tablero);
        
        this.setLayout(new BorderLayout());
        this.add(fondo, BorderLayout.CENTER);
        this.add(tit, BorderLayout.NORTH);


        //fondo.setBackground(MORADO); 
        fondo.setBackground(AMARILLO);
        fondo.setPreferredSize(new Dimension(550, 500));
        fondo.setLayout(null);
    
        tablero.setLocation(80, 70);
        tablero.setVisible(true);

        this.setIconImage(new ImageIcon("sudoku.jpg").getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void inicio()
    {
		tablero.generarSudoku();	
    }
}