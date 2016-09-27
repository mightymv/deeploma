package com.deeploma.bettingshop.controler.errorhnd;

/**
 * Info about error.
 * 
 */
public final class ErrorInfo {

	private String errorCode;
	private String localized;
	
	/**
	 * Creates empty error info.
	 */
	public ErrorInfo() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getLocalized() {
		return localized;
	}

	public void setLocalized(String localized) {
		this.localized = localized;
	}


}
