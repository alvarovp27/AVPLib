package avp.textFormat;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

public class FileReader {
	
	private String textRead;
	
	private String path;
	
	private LineParserInfo parserInfo;
	
	private FileReader() {
		// TODO Auto-generated constructor stub
	}
	
	private void readFile(){
        File archivo=null;
        java.io.FileReader fr=null;
        BufferedReader buffer=null;
		try {
			archivo = new File (path);
			fr = new java.io.FileReader(archivo);
			buffer = new BufferedReader(fr);
			
			textRead = "";
			String linea = "";
			boolean first = true;
			
			while((linea=buffer.readLine())!=null){
				
				/*Tratamiento del texto línea a línea*/
				if(parserInfo == null){
					if(!first)
						textRead+="\n";
					else
						first = false;
					
					textRead += linea;
				} else {
					String [] firstSplit = linea.split(parserInfo.getFirstSplitFormat());
					
					
					
				}
					
				
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr ){   
					fr.close();     
				}
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	}
	
	private String parse(String[] toParse,/*String line,*/List<Object> parseInfo,List<String> toWrite){
		String line = "";
		if(!parseInfo.isEmpty()){
			for(int i = 0;i<parseInfo.size();i++){
				if(parseInfo.get(i) instanceof LineParserInfo){
					LineParserInfo lpiAux = (LineParserInfo) parseInfo.get(i);
					String [] nextParse = toParse[i].split(lpiAux.getFirstSplitFormat());
					line+=parse(nextParse,/*line,*/lpiAux.getnSplitFormat(),lpiAux.getTextToInsert());
				} else {
					String strAux = (String) parseInfo.get(i);
					toParse[i].split(strAux);
				}
			}
		}
		
		return line;
	}
	
	
	
}
