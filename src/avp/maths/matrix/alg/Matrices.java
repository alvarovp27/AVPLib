package avp.maths.matrix.alg;

import avp.maths.matrix.CreateMatrix;
import avp.maths.matrix.Matrix;
import avp.maths.matrix.MatrixImpl;
import avp.maths.matrix.exception.UnsupportedMatrixException;

/**
 * Contiene algoritmos de utilidad para realizar operaciones b�sicas con matrices*/


public class Matrices {
	
/*	public static Matrix createMatrix(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Type matrix dimension. i:");
		Integer i = sc.nextInt();
		System.out.println("j:");
		Integer j = sc.nextInt();
		
		Matrix res= new MatrixImpl(i,j);
		
		for(Integer pointer_i=0;pointer_i<i;i++){
			
		}
		
		
		sc.close();
		return res;
	}*/
	
	/** Multiplica dos matrices (A*B). Algoritmo simple de complejidad O(n^3).
	 * Las matrices de entrada deben ser compatibles: el n�mero de columnas de la matriz A debe ser igual que
	 * el n�mero de filas de la matriz B.
	 * @param Matrix a
	 * @param Matrix b
	 * @return Matriz resultado de la multiplicaci�n de las matrices a y b de esta forma: (a*b).
	 * @exception UnsupportedMatrixException si las matrices de entrada no son compatibles.
	 * Este m�todo devuelve una matriz resultado de la multiplicaci�n de dos matrices a y b pasadas como
	 * */
	public static Matrix multiplyMatrix(Matrix a, Matrix b){
		if(a.getColumnSize()!=b.getRowSize())
			throw new UnsupportedMatrixException();
		
		Matrix res=new MatrixImpl(a.getRowSize(), b.getColumnSize());
		Integer index_a_row=0;
		Integer index_a_column=0;
		Integer index_b_row=0;
		Integer index_b_column=0;
		Double acum=0.0;
		
		while(!index_a_row.equals(a.getRowSize())) /*Deja fija la fila de la matr�z A*/{
			
			while(!index_b_column.equals(b.getColumnSize())){
				/**/
				
				while(!index_a_column.equals(a.getColumnSize()) && !index_b_row.equals(b.getRowSize())){
					
					acum=acum+(a.getCell(index_a_row, index_a_column)*b.getCell(index_b_row, index_b_column));
					
					res.setCell(index_a_row, index_b_column, acum);
					
					index_a_column++;
					index_b_row++;
				}
				acum=0.0;
				index_b_column++;
				index_a_column=0;
				index_b_row=0;
			}
			index_a_row++;
			index_b_column=0;
		}
		
		return res;
	}
	
	/**Devuelve una matr�z resultado de multiplicar una matriz a por un n�mero de tipo Double m, ambos son pasados como par�metros.
	 * Orden de complejidad: O()*/
	public static Matrix multiplyNumber(Matrix a, Double m){
		Matrix res = new MatrixImpl(a.getRowSize(),a.getColumnSize());
		
		Integer index_a_row=0;
		Integer index_a_column=0;
		Double value=0.0;
		while(index_a_row<a.getRowSize()){

			while(index_a_column<a.getColumnSize()){
				value = a.getCell(index_a_row, index_a_column);
				if(value.equals(0.0)){
					res.setCell(index_a_row, index_a_column, 0.0); //Hago esto para evitar que salga el valor -0.0
				} else {
					res.setCell(index_a_row, index_a_column, m*value);
				}
				index_a_column++;
			}
			
			index_a_column=0;
			index_a_row++;
		}
		
		return res;
	}
	
	/** Suma dos matrices. Algoritmo simple de complejidad O(n^2).
	 * Las matrices de entrada deben ser compatibles (deben ser de igual dimensi�n).
	 * @param Matrix a
	 * @param Matrix b
	 * @return Matriz resultado de sumar las matrices a y b (a+b)
	 * @exception UnsupportedMatrixException si las matrices no son compatibles.
	 * */
	public static Matrix addMatrix(Matrix a, Matrix b){
		
		if(!a.getRowSize().equals(b.getRowSize()) && !a.getColumnSize().equals(b.getColumnSize()))
			throw new UnsupportedMatrixException();
		
		Matrix res = new MatrixImpl(a.getRowSize(),a.getColumnSize());
		
		Double aux = 0.0;
		Integer i=0;
		Integer j=0;
		
		while(i<res.getRowSize()){
			
			while(j<res.getColumnSize()){
				aux=a.getCell(i, j) + b.getCell(i, j);
				res.setCell(i, j, aux);
				j++;
			}
			j=0;
			i++;
		}
		return res;
	}
	
	public static Matrix addMatrixConcurrent(Matrix a, Matrix b){
		if(!a.getRowSize().equals(b.getRowSize()) && !a.getColumnSize().equals(b.getColumnSize())){
			throw new IllegalArgumentException();
		}
		
		Matrix res=new MatrixImpl(a.getRowSize(), a.getColumnSize());
		
		
		
		return res;
	}
	
