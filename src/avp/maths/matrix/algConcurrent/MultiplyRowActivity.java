package avp.maths.matrix.algConcurrent;

import avp.maths.matrix.Matrix;
import avp.maths.matrix.MatrixImpl;

public class MultiplyRowActivity implements Runnable{
	
	/*El atributo vector es la fila de la matriz resultante calculada.
	 * El atributo aRow es la fila de la matríz a a multiplicar
	 * El atributo b es la matriz que multiplica a la matriz a*/

	private Matrix aRow;
	private Matrix b;
	private Matrix vector;
	
	public MultiplyRowActivity(Matrix aRow, Matrix b) {
		this.aRow=aRow;
		this.b=b;
		this.vector=new MatrixImpl(1,aRow.getColumnSize());
	}

	
	public Matrix getVector(){
		return vector;
	}
	
	public void run() {
		
		double acum=0.0;
		
		for(int j=0;j<b.getColumnSize();j++){
			for(int k=0;k<b.getRowSize();k++){
				acum+=aRow.getCell(0, k)*b.getCell(k, j);
			}
			vector.setCell(0, j, acum);
			acum=0.0;
		}
		
	}

}
