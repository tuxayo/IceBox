package com.mygdx.dragNdrop;

public class WrongSideException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4309594642810865081L;

	/** 
	* Crée une nouvelle instance de WrongSideException 
	*/  
	public WrongSideException() {}  
	
	/** 
	* Crée une nouvelle instance de WrongSideException 
	* @param message Le message détaillant exception 
	*/  
	public WrongSideException(String message) {  
		super(message); 
	}  
	
	/** 
	* Crée une nouvelle instance de WrongSideException 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public WrongSideException(Throwable cause) {  
		super(cause); 
	}  
	
	/** 
	* Crée une nouvelle instance de WrongSideException 
	* @param message Le message détaillant exception 
	* @param cause L'exception à l'origine de cette exception 
	*/  
	public WrongSideException(String message, Throwable cause) {  
		super(message, cause); 
	} 
}
