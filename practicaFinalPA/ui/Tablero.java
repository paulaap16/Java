package ui;

import dominio.Sudoku;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.lang.NumberFormatException;

public class Tablero extends JPanel
{
    private JTextField[][] sudokuTxt;
    private int ancho, alto, margen, letra;
    private Sudoku sudoku;
    private JTextField txt;
    private ArrayList<JTextField> numerosMostrados;
    private ArrayList<JTextField> todosTextFields;

    private final static Color MPULSADO = new Color(125,100,206); //para el pulsado
    private final static Color MRESTO = new Color(176,153,249); //para el resto
    private final static Color MLETRA = new Color(94,11,158); //para la letra

    private final static Color APULSADO = new Color(255,243,129); //para el pulsado
    private final static Color ARESTO = new Color(255,222,0); //para el resto
    private final static Color ALETRA = new Color(0,39,146); //para la letra

    public Tablero()
    {
        sudokuTxt = new JTextField[9][9];;
        ancho = 40;
        alto = 40;
        margen = 3;
        letra = 27;
        
        sudoku = new Sudoku();
        numerosMostrados = new ArrayList<>();
        todosTextFields = new ArrayList<>();

        this.setLayout(null);
        this.setSize(ancho * 9 + (margen * 4), alto * 9 + (margen * 4));
        this.setBackground(Color.BLACK);

        crearTablero();
    }

