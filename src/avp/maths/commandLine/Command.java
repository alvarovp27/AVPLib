package avp.maths.commandLine;

public interface Command {
	
	String getCommand();
	
	public void SetCommand(String s);
	
	public String getVarName();
	
	public void setVarName(String s);
	
	
	//CAMBIAR ESTOS MÉTODOS POR CONTAINS
	
	public boolean isOnlyVar();
	
	public boolean containsEquals();
	
	public boolean containsAdd();
	
	public boolean containsSub();
	
	public boolean containsMult();
	
	public boolean containsBrackets();

	//Estos métodos se deben usar solo en caso de que el comando sea resultado de un split	
	public boolean isSplitCommand();
	
	public void setIsSplitCommand(boolean b);
	
	public boolean isTheBracketCommand();
	
	public void setIsTheBracketCommand(boolean b);
	
	public boolean isTheAddCommand();
	
	public void setIsTheAddCommand(boolean b);
	
	public boolean isTheSubCommand();
	
	public void setIsTheSubCommand(boolean b);
	
	public boolean isTheMultCommand();
	
	public void setIsTheMultCommand(boolean b);
	
	public boolean theOperatorWasToRight();
	
	public void setTheOperatorWasToRight(boolean b);
	
	//Propiedades derivadas
	
	public Command[] splitByBrackets();
	
	public Command splitByEquals();
	
	public Command[] splitByAdd();
	
	public Command[] splitBySub();
	
	public Command[] splitByMult();

}
