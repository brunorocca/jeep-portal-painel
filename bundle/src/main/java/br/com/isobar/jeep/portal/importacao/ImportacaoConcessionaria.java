package br.com.isobar.jeep.portal.importacao;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import br.com.isobar.jeep.portal.painel.model.ConcessionariaVO;

public class ImportacaoConcessionaria {

	public static List<ConcessionariaVO> convert(File arquivo) {
		
		List<ConcessionariaVO> listaConcessionaria = new ArrayList<ConcessionariaVO>();
		
		Reader in = null;
		Iterable<CSVRecord> linhas = null;
		try {
			in = new FileReader(arquivo);
			linhas = CSVFormat.EXCEL.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		
		linhas.iterator().next(); // Pula a linha do Cabeçalho
		
		for (CSVRecord linhaArquivo : linhas) {
			
			int i = 0;
			ConcessionariaVO concessionaria = new ConcessionariaVO();
			
			concessionaria.setNomeFantasia(linhaArquivo.get(i));
			concessionaria.setCodigo(Long.getLong(linhaArquivo.get(i++)));
			concessionaria.setEmailConsultor(linhaArquivo.get(i++));
			concessionaria.setEmailRepresentante(linhaArquivo.get(i++));
			concessionaria.setRazaoSocial(linhaArquivo.get(i++));
			concessionaria.setEndereco(linhaArquivo.get(i++));
			concessionaria.setTelefone(linhaArquivo.get(i++));
			concessionaria.setFax(linhaArquivo.get(i++));
			concessionaria.setTelefone(linhaArquivo.get(i++));
			concessionaria.setEmail(linhaArquivo.get(i++));
			concessionaria.setBairro(linhaArquivo.get(i++));
			concessionaria.setCep(linhaArquivo.get(i++));
			concessionaria.setCidade(linhaArquivo.get(i++));
			concessionaria.setUf(linhaArquivo.get(i++));
			concessionaria.setWebsite(linhaArquivo.get(i++));
			concessionaria.setVendaNovo(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setVendaSeminovo(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setPecasAcessorios(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setEletrica(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setFunilariaPintura(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setRevisao(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setTestDrive(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setRam(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setDodge(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setJeep(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setChrysler(ImportacaoUtils.getBoolean(linhaArquivo.get(i++)));
			concessionaria.setLatitude(linhaArquivo.get(i++));
			concessionaria.setLongitude(linhaArquivo.get(i++));
			
			listaConcessionaria.add(concessionaria);
			
		}
		
		return listaConcessionaria;
	}
	
	public static void main(String[] args) {
		File file = new File("/home/brunorocca/Desktop/concessionarias.csv");
		
		System.out.println(ImportacaoConcessionaria.convert(file));
	}
	
}