    public void crearTablero()
    {
        int x = margen;
        int y = margen;

        for (int i = 0; i < sudokuTxt.length; i++) {
            for (int j = 0; j < sudokuTxt[0].length; j++) {
                txt = new JTextField();
                todosTextFields.add(txt);
                this.add(txt);
                txt.setBounds(x, y, ancho, alto);
                txt.setBackground(Color.WHITE);
                txt.setFont(new Font("Montserrat", Font.BOLD, letra));
                txt.setEditable(false);
                txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                txt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                txt.setVisible(true);

                txt.addMouseListener(new MouseAdapter() 
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        for(JTextField num : todosTextFields)
                            {
                                num.setBackground(Color.WHITE);
                                num.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                                if(num.isEditable())
                                    num.setForeground(ALETRA);
                            }

                        txt.setBackground(APULSADO);  
                        txt.setBorder(BorderFactory.createLineBorder(new Color(227, 227, 246), 4));
                        
                        for (int i = 0; i < sudokuTxt.length; i++) 
                            for (int j = 0; j < sudokuTxt[0].length; j++)
                                if(sudokuTxt[i][j] == txt)
                                    for(int k = 0; k < 9; k++)
                                       {
                                        sudokuTxt[i][k].setBackground(ARESTO);
                                        sudokuTxt[k][j].setBackground(ARESTO);
                                       }                          
                    }

                });

            
                txt.addKeyListener(new KeyAdapter() 
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        try
                        {
                            int numero = 0;
                            for (int i = 0; i < sudokuTxt.length; i++) 
                                for (int j = 0; j < sudokuTxt[0].length; j++)
                                {
                                    numero = Integer.parseInt(sudokuTxt[i][j].getText());

                                    if(sudokuTxt[i][j] == txt)
                                    {
                                        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                                        {
                                            if(i+1 <= sudokuTxt.length)
                                                txt = sudokuTxt[i+1][j];
                                        }
                                            
                                        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                                        {
                                            if(i-1 <= sudokuTxt.length)
                                                txt = sudokuTxt[i-1][j];
                                        }
                                        
                                        else if(e.getKeyCode() == KeyEvent.VK_UP)
                                        {
                                            if(j+1 <= sudokuTxt.length)
                                                txt = sudokuTxt[i][j+1];
                                        }
                                        
                                        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                                        {
                                            if(j-1 <= sudokuTxt.length)
                                                txt = sudokuTxt[i][j-1];
                                        }
                                    }
                                }
                        }

                        catch(NumberFormatException exc)
				        {
					        JOptionPane.showMessageDialog(Tablero.this, "Introduzca un n�mero");
				        }
                    }
                });

                x += ancho;
                if ((j + 1) % 3 == 0) {
                    x += margen;
                }
                sudokuTxt[i][j] = txt;
            }
            x = margen;
            y += alto;
            if ((i + 1) % 3 == 0) {
                y += margen;
            }
        }
    }
    
    public void borrarTodo()
    {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                sudokuTxt[i][j].setText(""); 

        numerosMostrados.clear();
    }

    public void generarSudoku()
    {
        borrarTodo();
        sudoku.generar();
        int[][] numerosTxt = sudoku.getSudoku();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(numerosTxt[i][j] != 0)
                {
                    sudokuTxt[i][j].setText(String.valueOf(numerosTxt[i][j]));
                    sudokuTxt[i][j].setForeground(Color.BLACK);
                    sudokuTxt[i][j].setEditable(false);
                    numerosMostrados.add(sudokuTxt[i][j]);
                }
                else
                {
                    sudokuTxt[i][j].setText("");
                    sudokuTxt[i][j].setEditable(true);
                    sudokuTxt[i][j].setForeground(ALETRA);
                }
    }

    public void limpiarSudoku()
    {
        for(int i = 0; i < sudokuTxt.length; i++)
            for(int j = 0; j < sudokuTxt[0].length; j++)
               {
                boolean n = false;
                for(JTextField num : numerosMostrados)
                    if(sudokuTxt[i][j] == num)
                        n = true;
                
                if(!n)
                    sudokuTxt[i][j].setText("");
               } 
    }

    public void resolverSudoku()
    {
        sudoku.resolver();
        int[][] numerosTxt = sudoku.getSudoku();

        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(!numerosMostrados.contains(sudokuTxt[i][j]))
                   {
                    sudokuTxt[i][j].setText(String.valueOf(numerosTxt[i][j]));
                    sudokuTxt[i][j].setForeground(Color.RED);
                   }
    }

    public void corrector()
    {
        sudoku.resolver();
        int[][] numerosTxt = sudoku.getSudoku(); //solucion sudoku
       // int sudo[][] = new int[9][9];
        for (int i = 0; i < sudokuTxt.length; i++) 
            for (int j = 0; j < sudokuTxt[0].length; j++)
                {
                    if(sudokuTxt[i][j].isEditable())  
                    {
                        JTextField txt = sudokuTxt[i][j];
                        txt.addKeyListener(new KeyAdapter() 
                        {
                            @Override
                            public void keyReleased(KeyEvent e)
                                {
                                    try
                                    {
                                        int a = Integer.parseInt(txt.getText());
                                        for (int i = 0; i < sudokuTxt.length; i++) 
                                            for (int j = 0; j < sudokuTxt[0].length; j++)
                                                if(sudokuTxt[i][j] == txt)
                                                {
                                                    if(a == numerosTxt[i][j])  //BIEN
                                                    {
                                                        txt.setBackground(Color.GREEN);
                                                        txt.setForeground(Color.BLACK);
                                                    }
                                                    else //MAL
                                                    {
                                                        txt.setBackground(Color.RED);
                                                        txt.setForeground(Color.BLACK);
                                                    }
                                                }    
                                    }

                                    catch(NumberFormatException exc)
                                    {
                                        JOptionPane.showMessageDialog(Tablero.this, "Introduce un número");
                                    }
                                    
                                }
                        }); 

                        txt.addMouseListener(new MouseAdapter() 
                        {
                            @Override
                            public void mouseClicked(MouseEvent e)
                            {
                                if(txt.getText() != "")
                                {
                                    int a = Integer.parseInt(txt.getText());
                                        for (int i = 0; i < sudokuTxt.length; i++) 
                                            for (int j = 0; j < sudokuTxt[0].length; j++)
                                                if(sudokuTxt[i][j] == txt)
                                                {
                                                    if(a == numerosTxt[i][j])  //BIEN
                                                    {
                                                        txt.setBackground(Color.GREEN);
                                                        txt.setForeground(Color.RED);
                                                    }
                                                    else //MAL
                                                    {
                                                        txt.setBackground(Color.RED);
                                                        txt.setForeground(Color.BLACK);
                                                    }
                                                }  
                                }
                                    
                            }
                        });  
                    }
                }
    }

    public void comprobarSudoku()
    {
        int sudo[][] = new int[9][9];
        sudoku.resolver();
        int contador = 0;
        System.out.println(contador);
        for (int i = 0; i < sudokuTxt.length; i++)
        {
            for (int j = 0; j < sudokuTxt[0].length; j++) 
            {
                if (sudokuTxt[i][j].getText().isEmpty())
                    contador ++;
            }
        }

        for (int i = 0; i < sudokuTxt.length; i++)
            
            for (int j = 0; j < sudokuTxt[0].length; j++) 
               
                if(contador == 0)
                {
                    sudo[i][j] = Integer.parseInt(sudokuTxt[i][j].getText());
                    if(sudo[i][j] == sudoku.getSudoku()[i][j])
                         {
                            JOptionPane.showMessageDialog(null, "Sudoku correcto!!", "Sudoku", JOptionPane.INFORMATION_MESSAGE);
                            return;
                         }  
                    else
                        {
                          JOptionPane.showMessageDialog(null, "Sudoku incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                         return;
                        }
                }

                else
                {
                    JOptionPane.showMessageDialog(null, "Sudoku incompleto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
               
            
    }

}