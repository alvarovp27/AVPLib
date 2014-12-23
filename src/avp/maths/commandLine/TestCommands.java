package avp.maths.commandLine;
import java.util.Scanner;


public class TestCommands {
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		Command command = new CommandLine(sc);
		
		Command[] arrCommand = command.splitByBrackets();
		
		System.out.println("Primer bloque:");
		System.out.println(arrCommand[0].getCommand());
		System.out.println("¿Es resultado de un split? "+arrCommand[0].isSplitCommand());
		System.out.println("¿Es el bloque de los paréntesis? "+arrCommand[0].isTheBracketCommand());
		System.out.println("¿Es suma? "+arrCommand[0].isTheAddCommand());
		System.out.println("¿Es resta? "+arrCommand[0].isTheSubCommand());
		System.out.println("¿Es multiplicación? "+arrCommand[0].isTheMultCommand());

		
		System.out.println();
		
		System.out.println("Segundo bloque:");
		System.out.println(arrCommand[1].getCommand());
		System.out.println("¿Es resultado de un split? "+arrCommand[1].isSplitCommand());
		System.out.println("¿Es el bloque de los paréntesis? "+arrCommand[1].isTheBracketCommand());
		System.out.println("¿Es suma? "+arrCommand[1].isTheAddCommand());
		System.out.println("¿Es resta? "+arrCommand[1].isTheSubCommand());
		System.out.println("¿Es multiplicación? "+arrCommand[1].isTheMultCommand());

		
//		System.out.println("Resultado de ejecutar deleteBlancks: "+FormatTransform.deleteBlanks(s));
		
//		System.out.println("Resultado de ejecutar deleteBlancksIntoMatrix: "+FormatTransform.deleteBlanksIntoMatrix(s));
		
	}
}
