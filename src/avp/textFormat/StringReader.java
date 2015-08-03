package avp.textFormat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//db.execSQL("INSERT INTO WORDTRANSLATIONS VALUES('dormir','v','sleep','v')");
		
		File archivoOrigen=new File ("C:\\Users\\Alvaro\\workspace_JAVA\\AVP_Lib\\files\\phrasal_verbs.txt");
		File archivoDestino = new File("C:\\Users\\Alvaro\\workspace_JAVA\\AVP_Lib\\files\\output.txt");
		
        BufferedReader buffer=null;
        BufferedWriter bufferSalida = null;
        
        List<String> l = new ArrayList<>();
        
		try {
			buffer = new BufferedReader(new java.io.FileReader(archivoOrigen));
//			bufferSalida = new BufferedWriter(new FileWriter(archivoDestino));
			
			String linea = "";
			boolean first = true;
			
			while((linea=buffer.readLine())!=null){
				/*Tratamiento del texto línea a línea*/
				System.out.println(linea);
				String toWrite = "db.execSQL(\"INSERT INTO WORDTRANSLATIONS VALUES('";
				String [] lineaSplit = linea.split(",");
				toWrite+=lineaSplit[1]+"'"+",'phrv','"+lineaSplit[0]+"','phrv')\");\n";
				
				//bufferSalida.write(toWrite);
				l.add(toWrite);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			bufferSalida = new BufferedWriter(new FileWriter(archivoDestino));
			System.out.println("*************");
			for(String s:l){
				System.out.println(s);
				bufferSalida.write(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bufferSalida.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			
		
	}
}
