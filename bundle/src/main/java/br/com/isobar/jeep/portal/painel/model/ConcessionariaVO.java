package br.com.isobar.jeep.portal.painel.model;

import com.google.gson.annotations.SerializedName;

public class ConcessionariaVO {
	
	/*
	   "dealersCode": "1",
	   "bairro":"Santa Inês",
	   "cep": "69907-701",
	   "chrysler": true,
	   "cidade": "Rio Branco",
	   "dodge": true,
	   "eletrica": true,
	   "email_consultor": "rodrigo.albuquerque@fcagroup.com",
	   "email": "walter.filho@acrediesel.com.br",
	   "endereco": "Rodovia BR. 364, 4260 -  S. 1",
	   "fax": "68-3212-1059",
	   "funilariaPintura": true,
	   "jeep": true,
	   "nomeFantasia": "Acrediesel",
	   "pecasAcessorios": true,
	   "ram": true,
	   "razaoSocial": "Raviera Motors Comercial de Veículos LTDA.",
	   "revisao": true,
	   "telefone": "68-3212-1000",
	   "testDrive": true,
	   "uf": "AC",
	   "vendaNovo": true,
	   "vendaSeminovo": true,
	   "website": "",
	   "latitude": -10.0134184,
	   "longitude": -67.80112629999996
	 */
	
	@SerializedName("dealersCode")
	private Long codigo;
	private String nome;
	private String nomeFantasia;
	private String endereco;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;
	private String telefone;
	@SerializedName("email_consultor")
	private String emailConsultor;
	private String email;
	private String emailRepresentante;
	private String fax;
	private String website;
	private String latitude;
	private String longitude;
	private boolean funilariaPintura;
	private boolean jeep;
	private boolean pecasAcessorios;
	private boolean ram;
	private String razaoSocial;
	private boolean revisao;
	private boolean testDrive;
	private boolean vendaNovo;
	private boolean vendaSeminovo;
	private boolean dodge;
	private boolean eletrica;
	private boolean chrysler;
	
	public ConcessionariaVO() {
		super();
	}
	public ConcessionariaVO(Long codigo) {
		super();
		this.codigo = codigo;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmailConsultor() {
		return emailConsultor;
	}
	public void setEmailConsultor(String emailConsultor) {
		this.emailConsultor = emailConsultor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public boolean isFunilariaPintura() {
		return funilariaPintura;
	}
	public void setFunilariaPintura(boolean funilariaPintura) {
		this.funilariaPintura = funilariaPintura;
	}
	public boolean isJeep() {
		return jeep;
	}
	public void setJeep(boolean jeep) {
		this.jeep = jeep;
	}
	public boolean isPecasAcessorios() {
		return pecasAcessorios;
	}
	public void setPecasAcessorios(boolean pecasAcessorios) {
		this.pecasAcessorios = pecasAcessorios;
	}
	public boolean isRam() {
		return ram;
	}
	public void setRam(boolean ram) {
		this.ram = ram;
	}
	public boolean isRevisao() {
		return revisao;
	}
	public void setRevisao(boolean revisao) {
		this.revisao = revisao;
	}
	public boolean isTestDrive() {
		return testDrive;
	}
	public void setTestDrive(boolean testDrive) {
		this.testDrive = testDrive;
	}
	public boolean isVendaNovo() {
		return vendaNovo;
	}
	public void setVendaNovo(boolean vendaNovo) {
		this.vendaNovo = vendaNovo;
	}
	public boolean isVendaSeminovo() {
		return vendaSeminovo;
	}
	public void setVendaSeminovo(boolean vendaSeminovo) {
		this.vendaSeminovo = vendaSeminovo;
	}
	public boolean isDodge() {
		return dodge;
	}
	public void setDodge(boolean dodge) {
		this.dodge = dodge;
	}
	public boolean isEletrica() {
		return eletrica;
	}
	public void setEletrica(boolean eletrica) {
		this.eletrica = eletrica;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getEmailRepresentante() {
		return emailRepresentante;
	}
	public void setEmailRepresentante(String emailRepresentante) {
		this.emailRepresentante = emailRepresentante;
	}
	public boolean isChrysler() {
		return chrysler;
	}
	public void setChrysler(boolean chrysler) {
		this.chrysler = chrysler;
	}
	@Override
	public String toString() {
		return "ConcessionariaVO [codigo=" + codigo + ", nomeFantasia=" + nomeFantasia + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcessionariaVO other = (ConcessionariaVO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
