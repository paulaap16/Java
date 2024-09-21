package dominio;

import java.util.Random;

public class Sudoku
{
	public final static int SIZE = 9;

	private int sudoku[][];

	public Sudoku()
	{
		this.sudoku = new int[SIZE][SIZE];
		this.limpiar();
	}

	public void generar() // GENERAR UN SUDOKU ALEATORIO
	{
		int s[][] = new int [SIZE][SIZE];
		Random rand = new Random();
		int ceros = 50;
		limpiar();

		for(int i = 0; i < 3; i++)  //matriz 1 
			for(int j = 0; j < 3; j++)
			{
				int valor = rand.nextInt(SIZE) + 1;
				if(!inMatriz(valor,i,j))
					s[i][j] = valor;
				else
					j--;
			}

		for(int i = 3; i < 6; i++)  //matriz 5 
			for(int j = 3; j < 6; j++)
			{
				int valor = rand.nextInt(SIZE) + 1;
				if(!inMatriz(valor,i,j))
					s[i][j] = valor;
				else
					j--;
			}
		
		for(int i = 6; i < 9; i++)  //matriz 9 
			for(int j = 6; j < 9; j++)
			{
				int valor = rand.nextInt(SIZE) + 1;
				if(!inMatriz(valor,i,j))
					s[i][j] = valor;
				else
					j--;
			}
		
		resolver();  //sudoku generado!
		
        for (int k = 0; k < ceros; k++)
        {
            int i = rand.nextInt(SIZE);
            int j = rand.nextInt(SIZE);

            if (s[i][j] != 0) 
            {
                s[i][j] = 0;
            } else
             {
                k--;
           	 }
		s = sudoku;
		
        }
	}

	public int[][] getSudoku()
	{
		return sudoku;
	}

	public boolean resolver() 
	{
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(sudoku[i][j] == 0)
				{
					for(int k = 1; k < 10; k++)
						if(this.inFila(k, i) == false && this.inColumna(k, j)== false && this.inMatriz(k,i,j )== false )
						{
							sudoku[i][j] = k;
							if(resolver())
								return true;

							sudoku[i][j] = 0;
							
						}
					return false;	
				}
			return true;
	}

	public boolean inFila(int num, int fila)
	{
		for(int i = 0; i < sudoku[fila].length; i++)
			if(sudoku[fila][i] == num)
				return true;
		return false;
	}

	public boolean inColumna(int num, int columna)
	{
		for(int i = 0; i < sudoku[columna].length; i++)
			if(sudoku[i][columna] == num)
				return true;
		return false;
	}

	public int pos(int pos)
	{
		int f = -1;
		if(pos < 3)
			f = 3;
		else if(pos < 6)
			f = 6;
		else if(pos >= 6)
			f = 9;
		return f;
	}
	public boolean inMatriz(int num, int fila, int col)
	{
		int f = this.pos(fila);
		int c = this.pos(col);
		for(int i = (f-3); i < f; i++)
			for(int j = (c-3); j < c; j++)
				if(sudoku[i][j] == num)
					return true;
		return false;
	}

	public void mostrar() 
	{	
		for(int i = 0; i < sudoku.length; i++)
			for(int j = 0; j <= sudoku[i].length; j++)
				if(j == sudoku[i].length)
					System.out.print("\n");
				else
					System.out.print(sudoku[i][j] + " ");
	}

	public void mostrarResuelto() 
	{	
		this.resolver();
		for(int i = 0; i < sudoku.length; i++)
			for(int j = 0; j <= sudoku[i].length; j++)
				if(j == sudoku[i].length)
					System.out.print("\n");
				else
					System.out.print(sudoku[i][j] + " ");
	}

	public void limpiar() 
	{
		for(int i = 0; i < sudoku.length; i++)
			for(int j = 0; j < sudoku.length; j++)
				sudoku[i][j] = 0;
	}

}