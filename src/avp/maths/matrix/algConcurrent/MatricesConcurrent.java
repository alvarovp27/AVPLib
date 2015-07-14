package avp.maths.matrix.algConcurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import avp.maths.matrix.Matrix;
import avp.maths.matrix.MatrixImpl;
import avp.maths.matrix.alg.Matrices;


/**Esta clase implementa algunas algoritmos para realizar operaciones básicas sobre matrices
 * empleando técnicas de programación concurrente.*/
public class MatricesConcurrent {
	
	
	public static Matrix multiplyMatrixP(Matrix a, Matrix b) throws InterruptedException {
//		if(a.getColumnSize()!=b.getRowSize())
//			throw new UnsupportedMatrixException();
		
		Matrix res=new MatrixImpl(a.getRowSize(), b.getColumnSize());
		
		int procesadores= Runtime.getRuntime().availableProcessors();
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(procesadores, procesadores, 50000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		
		MultiplyRowActivity mra[] = new MultiplyRowActivity[a.getRowSize()];
		
		for(Integer i_a=0;i_a<a.getRowSize();i_a++){
			Matrix aux=a.getSubMatrix(1, a.getColumnSize(), i_a, 0);
			mra[i_a] = new MultiplyRowActivity(aux, b);
			tpe.execute(mra[i_a]);
		}
		
		tpe.shutdown();
		tpe.awaitTermination(999, TimeUnit.DAYS);
		
		for(int i=0;i<a.getRowSize();i++){
			Matrices.joinMatrices(mra[i].getVector(), res, i, 0);
		}
		
		return res;
	}
	
	

	
	
	public static Matrix strassenAlgorithmP(Matrix a, Matrix b) throws InterruptedException{
		Matrix res=new MatrixImpl(a.getRowSize(), a.getColumnSize());
		
		if(a.getRowSize().equals(1)){
			res.setCell(0, 0, a.getCell(0, 0)*b.getCell(0, 0));
		} else {
		
			Integer half = a.getColumnSize()/2;
			
			Matrix a11 = a.getSubMatrix(half, half, 0, 0);
			Matrix a12 = a.getSubMatrix(half, half, 0, half);
			Matrix a21 = a.getSubMatrix(half, half, half, 0);
			Matrix a22 = a.getSubMatrix(half, half, half, half);
			
			Matrix b11 = b.getSubMatrix(half, half, 0, 0);
			Matrix b12 = b.getSubMatrix(half, half, 0, half);
			Matrix b21 = b.getSubMatrix(half, half, half, 0);
			Matrix b22 = b.getSubMatrix(half, half, half, half);
	
			
			
			Matrix m1 = new MatrixImpl(half,half);
			Matrix m2 = new MatrixImpl(half,half);
			Matrix m3 = new MatrixImpl(half,half);
			Matrix m4 = new MatrixImpl(half,half);
			Matrix m5 = new MatrixImpl(half,half);
			Matrix m6 = new MatrixImpl(half,half);
			Matrix m7 = new MatrixImpl(half,half);
			
			MultiplyStrassenActivity r1 = new MultiplyStrassenActivity(Matrices.addMatrix(a11,a22),Matrices.addMatrix(b11, b22));
			MultiplyStrassenActivity r2 = new MultiplyStrassenActivity(Matrices.addMatrix(a21, a22), b11);
			MultiplyStrassenActivity r3 = new MultiplyStrassenActivity(a11, Matrices.subtractMatrix(b12, b22));
			MultiplyStrassenActivity r4 = new MultiplyStrassenActivity(a22, Matrices.subtractMatrix(b21, b11));
			MultiplyStrassenActivity r5 = new MultiplyStrassenActivity(Matrices.addMatrix(a11, a12), b22);
			MultiplyStrassenActivity r6 = new MultiplyStrassenActivity(Matrices.subtractMatrix(a21, a11),Matrices.addMatrix(b11, b12));
			MultiplyStrassenActivity r7 = new MultiplyStrassenActivity(Matrices.subtractMatrix(a12, a22),Matrices.addMatrix(b21, b22));
			
			int procesadores= Runtime.getRuntime().availableProcessors();
			ThreadPoolExecutor tpe = new ThreadPoolExecutor(procesadores, procesadores, 50000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
			
			tpe.execute(r1);
			tpe.execute(r2);
			tpe.execute(r3);
			tpe.execute(r4);
			tpe.execute(r5);
			tpe.execute(r6);
			tpe.execute(r7);
			
			tpe.shutdown();
			tpe.awaitTermination(9, TimeUnit.DAYS);
			
			m1= r1.getRes();
			m2= r2.getRes();
			m3= r3.getRes();
			m4= r4.getRes();
			m5= r5.getRes();
			m6= r6.getRes();
			m7= r7.getRes();

			Matrix c11 = Matrices.subtractMatrix(Matrices.addMatrix(m1, Matrices.addMatrix(m4, m7)),m5);
			Matrix c12 = Matrices.addMatrix(m3, m5);
			Matrix c21 = Matrices.addMatrix(m2, m4);
			Matrix c22 = Matrices.subtractMatrix(Matrices.addMatrix(m1,Matrices.addMatrix(m3, m6)),m2);
			
			Matrices.joinMatrices(c11,res,0,0);
			Matrices.joinMatrices(c12,res,0,half);
			Matrices.joinMatrices(c21,res,half,0);
			Matrices.joinMatrices(c22,res,half,half);
		}
		return res;
	}
}
