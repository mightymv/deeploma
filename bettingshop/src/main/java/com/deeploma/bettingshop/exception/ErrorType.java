package com.deeploma.bettingshop.exception;

import java.util.Arrays;

public enum ErrorType {
	
	UNKNOWN_INTERNAL_ERROR(666, "Nepoznata greska na serveru"),
	
	MATCH_ALREADY_STARTED(601, "Ne mozete se kladiti na zapocet mec"),	
	MATCH_BAD_STATUS(602, "Mec nije u dozvoljenom statusu za kladjenje"),
	TICKET_CANNOT_CONTAIN_MORE_THAN_ONE_ROW_WITH_SAME_MATCH(603, "Tiket ne moze imati vise od jedne opklade na isti mec");
	
	
	private ErrorType(int code) {
		this(code, null);
	}
	
	private ErrorType(int code, String description) {
		this.code = code;
		this.description = description;
		
	}
	
	private final int code;
	private final String description;
	
	/**
	 * @return error code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return description for error code.
	 * It can be null if no description provided.
	 */
	public String getDescription() {
		return description;
	}
		
	public static ErrorType findByCode(int code){
		return Arrays.asList(values())
				.stream()
				.filter(ec -> ec.code == code)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Error Type with code "+code+ " doesn't exist"));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getCode()).append(" - ").append(name());
		if (getDescription() != null) {
			builder.append(" - ").append(getDescription());
		}
		return builder.toString();
	}

}
