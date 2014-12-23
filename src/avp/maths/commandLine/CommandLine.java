package avp.maths.commandLine;

import java.util.Scanner;

public class CommandLine implements Command{
	
	private String command;
	private String varName;
	
	//Atributos contains
	private boolean isOnlyVar;
	private boolean containsEquals;
	private boolean containsAdd;
	private boolean containsSub;
	private boolean containsMult;
	private boolean containsBrackets;
	
	//Atributos para split
	private boolean isSplitCommand=false;
	private boolean isTheBracketCommand=false;
	private boolean isTheAddCommand=false;
	private boolean isTheSubCommand=false;
	private boolean isTheMultCommand=false;
	private boolean theOperatorWasToRight=false; //true si el operador estaba a la derecha del paréntesis
	
	public CommandLine(Scanner sc){
		String c=sc.nextLine();
		contains(c);
		c=deleteBlanks(c);
		this.command=deleteBlanksIntoMatrix(c);
	}
	
	public CommandLine(String command){
		contains(command);
		this.command=deleteBlanks(command);
		this.command=deleteBlanksIntoMatrix(this.command);
	}
	
	public CommandLine(Command c){
		this.command=c.getCommand();
		contains(command);
	}
	
	//Este constructor se usa sólo cuando el trozo es un split AMBIGUO
	public CommandLine(String command, boolean isTheBracketCommand, boolean isTheAddCommand, boolean isTheSubCommand, boolean isTheMultCommand){
		contains(command);
		this.command=deleteBlanks(command);
		this.command=deleteBlanksIntoMatrix(this.command);
		
		isSplitCommand=true;
		this.isTheBracketCommand=isTheBracketCommand;
		this.isTheAddCommand=isTheAddCommand;
		this.isTheSubCommand=isTheSubCommand;
		this.isTheMultCommand=isTheMultCommand;
	}
	
	private void contains(String command){
		
		if(command.contains("(") && command.contains(")"))
			containsBrackets=true;
		
		if(command.contains("="))
			containsEquals=true;
		
		if(command.contains("+"))
			containsAdd=true;
		
		if(command.contains("-"))
			containsSub=true;
		
		if(command.contains("*"))
			containsMult=true;
		
		if(containsEquals==false && containsAdd==false && containsSub==false && containsMult==false && containsBrackets==false)
			isOnlyVar=true;
		
	}
	
	private String deleteBlanks(String s){
		String res="";
		if(!s.contains("[")){
			res=s.replace(" ", "");
		} else {
			boolean outMatrix=true;
			for(Integer i=0;i<s.length();i++){
				Character character = s.charAt(i);
				
				if(character.equals('[')){
					outMatrix=false;
				}
				
				if(outMatrix && character.equals(' ')){
					res+="";
				} else {
					res+=s.charAt(i);
				}
				
				if(character.equals(']')){
					outMatrix=true;
				}
			}
		}
		return res;
	} 
	
	
	private String deleteBlanksIntoMatrix(String s){
		String res="";
		boolean deleteNext=false;
		for(Integer i=0;i<s.length();i++){
			Integer nextIndex=i+1;
			Character character = s.charAt(i);
			Character character2=' ';
			if(!nextIndex.equals(s.length())){
				character2 =s.charAt(nextIndex);
			}
			if(deleteNext || (character.equals(' ') && character2.equals(';')) || (character.equals(' ') && character2.equals(']')) || (character.equals(' ') && character2.equals(' ')) ){
				res+="";
			}else{
				res+=s.charAt(i);
			}
			if((character.equals('[') && character2.equals(' ')) 
					|| (character.equals(';') && character2.equals(' '))
					|| (character.equals(' ') && character2.equals(' '))){

				deleteNext=true;
			} else {
				deleteNext=false;
			}
		}
		return res;
	}
	
	public String getCommand(){
		return command;
	}
	
	public void SetCommand(String s){
		this.command=s;
	}
	

	public String getVarName() {
		return varName;
	}

	public void setVarName(String s) {
		this.varName=s;
	}
	
	
	@Override
	public boolean isOnlyVar() {
		return isOnlyVar;
	}

	@Override
	public boolean containsEquals() {
		return containsEquals;
	}

	@Override
	public boolean containsAdd() {
		return containsAdd;
	}

	@Override
	public boolean containsSub() {
		return containsSub;
	}

	@Override
	public boolean containsMult() {
		return containsMult;
	}
	
	public boolean containsBrackets(){
		return containsBrackets;
	}
	
	
	//Estos métodos se deben usar solo en caso de que el comando sea resultado de un split	
	public boolean isSplitCommand(){
		return isSplitCommand;
	}
	
	public void setIsSplitCommand(boolean b){
		this.isSplitCommand=b;
	}
	
	public boolean isTheBracketCommand(){
		return isTheBracketCommand;
	}
	
	public void setIsTheBracketCommand(boolean b){
		this.isTheBracketCommand=b;
	}
	
	public boolean isTheAddCommand(){
		return isTheAddCommand;
	}
	
	public void setIsTheAddCommand(boolean b){
		this.isTheAddCommand=b;
	}
	
	public boolean isTheSubCommand(){
		return isTheSubCommand;
	}
	
	public void setIsTheSubCommand(boolean b){
		this.isTheSubCommand=b;
	}
	
	public boolean isTheMultCommand(){
		return isTheMultCommand;
	}	
	
	public void setIsTheMultCommand(boolean b){
		this.isTheMultCommand=b;
	}
	
