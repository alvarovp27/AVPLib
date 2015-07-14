package avp.maths.matrix.algConcurrent;


public class Test {

	public static void main(String args[]) throws InterruptedException{
		
		for(Integer i=2;i<=32768;i*=2){
			TestClass tc = new TestClass(i);
			tc.execute();
			
			System.out.println();
		}
	}
}
