package avp.maths.commandLine;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import avp.maths.matrix.Matrix;


public class Main {
	
	public static void main(String[] args){
		
		Map<String, Matrix> var = new HashMap<>();
		String[] chars={"=","+","-","*","/","inv(","identity("};
		System.out.println("Bienvenido a MathApp");
		Scanner sc = new Scanner(System.in);
//		String command=" ";
		String varName=" ";
		
		Integer numFormat = 0;
		
		Integer idVar=0;
		
		while(true){
			Command command = new CommandLine(sc);
			
			if(command.isTypeV()){
				var.get(command.getCommand()).printMatrix();
			} else if(command.isTypeAlpha() && command.isTypeEquals() && !command.isTypeBetha()){
				command=FormatTransform.splitByEquals(command);
				var.put(command.getVarName(), FromCommand.createMatrixFromString(command.getCommand()));
			} else if(command.isTypeAdd() && command.isTypeEquals()){
				
				command=FormatTransform.splitByEquals(command);
				Matrix b=FromCommand.transformAdd(command,var);
				var.put(command.getVarName(), b);
			}
			
			

			
		}
		
		
		
		
		
//		while(true){
//			command = sc.nextLine();
//			command=FormatTransform.deleteBlanks(FormatTransform.deleteBlanksIntoMatrix(command)); //Borra los espacios en blanco innecesario
////			System.out.println(command);
//			
//			
//			//Imprime en consola la matríz asignada a la variable pasada por consola
//			if(var.containsKey(command)){
//				var.get(command).printMatrix();
//			}
//			
//			
//			if(command.contains("=")){
//				String [] format = FormatTransform.splitByEquals(command);
//				command=format[1];
//				varName=format[0];
//				
////				Matrix a=FromString.createMatrixFromString(command); //Suponiendo que el comando lo único que hace es crear una matriz
////				var.put(varName, a);
//				
//				
//			}
//			if(command.contains("*")){
//				String [] formatMult=FormatTransform.splitByMult(command);
//				numFormat=formatMult.length;
//				
//			}
//			
//			for(Integer i=0;i<numFormat;i++){
//				
//			}
//			
//			
//			
//			
////			if(command.contains("=[") && command.contains("]"));
////			/* El formato de las matrices por consola será:
////			 * [valor valor valor; valor valor valor;...]*/
////				String[] format = command.split("=");
////				
////				format[1]=format[1].replace("[", "");
////				format[1]=format[1].replace("]", "");
////				String[] format2 = format[1].split(";");
////				
////				String[] format3 = format2[0].split(" ");
////
////				Matrix a = new MatrixImpl(format2.length,format3.length);
////				
////				Integer index_i=0;
////				Integer index_j=0;
////				
////				while(index_i<a.getRowSize()){
////					
////					while(index_j<a.getColumnSize()){
////						String[] format_aux=format2[index_i].split(" ");
////						String aux = format_aux[index_j];
////						//System.out.println(aux);
////						Double cell_value= Double.parseDouble(aux);
////						a.setCell(index_i, index_j, cell_value);
////						
////						index_j++;
////					}
////					index_j=0;
////					index_i++;
////					
////				}
//				
//				
//				
////				var.put(format[0], a);
////			
////				a.printMatrix();
//			
//			
//		}
		
	}

}
