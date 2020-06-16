package hiago.silva.model.entities;

public abstract class Veiculo {
	
	private String tipoVeiculo;
	private int ano;
	private long kmRodado;
	private String marca;
	private String modelo;
	private String placa;
	public static double valorPadrao = 10;
	

	
	public Veiculo(int ano, long kmRodado, String marca, String modelo, String placa) {
		this.ano = ano;
		this.kmRodado = kmRodado;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
	}


	
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public int getAno() {
		return ano;
	}


	public long getKmRodado() {
		return kmRodado;
	}


	public String getMarca() {
		return marca;
	}


	public String getModelo() {
		return modelo;
	}


	public String getPlaca() {
		return placa;
	}


	public double getValorPadrao() {
		return valorPadrao;
	}
	
	public abstract double calcularDiaria();
	
	public void atualizarKm(long kmAtual) {
		this.kmRodado = kmAtual;
	}
	
	public String exportar() {
		StringBuilder sb = new StringBuilder();
			
			sb.append(this.getPlaca());
			sb.append(";");
			sb.append(this.getAno());
			sb.append(";");
			sb.append(this.getMarca());
			sb.append(";");
			sb.append(this.getModelo());
			sb.append(";");
			sb.append(this.getKmRodado());
			sb.append(";");
		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Placa: ").append(this.getPlaca());
		sb.append("Ano: ").append(this.getAno());
		sb.append("Marca: ").append(this.getMarca());
		sb.append("Modelo: ").append(this.getModelo());
		sb.append("Km: ").append(this.getKmRodado());

		return sb.toString();
	}
	
	
}
