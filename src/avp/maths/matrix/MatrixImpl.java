package avp.maths.matrix;
public class MatrixImpl implements Matrix{
	
	private Integer i;
	private Integer j;
	private Double [] [] matrix;
	
	//Empty matrix constructor
	public MatrixImpl(Integer i, Integer j){
		this.i=i;
		this.j=j;
		matrix=new Double[i][j];
	}
	
	//Complete matrix constructor
	public MatrixImpl(Double [][] m){
//		checkMatrix(m);
		this.matrix=m;
		i=m.length;
		j=m[0].length;
	}
	
//	private void checkMatrix(Integer[][] m){
//		for(Integer pointer_i=0;pointer_i<m.length;pointer_i++){
//			if((m[0].length)!=(m[i].length)){
//				throw new IllegalArgumentException("");
//			}
//		}
//	}
	
	
	public Integer getRowSize(){
		return i;
	}
	
	public Integer getColumnSize(){
		return j;
	}
	
	public String getMatrixDimension(){
		String res=getRowSize()+"x"+getColumnSize();
		return res;
	}
	
	public Double getCell(Integer index_i, Integer index_j){
		if(index_i>=i || index_j>=j){
			throw new IndexOutOfBoundsException("The value of index_i and index_j must be less than i and j");
		}
		return matrix[index_i][index_j];
	}
	
	public void setCell(Integer index_i, Integer index_j, Double value){
		if(index_i>=i || index_j>=j){
			throw new IndexOutOfBoundsException("The value of index_i and index_j must be less than i and j");
		}
		matrix[index_i][index_j]=value;
	}
	
	public void cleanMatrix(){
		for(Integer index_i=0;index_i<i;index_i++){
			for(Integer index_j=0;index_j<j;index_j++){
				setCell(index_i,index_j,0.0);
			}
		}
	}
		
	public void printRow(Integer pointer_i){
		if(pointer_i>=i){
			throw new IndexOutOfBoundsException("The value of pointer_j must be less than j");
		}
		System.out.print("[ ");
		for(Integer pointer_j=0;pointer_j<j;pointer_j++){
			System.out.print(getCell(pointer_i,pointer_j)+" ");
		}
		System.out.println(" ]");
	}
	
	
	public void printColumn(Integer pointer_j){
		if(pointer_j>=j){
			throw new IndexOutOfBoundsException("The value of pointer_i must be less than i");
		}
		System.out.print("[ ");
		for(Integer pointer_i=0;pointer_i<i;pointer_i++){
			if(pointer_j==j-1){
				System.out.print(getCell(pointer_i,pointer_j)+" ");
			} else {
				System.out.print(getCell(pointer_i,pointer_j)+" ; ");
			}

		}
		System.out.println(" ]");
	}
	
	
    public void printMatrix() {

        for(Integer pointer_i=0;pointer_i<i;pointer_i++) {
        	System.out.print("[ ");
            for(Integer pointer_j=0;pointer_j<j;pointer_j++) {
                System.out.print(getCell(pointer_i, pointer_j)+" ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

	

}
