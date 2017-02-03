package cellsociety_team12;

public class XMLException extends RuntimeException{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public XMLException(String information, Object values) {
		super(String.format(information, values));
	}
	
	
	
	public XMLException(Throwable e) {
		super(e);
	}
	
	
}
