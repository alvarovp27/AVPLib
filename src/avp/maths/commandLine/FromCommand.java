package avp.maths.commandLine;

import java.util.Map;

import avp.maths.matrix.Matrix;
import avp.maths.matrix.MatrixImpl;
import avp.maths.matrix.alg.Matrices;


public class FromCommand {
	
	public static Matrix createMatrixFromString(String s){
		s=s.replace("[", "");
		s=s.replace("]", "");
		
//		System.out.println(s);
		
		String[] format_0 = s.split(";");
		String[] format_aux=format_0[0].split(" ");
		
		Integer rowSize = format_0.length;
		Integer columnSize = format_aux.length;
		
		Matrix res = new MatrixImpl(rowSize,columnSize);
		
		Integer i=0;
		Integer j=0;
		
		while(i<rowSize){
		
			while(j<columnSize){
				String[] format=format_0[i].split(" ");
				String aux = format[j];
				//System.out.println(aux);
				Double cell_value= Double.parseDouble(aux);
				res.setCell(i, j, cell_value);
				
				j++;
			}
		j=0;
		i++;
		
	}
		return res;
	}
	
	public static Matrix createMatrixFromCommand(Command c){
		String s=c.getCommand();
		s=s.replace("[", "");
		s=s.replace("]", "");
		
		String[] format_0 = s.split(";");
		String[] format_aux=format_0[0].split(" ");
		
		Integer rowSize = format_0.length;
		Integer columnSize = format_aux.length;
		
		Matrix res = new MatrixImpl(rowSize,columnSize);
		
		Integer i=0;
		Integer j=0;
		
		while(i<rowSize){
		
			while(j<columnSize){
				String[] format=format_0[i].split(" ");
				String aux = format[j];
				//System.out.println(aux);
				Double cell_value= Double.parseDouble(aux);
				res.setCell(i, j, cell_value);
				
				j++;
			}
		j=0;
		i++;
		
		}

		return res;
	}
	
//	public static Matrix transformAdd(Command c, Map<String,Matrix> m){
//		
//		List<Command> lca = FormatTransform.splitByAdd(c);
//		
//		Matrix res;
//		if(lca.get(0).isTypeAlpha()==true){
//			res=CreateMatrix.copyOf(createMatrixFromCommand(lca.get(0)));
//		} else {
//			res=CreateMatrix.copyOf(m.get(lca.get(0).getCommand()));
//		}
//		res.cleanMatrix();
//		for(Integer i=0;i<lca.size();i++){
//			Command aux=lca.get(i);
//			
//			if(aux.isTypeAlpha()){
//				Matrix a=createMatrixFromCommand(aux);
//				if(i.equals(0)){
//					res=a;
//				} else {
//					res=Matrices.addMatrix(res, createMatrixFromCommand(aux));
//				}
//			} else if(aux.isTypeV()){
//				res=Matrices.addMatrix(res, m.get(aux.getCommand()));
//			}
//		}
//		
//		return res;
//	}
	
	public static Matrix transform(Command c,Map<String,Matrix> m, Matrix res,boolean isTheFirstMatrix){
		
		Command[] cArr = c.splitByBrackets();
		
		if(cArr[0].containsBrackets()==true){
			res=transform(cArr[0],m,res,isTheFirstMatrix);
		} else{
			
//			res=createMatrixFromCommand(cArr[])
			
			if(cArr[0].containsAdd()){
				Command[] cAdd = cArr[0].splitByAdd();
				for(Integer i=0;i<cAdd.length;i++){
	
					if(cAdd[i].containsSub()){
						Command[] cSub=cAdd[i].splitBySub();
						Matrix aux2=null;
						for(Integer j=0;j<cSub.length;j++){
							
							if(cSub[j].containsMult()){
								Command[] cMult=cSub[j].splitByMult();
								Matrix aux = null;
								for(Integer k=0;k<cMult.length;k++){
									aux=Matrices.multiplyMatrix(aux, createMatrixFromCommand(cMult[k]));
								}
								aux2=Matrices.subtractMatrix(res, aux);
							} else {
								aux2=Matrices.subtractMatrix(aux2, createMatrixFromCommand(cSub[j]));
							}
						}
						
						res=Matrices.addMatrix(res, aux2);
					} else{
						res=Matrices.addMatrix(res, createMatrixFromCommand(cAdd[i]));
					}
					
				}
			} else if(cArr[0].containsSub()){
				Command[] cSub = cArr[0].splitBySub();
				for(Integer i=0;i<cSub.length;i++){
					if(cSub[i].containsMult()){
						Matrix aux = null;
						Command[] cMult = cSub[i].splitByMult();
						for(Integer j=0;j<cMult.length;j++){
							aux=Matrices.multiplyMatrix(aux, createMatrixFromCommand(cMult[j]));
						}
					} else {
						res=Matrices.subtractMatrix(res, createMatrixFromCommand(cSub[i]));
					}
					
				}
			} else {
				Command[] cMult=cArr[0].splitByMult();
				for(Integer i=0;i<cMult.length;i++){
					res=Matrices.multiplyMatrix(res, createMatrixFromCommand(cMult[i]));
				}
			}
			
		}
 		
		if(cArr[1].isTheAddCommand()==true){
			res=Matrices.addMatrix(res, transform(cArr[1], m, res,isTheFirstMatrix));
		} else if(cArr[1].isTheSubCommand()==true){
			
			if(cArr[1].theOperatorWasToRight()){
				res=Matrices.subtractMatrix(res, transform(cArr[1],m,res,isTheFirstMatrix));

			} else {
				res=Matrices.subtractMatrix(transform(cArr[1],m,res,isTheFirstMatrix),res);
			}
		} 
//			else {
//			//Mejor que esto, debería hacer que hiciera sólo un split del primer signo distinto del '*'
//			Command[] aux=cArr[1].splitByAdd();
//			if(cArr[1].theOperatorWasToRight()){
//				if(aux[0].containsSub()){
//					//TODO RELLENAR
//				} else{
//					res=Matrices.multiplyMatrix(res, transform(aux[0], m, res));
//					Command[] aux2=new Command[aux.length-1];
//					for(Integer i=0;i<aux2.length;i++){ //Crea un array con todos los elementos menos el primero
//						aux2[i]=aux[i+1]; 
//					}
//				}
//			} else {
//				if(aux[aux.length-1].containsSub()){
//					//TODO RELLENAR
//				} else{
//					res=Matrices.multiplyMatrix(transform(aux[aux.length-1], m, res),res);
//					Command[] aux2=new Command[aux.length-1];
//					for(Integer i=0;i<aux2.length;i++){ //Crea un array con todos los elementos menos el primero
//						aux2[i]=aux[i];
//					}
//				}
//			}
//			
//			res=Matrices.addMatrix(res, transform(aux2))
//			
//			
//			
//			
//		}
	
		return res;
		
	}
	
}
