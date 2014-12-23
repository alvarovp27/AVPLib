package avp.maths.matrix;


public class CreateMatrix {
	
	
	public static Matrix copyOf(Matrix a){
		
		Matrix res = new MatrixImpl(a.getRowSize(),a.getColumnSize());
		
		for(Integer index_i=0;index_i<a.getRowSize();index_i++){
			for(Integer index_j=0;index_j<a.getColumnSize();index_j++){
				res.setCell(index_i, index_j, a.getCell(index_i, index_j));
			}
		}
		
		return res;
	}
}
