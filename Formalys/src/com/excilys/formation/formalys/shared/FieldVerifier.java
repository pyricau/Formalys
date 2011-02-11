package com.excilys.formation.formalys.shared;

public class FieldVerifier {
	
	public static boolean emptyContent(String content) {
		return content==null || content.trim().equals("");
	}

}