	/**Devuelve una matriz resultado de restar la matriz b a la matriz a, ambas pasadas como par�metros.
	 * Orden de complejidad: */
	public static Matrix subtractMatrix(Matrix a,Matrix b){
		Matrix neg=negMatrix(b);
		return addMatrix(a, neg);
	}

	/**Devuelve la matriz opuesta a la matriz a pasada como par�metro.
	 * Orden de complejidad: n^2*/
	public static Matrix negMatrix(Matrix a){
		Matrix res = new MatrixImpl(a.getRowSize(),a.getColumnSize());
		
		Integer index_a_row=0;
		Integer index_a_column=0;
		Double value=0.0;
		while(index_a_row<a.getRowSize()){

			while(index_a_column<a.getColumnSize()){
				value = a.getCell(index_a_row, index_a_column);
				if(value.equals(0.0)){
					res.setCell(index_a_row, index_a_column, 0.0); //Hago esto para evitar que salga el valor -0.0
				} else {
					res.setCell(index_a_row, index_a_column, -1.0*value);
				}
				index_a_column++;
			}
			
			index_a_column=0;
			index_a_row++;
		}
		
		return res;
	}
	
	/**Devuelve la matr�z identidad de orden d, pasado como par�metro.
	 * Orden de complejidad: n^2*/
	public static Matrix identityMatrix(Integer d){
		
		Matrix res = new MatrixImpl(d, d);
		
		Integer index_row=0;
		Integer index_column=0;
		
		while(!index_row.equals(d)){
			
			while(!index_column.equals(d)){
				if(index_column==index_row){
					res.setCell(index_row, index_column, 1.0);
				} else {
					res.setCell(index_row, index_column, 0.0);
				}
				index_column++;
			}
			index_column=0;
			index_row++;
		}
		
		return res;
		
	}
	
	/**Devuelve el determinante de la matriz a pasada como par�metro.
	 * Orden de complejidad: */
	public static Double determinant(Matrix a){
		
		if(!a.getRowSize().equals(a.getColumnSize())){
			throw new IllegalArgumentException("La matr�z debe ser cuadrada");
		}
		
		Double res=1.0;
		
		
		Integer rows=a.getRowSize();
		Integer columns=a.getColumnSize();
		Integer index_i=0; /*Puntero que apunta a la fila del pivote*/
		Integer index_j=0; /*Puntero que apunta a la columna del pivote*/
		Integer i_pointer=0; /*Puntero que apunta a la fila a manipular*/
		Integer j_pointer=0; /*Puntero que apunta a la columna a manipular*/
//		Matrix aux=a; /*Matriz resultado de las modificaciones de Gauss*/
		
		Matrix aux=CreateMatrix.copyOf(a); //Creo la matriz aux de esta manera para que no modificar la matriz pasada como par�metro
		
		Double column_0=0.0; /*Guarda en valor de la celda situada en la primera columna y en la fila a manipular (la celda que debe tener el valor 0.0)*/ 
		Double pivot=0.0; /*Guarda el valor del pivote. Siempre ser� un elemento de la diagonal.*/
		
		while(index_i<rows){/*Hago que recorra todas las filas. */
			Boolean brk=false;
			i_pointer++;
			
			pivot=aux.getCell(index_i, index_j);
			
			Double pivot_aux = pivot; //Esta variable almacena el valor temporal del pivote dentro del siguiente bucle
			Integer index_i_aux=index_i; //Este puntero se�alar� una fila distinta a la apuntada por index_i para intercambiarla por la fila index_i
			
			while(pivot_aux==0){ /*Si el pivote escogido es cero, este bucle ir� permutando filas hasta que el pivote sea distinto de cero. 
			En el caso en el que recorra todas las filas posibles y todos los posibles pivotes sean 0.0, salgo del bucle y establezco el valor del
			determinante a 0.0*/
				
				if(index_i_aux==(rows-1)){
					res=0.0;
					brk=true; //Si toda la columna es cero, activo esta variable para que se salga de todo el bucle.
					break;
				}
				
				Matrices.rotateRow(aux,index_i ,index_i_aux);
				index_i_aux++;
				res*=-1.0; //Cada vez que roto una fila, el determinante cambia de signo.
				pivot_aux=aux.getCell(index_i, index_j); 
			}
			
				pivot=pivot_aux; //Hago que el valor del pivote sea el que se ha establecido tras el cambio de filas (si lo ha habido).
			
			if(brk){
				break; //Si se activ� esta variable con anterioridad, se saltar� todo el bucle. Lo hago para evitar las divisiones entre cero y valores sin sentido
			}
				
			/*Guarda como pivote el valor de la diagonal de la fila que corresponda*/
			while(i_pointer < rows){ /*Recorro las filas a manipular*/
				
				column_0=aux.getCell(i_pointer, j_pointer); /*Obtengo el valor de la celda que debe valer 0.0*/
				
				while(j_pointer<columns){/*Recorro las columnas a manipular de la fila a manipular*/
					
					Double value=(aux.getCell(i_pointer, j_pointer))-(aux.getCell(index_i, j_pointer)*(column_0/pivot));
					/*A la fila actual le resto la fila del pivote multiplicada por el primer elemento distinto de cero de la fila actual dividio por el pivote*/
					aux.setCell(i_pointer, j_pointer, value);
					j_pointer++;
				}
				i_pointer++;
				j_pointer=index_j;
			}
			
			i_pointer=index_i;
			index_i++;
			index_j++;
		}
		
		index_i=0;
		
		//Ahora recorro la diagonal de la matrix y la multiplico para obtener el determinante
		while(index_i<rows){
			res*=aux.getCell(index_i, index_i);
			index_i++;
		}
		
		return res;
	}
	
