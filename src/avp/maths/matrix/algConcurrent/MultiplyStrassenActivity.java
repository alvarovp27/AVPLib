package avp.maths.matrix.algConcurrent;

import avp.maths.matrix.Matrix;
import avp.maths.matrix.alg.Matrices;

public class MultiplyStrassenActivity extends Thread{
	
	private Matrix a;
	private Matrix b;
	private Matrix res;
	
	
	public MultiplyStrassenActivity(Matrix a,Matrix b){
		this.a=a;
		this.b=b;
	}
	
	public void run(){
			res= Matrices.strassenAlgorithm(a, b);
	}

	public Matrix getRes() {
		return res;
	}
}
