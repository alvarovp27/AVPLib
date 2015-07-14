package avp.maths.matrix;

import avp.maths.matrix.alg.Matrices;


public abstract class Test {

	public static void main(String[] args) {
		
		Matrix m = new MatrixImpl(3, 3);
		
		m.setCell(0, 0, 3.0);
		m.setCell(0, 1, 3.0);
		m.setCell(0, 2, 3.0);
		m.setCell(1, 0, 3.0);
		m.setCell(1, 1, 50.0);
		m.setCell(1, 2, 3.0);
		m.setCell(2, 0, 3.0);
		m.setCell(2, 1, 3.0);
		m.setCell(2, 2, 3.0);
		
//		System.out.println(m.toString());
//		System.out.println(m.getCell(0,0));
		
//		Matriz m1=new MatrizImpl(1,1);
//		m1.setCell(0, 0, 800);
		//System.out.println(m1.toString());
		
//		m.printMatrix();
//		m.printRow(1);
//		m.printColumn(1);
		
		Double[][]arrayM={{2.0,1.0},{7.0,1.0},{3.0,0.0},{0.0,0.0}};
		
//		Double[][] m1 = { {-1.0,0.0,0.0},{0.0,-1.0,0.0},{0.0,0.0,-1.0} };
//		Double[][] m2 = { {0.0,2.0,-2.0}-,,{1.0,0.0,1.0},{2.0,2.0,0.0} };
		
		Double[][] m1 = { {0.0,0.0,0.0,0.0},{0.0,0.0,0.0,0.0},{-1.0,-1.0,1.0,1.0},{-2.0,-2.0,0.0,2.0} };
		Double[][] m2 = { {1.0,0.0,0.0,0.0},{1.0,1.0,0.0,0.0},{1.0,-1.0,1.0,0.0},{1.0,-1.0,0.0,1.0} };
		
		
//		Matrix m2 = new MatrixImpl(arrayM);
//		m2.printMatrix();
		
		Matrix m_1= new MatrixImpl(m1);
		Matrix m_2= new MatrixImpl(m2);
		
		Matrices.multiplyMatrix(m_1, m_2).printMatrix();
		new MatrixImpl(Matrices.multiplyMatrix(m1, m2)).printMatrix();
		
//		m_1.printMatrix();
//		m_2.printMatrix();
		
//		Matrix mult = new MatrixImpl(3,3);
//		
//		mult=Matrices.multiplyMatrix(m_1, m_2);
//		
//		mult.printMatrix();
//		System.out.println();
//		
//		mult=Matrices.negMatrix(Matrices.multiplyMatrix(m_1, m_2));
		
//		m_1.printMatrix();
//		System.out.println();
//		m_2.printMatrix();
//		System.out.println();
//		mult.printMatrix();
		
//		Matrix id = Matrices.identityMatrix(5);
//		System.out.println();
//		id.printMatrix();
		
		Double[][] m3={ {2.0,0.0,1.0},{1.0,1.0,-4.0},{3.0,7.0,-3.0}};
		Matrix m_3=new MatrixImpl(m3);
		System.out.println("Valor del determinante: " +Matrices.determinant(m_3));
		System.out.println();
		
		Double[][] m4={ {0.0,-2.0,1.0,0.0,1.0},{1.0,2.0,1.0,3.0,0.0},{-2.0,1.0,-2.0,1.0,1.0},{1.0,1.0,0.0,0.0,1.0},{2.0,-2.0,1.0,2.0,-2.0}};
		Matrix m_4=new MatrixImpl(m4);
		m_4.printMatrix();
		System.out.println("Valor del determinante: " +Matrices.determinant(m_4));
		System.out.println("Compruebo si la matríz ha sido modificada: ");
		m_4.printMatrix();
		System.out.println();

		Double[][] m5={ {0.0,-2.0,1.0,0.0,1.0},{0.0,2.0,1.0,3.0,0.0},{0.0,1.0,-2.0,1.0,1.0},{0.0,1.0,0.0,0.0,1.0},{0.0,-2.0,1.0,2.0,-2.0}};
		Matrix m_5=new MatrixImpl(m5);
		System.out.println("Valor del determinante: " +Matrices.determinant(m_5));
		System.out.println();
		
		Double[][] m6={{2.0, 1.0,-2.0},{1.0,1.0,-2.0},{-1.0,0.0,1.0} };
		Matrix m_6=new MatrixImpl(m6);
		Matrix m_6aux=Matrices.cofactorMatrix(m_6);
		m_6aux.printMatrix();
		System.out.println("dimension: "+m_6aux.getMatrixDimension());
		System.out.println();
		
		System.out.println("Matriz traspuesta: ");
		Matrices.transposeMatrix(m_6aux).printMatrix();
		System.out.println();
		
		System.out.println("Inversa de la matriz m_6: ");
		System.out.println("Determinante: "+Matrices.determinant(m_6));
		Matrix m_6inv=Matrices.inverseMatrix(m_6);
		m_6inv.printMatrix();
		System.out.println("Compruebo si la multiplicacion con su inversa da la identidad:");
		Matrices.multiplyMatrix(m_6, m_6inv).printMatrix();
		System.out.println();
		
		System.out.println("Ejecución de addMatrix con dos matrices identidad de orden 3");
		Matrix z1 = Matrices.identityMatrix(3);
		Matrix z2 = Matrices.identityMatrix(3);
		Matrices.addMatrix(z1, z2).printMatrix();
		System.out.println();
		
		System.out.println("Ejecución de addMatrix con dos matrices identidad de orden 3");
		Double[][] z_3= {{1.0, 1.0, 1.0},{2.0, 3.0, 4.0},{0.0, 0.0, 0.0}};
		Double[][] z_4= {{2.0, 2.0, 2.0},{1.0, 0.0, -1.0},{5.0, 6.0, 7.0}};
		Matrix z3 =new MatrixImpl(z_3);
		Matrix z4 = new MatrixImpl(z_4);
		Matrices.addMatrix(z3, z4).printMatrix();
		System.out.println();
		
		System.out.println("Pintando m_5...");
		m_5.printMatrix();
		System.out.println("Ejecución subMatrix de m_5");
		m_5.getSubMatrix(2, 2, 3, 3).printMatrix();
		System.out.println();
		
		System.out.println("Resultado de la multiplicación de matrices m_3 y m_6: ");
		Matrices.multiplyMatrix(m_3, m_6).printMatrix();
		System.out.println();
	}

}
