package avp.textFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by Álvaro Valencia
 * 01/08/2015
 * 
 * */

/**
 * This object contains information about the format of a text file to read.
 */
public class LineParserInfo {

	private int fieldNumber;
	private String firstSplitFormat;
	private List<Object> nSplitFormat = new ArrayList<>();

	private List<String> textToInsert = new ArrayList<>();
	
	/** 
	 * Private constructor
	 * */
	private LineParserInfo(int fieldNumber, String firstSplitFormat,
			List<Object> nSplitFormat, List<String> textToInsert) {
		super();
		this.fieldNumber = fieldNumber;
		this.firstSplitFormat = firstSplitFormat;
		this.nSplitFormat = nSplitFormat;
		this.textToInsert = textToInsert;
	}
	
	
	/**
	 * Public constructors
	 * */
	private static LineParserInfo create(){
		return new LineParserInfo(0, null, null, null);
	}
	
	private static LineParserInfo create(int fieldNumber){
		return new LineParserInfo(fieldNumber, null, null, null);
	}
	
	private static LineParserInfo create(int fieldNumber, String firstSplitFormat){
		return new LineParserInfo(fieldNumber, firstSplitFormat, null, null);
	}
	
	private static LineParserInfo create(int fieldNumber, String firstSplitFormat, List<Object> nSplitFormat){
		return new LineParserInfo(fieldNumber, firstSplitFormat, nSplitFormat, null);
	}

	private static LineParserInfo create(int fieldNumber, String firstSplitFormat, List<Object> nSplitFormat, List<String> textToInsert){
		return new LineParserInfo(fieldNumber, firstSplitFormat, nSplitFormat, textToInsert);
	}
	
	
	/** 
	 * Getters and setters 
	 * */
	
	public int getFieldNumber() {
		return fieldNumber;
	}

	public void setFieldNumber(int fieldNumber) {
		this.fieldNumber = fieldNumber;
	}

	public String getFirstSplitFormat() {
		return firstSplitFormat;
	}

	public void setFirstSplitFormat(String firstSplitFormat) {
		this.firstSplitFormat = firstSplitFormat;
	}

	public List<Object> getnSplitFormat() {
		return copyOf(nSplitFormat);
	}

	public void setnSplitFormat(List<Object> nSplitFormat) {
		this.nSplitFormat = nSplitFormat;
	}

	public List<String> getTextToInsert() {
		return copyOf(textToInsert);
	}

	public void setTextToInsert(List<String> textToInsert) {
		this.textToInsert = textToInsert;
	}

	
	private <T> List<T> copyOf(List<T> toCopy) {
		List<T> res = new ArrayList<>(toCopy);
		return res;
	}

}
