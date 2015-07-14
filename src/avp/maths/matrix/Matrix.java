package avp.maths.matrix;

public interface Matrix {
	
	public Integer getRowSize();
	public Integer getColumnSize();
	public String getMatrixDimension();
	
	public Matrix getSubMatrix(Integer rows, Integer columns, Integer i, Integer j);
	
	public void setCell(Integer index_i, Integer index_j, Double value);
	public Double getCell(Integer index_i, Integer index_j);
	
	public void cleanMatrix();
	
    public void printMatrix();
	public void printRow(Integer pointer_j);
	public void printColumn(Integer pointer_i);

}
