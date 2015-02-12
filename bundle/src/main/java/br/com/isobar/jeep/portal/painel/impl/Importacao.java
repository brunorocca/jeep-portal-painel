//package br.com.isobar.jeep.portal.painel.impl;
//
//import java.io.FileReader;
//import java.io.Reader;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVRecord;
//
//public class Importacao {
//
//	public static void main(String[] args) throws Exception {
//		Reader in = new FileReader("/home/brunorocca/Desktop/concessionarias.csv");
//		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
//		for (CSVRecord record : records) {
//		    String nome = record.get(0);
//		    String codigo = record.get(1);
//		    
//		    System.out.println(codigo);
//		}
//	}
//
//}
