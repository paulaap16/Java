package ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import javax.swing.*;
import java.awt.*;

public class Portada extends JFrame
{
    private Ventana ventana;

    public Portada()
    {
        JPanel fondo = new JPanel();
        JLabel label = new JLabel(new ImageIcon("sudoku.jpg"));

        JButton button = new JButton("JUGAR");
        button.setFont(new Font("Bertram Let", Font.BOLD, 24));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(243, 255, 63));

        LineBorder bordeExterior = new LineBorder(new Color(0, 0, 0, 60), 3, true); // Borde exterior con efecto de sombra
        LineBorder bordeInterior = new LineBorder(new Color(255, 255, 255, 100), 3, true); // Borde interior con efecto de resalte
        CompoundBorder bordePersonalizado = new CompoundBorder(bordeExterior, bordeInterior);
        button.setBorder(bordePersonalizado);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ventana = new Ventana();
                ventana.inicio();
                ventana.setVisible(true);
            }
        });

        button.setBounds(180, 230, 150, 50);
        label.add(button);
        fondo.add(label);

        this.add(fondo);
        this.setIconImage(new ImageIcon("sudoku.jpg").getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 600);
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}