	public boolean theOperatorWasToRight(){
		return theOperatorWasToRight;
	}
	
	public void setTheOperatorWasToRight(boolean b){
		this.theOperatorWasToRight=b;
	}
	
	//Métodos de formatos de comandos con paréntesis
	
	public Command[] splitByBrackets(){
		//Este bucle era para eliminar los paréntesis innecesarios, ejemplo: (A+B*C)
//		while(getCommand().charAt(0)=='(' && getCommand().charAt(getCommand().length()-1)==')'){
//			//TODO completar bucle
//		}
		
		Boolean firstBracketFound=false;
		Integer firstBracketIndex=0;
		
		Boolean lastBracketFound=false;
		Integer lastBracketIndex=0;
		
		Integer firstBracketCount=0;
		Integer lastBracketCount=0;
		
		for(Integer i=0;i<getCommand().length();i++){
			char aux=getCommand().charAt(i);
			if(aux=='(' && firstBracketFound.equals(false)){
				firstBracketFound=true;
				firstBracketIndex=i;
				firstBracketCount=1;
				
			} else if(aux=='('){
				firstBracketCount++;
			} else if(aux==')'){
				lastBracketCount++;
			}			
			if(lastBracketCount.equals(firstBracketCount) && firstBracketFound.equals(true)){
				lastBracketFound=true;
				lastBracketIndex=i;
				break;
			}
			
		}
				
		Command part1 = new CommandLine("");
		part1.setIsSplitCommand(true);
		part1.setIsTheBracketCommand(true);
		
		Command part2 = new CommandLine("");
		part2.setIsSplitCommand(true);
		
		if(firstBracketIndex.equals(0)){ //Si los paréntesis están en el primer bloque			
			if(getCommand().charAt(lastBracketIndex+1)=='+'){//Configura cada uno de los bloques resultados del split
				part2.setIsTheAddCommand(true);
			} else if(getCommand().charAt(lastBracketIndex+1)=='-'){
				part2.setIsTheSubCommand(true);
			} else {
				part2.setIsTheMultCommand(true);
			}
			
			part2.setTheOperatorWasToRight(true);
			
			for(Integer i=1;i<lastBracketIndex;i++){ //Forma el primer bloque	
				part1.SetCommand(part1.getCommand()+getCommand().charAt(i));
			}
			
			for(Integer i=lastBracketIndex+2;i<getCommand().length();i++){ //Forma el segundo bloque
				part2.SetCommand(part2.getCommand()+getCommand().charAt(i));
			}
		} else if(lastBracketIndex.equals(getCommand().length()-1)){ //Si los paréntesis están en el segundo bloque
			
			if(getCommand().charAt(firstBracketIndex-1)=='+'){ //Configura cada uno de los bloques resultados del split
				part2.setIsTheAddCommand(true);
			} else if(getCommand().charAt(firstBracketIndex-1)=='-'){
				part2.setIsTheSubCommand(true);
			} else {
				part2.setIsTheMultCommand(true);
			}
			
			for(Integer i=0;i<firstBracketIndex-1;i++){ //Forma el segundo bloque a partir del primero
				part2.SetCommand(part2.getCommand()+getCommand().charAt(i));
			}
			for(Integer i=firstBracketIndex+1;i<getCommand().length()-1;i++){//Forma el primer bloque a partir del segundo (el de los paréntesis)
				part1.SetCommand(part1.getCommand()+getCommand().charAt(i));				
			}
			
		} else {
			if(getCommand().charAt(firstBracketIndex-1)=='+'){//Configura cada uno de los bloques resultados del split
				part2.setIsTheAddCommand(true);
			} else if(getCommand().charAt(firstBracketIndex-1)=='-'){
				part2.setIsTheSubCommand(true);
			} else {
				part2.setIsTheMultCommand(true);
			}
			
			for(Integer i=0;i<firstBracketIndex-1;i++){
				part2.SetCommand(part2.getCommand()+getCommand().charAt(i));
				}
			
			if(getCommand().charAt(lastBracketIndex+1)=='*'){ //Si la operación justo después de los paréntesis es una multiplicación, dejo los paréntesis para
				//respetar la prioridad de operaciones.
				for(Integer i=firstBracketIndex;i<=lastBracketIndex;i++){
					part1.SetCommand(part1.getCommand()+getCommand().charAt(i));
				}
				for(Integer i=lastBracketIndex+1;i<getCommand().length();i++){
					part1.SetCommand(part1.getCommand()+getCommand().charAt(i));
				} 
			} else {
				for(Integer i=firstBracketIndex+1;i<lastBracketIndex;i++){
					part1.SetCommand(part1.getCommand()+getCommand().charAt(i));
				}
				for(Integer i=lastBracketIndex+1;i<getCommand().length();i++){
					part1.SetCommand(part1.getCommand()+getCommand().charAt(i));
				}
			}
			
		}
		
		Command[] res={part1,part2};
		
		return res;
	}

	@Override
	public Command splitByEquals() {
		String[] format =getCommand().split("=");
		setVarName(format[0]);
		return new CommandLine(format[1]);
	}

	@Override
	public Command[] splitByAdd() {
		if(containsBrackets()==false){
			String[] format = getCommand().split("+");
			Command[] res = new Command[format.length];
			for(Integer i=0;i<format.length;i++){
				Command aux=new CommandLine(format[i]);
				res[i]=aux;
			}
		} else {
			
			
			
		}
		
		return null;
	}

	@Override
	public Command[] splitBySub() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Command[] splitByMult() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
