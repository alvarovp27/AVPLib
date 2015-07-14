package avp.maths.matrix.algConcurrent;

import java.util.Random;

import avp.maths.matrix.Matrix;
import avp.maths.matrix.MatrixImpl;
import avp.maths.matrix.alg.Matrices;

public class TestClass {

	private Integer dimension;
	
	public TestClass(Integer dimension) {
		this.dimension=dimension;
	
	}

	public void execute() throws InterruptedException{
		Random rnm = new Random();
		
		
		Matrix z1 = new MatrixImpl(dimension,dimension);
		for(Integer i=0;i<z1.getRowSize();i++){
			for(Integer j=0;j<z1.getColumnSize();j++){
				z1.setCell(i, j, (double) rnm.nextInt(2));
			}
		}
		
		Matrix z2 = new MatrixImpl(dimension,dimension);
		for(Integer i=0;i<z2.getRowSize();i++){
			for(Integer j=0;j<z2.getColumnSize();j++){
				z2.setCell(i, j, (double) rnm.nextInt(2));
			}
		}
		
		long tinicio = System.currentTimeMillis();
		Matrices.multiplyMatrix(z1, z2);
		long tfinal = System.currentTimeMillis();
		
		System.out.println("Multiplicacion trivial ("+dimension+"): "+(tfinal-tinicio)+"ms");
		
		tinicio = System.currentTimeMillis();
		MatricesConcurrent.multiplyMatrixP(z1, z2);
		tfinal = System.currentTimeMillis();
		
		System.out.println("Multiplicacion trivial paralelizada ("+dimension+"): "+(tfinal-tinicio)+"ms");
		
		tinicio = System.currentTimeMillis();
		Matrices.strassenAlgorithm(z1, z2);
		tfinal = System.currentTimeMillis();
		
		System.out.println("Multiplicacion Strassen ("+dimension+"): "+(tfinal-tinicio)+"ms");
		
		tinicio = System.currentTimeMillis();
		MatricesConcurrent.strassenAlgorithmP(z1, z2);
		tfinal = System.currentTimeMillis();
		
		System.out.println("Multiplicacion Strassen paralelizada("+dimension+"): "+(tfinal-tinicio)+"ms");
	}
	
	
}
