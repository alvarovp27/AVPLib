package avp.maths.commandLine;

import java.util.ArrayList;
import java.util.List;

public class FormatTransform {
	
	

	public static boolean isValidFormat(String[] s){
		boolean res=true;
		
		
		return res;
	}
	
//	public static String deleteBlanks(String s){
//		String res="";
//		if(!s.contains("[")){
//			res=s.replace(" ", "");
//		} else {
//			boolean outMatrix=true;
//			for(Integer i=0;i<s.length();i++){
//				Character character = s.charAt(i);
//				
//				if(character.equals('[')){
//					outMatrix=false;
//				}
//				
//				if(outMatrix && character.equals(' ')){
//					res+="";
//				} else {
//					res+=s.charAt(i);
//				}
//				
//				if(character.equals(']')){
//					outMatrix=true;
//				}
//			}
//		}
//		return res;
//	}
	
	
//	public static String deleteBlanksIntoMatrix(String s){
//		String res="";
//		boolean deleteNext=false;
//		for(Integer i=0;i<s.length();i++){
//			Integer nextIndex=i+1;
//			Character character = s.charAt(i);
//			Character character2=' ';
//			if(!nextIndex.equals(s.length())){
//				character2 =s.charAt(nextIndex);
//			}
//			
//			if(deleteNext || (character.equals(' ') && character2.equals(';')) || (character.equals(' ') && character2.equals(']')) || (character.equals(' ') && character2.equals(' ')) ){
//				res+="";
//			}else{
//				res+=s.charAt(i);
//			}
//			
//			if((character.equals('[') && character2.equals(' ')) 
//					|| (character.equals(';') && character2.equals(' '))
//					|| (character.equals(' ') && character2.equals(' '))){
//
//				deleteNext=true;
//			} else {
//				deleteNext=false;
//			}
//			
//		}
//		
//		return res;
//	}
	
	public static Command splitByEquals(Command command){
		String s=command.getCommand();
		String[] format=s.split("="); //Añadir una excepción si el nombre de la variable contiene un caracter no permitido
		Command res = new CommandLine(format[1]);
		res.setVarName(format[0]);
		return res;
	}
	
	public static String[] splitByMult(String s){
		return s.split("*");
	}
	
	public static List<Command> splitByAdd(Command c){
		List<Command> res = new ArrayList<>();
		String s=c.getCommand();
		
		String[] format = s.split("\\+"); //Lo de las barras \\ es porque + es un formato especial de Java y si no las pongo lana una excepción
		
		for(Integer i=0;i<format.length;i++){
			Command aux=new CommandLine(format[i]);
			res.add(aux);
		}
		
		return res;
	}
	
	public static String[] splitBySub(String s){
		return s.split("-");
	}
	
}