	/**M�todo privado que dada una matriz m, un Integer i y un Integer nextRow, intercambia las filas i y nextRow
	 * de la matriz. No devuelve nada.*/
	private static void rotateRow(Matrix m,Integer i,Integer nextRow){ //Le paso la matriz a transformar, y las filas que quiero rotar
		
		Integer j=0;
		
		Matrix aux = new MatrixImpl(1, m.getColumnSize());
		while(j<m.getColumnSize()){
			aux.setCell(0, j, m.getCell(nextRow, j)); //Almaceno la fila i+1 en el vector auxiliar
			
			m.setCell(nextRow, j, m.getCell(i, j)); //Meto la fila i en la fila i+1
			
			m.setCell(i, j, aux.getCell(0, j)); //Meto el vector auxiliar en la fila i
			
			j++;
		}
		
	}
	
	/**Devuelve la matriz de cofactores de a. Orden de complejidad:*/
	public static Matrix cofactorMatrix(Matrix a){

		Integer tam = a.getRowSize();
		Matrix res = new MatrixImpl(tam, tam);
		Matrix auxDet = new MatrixImpl(tam-1,tam-1);
		
		Integer index_i=0;
		Integer index_j=0;
		Integer aux_i=0;
		Integer aux_j=0;
		Integer point_i_det=0;
		Integer point_j_det=0;
		Double temp=0.0;
		Double determinant=0.0;
		
		while(index_i<tam){ 
			while(index_j<tam){ //Los punteros index_i e index_j apuntan a la celda a la que calcular el cofactor
				while(aux_i<tam){ //En los siguientes dos bucles, busco los elementos de la matriz del cofactor (index_i,index_j)
					if(!aux_i.equals(index_i)){
						while(aux_j<tam){
							if(!aux_j.equals(index_j)){
								//System.out.println("he llegado aqui");
								temp=a.getCell(aux_i,aux_j);
								auxDet.setCell(point_i_det, point_j_det, temp);/*point_i_det y point_j_det apuntan a la celda de la matriz del cofactor
								index_i e index_j en la que colocar el elemento aux_i y aux_j*/
								point_j_det++;
								
//								System.out.println("Puntero aux_i: "+aux_i+" | Puntero aux_j: "+aux_j+" | Valor de la celda: "+temp);
							}
							aux_j++;
						}
						point_j_det=0;
						point_i_det++;
					}
					aux_j=0;
					aux_i++;
				}
//				auxDet.printMatrix();
//				System.out.println();
				aux_i=0;
				point_i_det=0;
				determinant=Matrices.determinant(auxDet);
				if((index_i+index_j)%2!=0){ //Le cambio el signo si la suma de sus �ndices es impar.
					determinant*=-1.0;
				}
				res.setCell(index_i, index_j, determinant);
				index_j++;
			}
			index_j=0;
			index_i++;
		}
		return res;
	}
	
	/**Devuelve la matriz adjunta de a, pasada como par�metro. Orden de complejidad: */
	public static Matrix adjugateMatrix(Matrix a){
		Matrix res=Matrices.transposeMatrix(Matrices.cofactorMatrix(a));
		return res;
	}
	
	/**Devuelve la matriz traspuesta de a, pasada como par�metro. Orden de complejidad:*/
	public static Matrix transposeMatrix(Matrix a){
		Matrix res=new MatrixImpl(a.getColumnSize(),a.getRowSize());
		Double temp=0.0;
		for(Integer index_i=0;index_i<a.getRowSize();index_i++){
			for(Integer index_j=0;index_j<a.getColumnSize();index_j++){
				temp=a.getCell(index_i, index_j);
				res.setCell(index_j, index_i, temp);
			}
		}
		
		return res;
	}
	
	/**Devuelve la matriz inversa de a, pasada como par�metro. Orden de complejidad:*/
	public static Matrix inverseMatrix(Matrix a){
		Matrix res=new MatrixImpl(a.getRowSize(), a.getColumnSize());
		Double determinant=Matrices.determinant(a);
		res=Matrices.multiplyNumber(Matrices.adjugateMatrix(a), (1/determinant));
		
		return res;
	}
	
}
