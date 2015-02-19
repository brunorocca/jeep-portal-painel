package br.com.isobar.jeep.portal.importacao;


public class ImportacaoUtils {
	
	public static Boolean getBoolean(String valor) {
		
		if ("x".equalsIgnoreCase(valor)) {
			return true;
		}
		
		return false;
		
	}

}
