package hiago.silva.model.entities;

public class Passeio extends Veiculo{
	
	private String tipoVeiculo;

	private boolean luxo;
	private int nroPortas;
	private int potencia;
	
	public Passeio(int ano, long kmRodado, String marca, String modelo, String placa, boolean luxo,
			int nroPortas, int potencia) {
		super(ano, kmRodado, marca, modelo, placa);
		this.luxo = luxo;
		this.nroPortas = nroPortas;
		this.potencia = potencia;
		this.tipoVeiculo = "Passeio";
	}

	public boolean isLuxo() {
		return luxo;
	}

	public int getNroPortas() {
		return nroPortas;
	}

	public int getPotencia() {
		return potencia;
	}	
		
	
	 public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	@Override
	public double calcularDiaria() {
		double valorDiaria = 0;
		
		valorDiaria = (this.getValorPadrao() * this.getPotencia()) / this.getNroPortas();
		
		if(this.isLuxo()) {
			valorDiaria = valorDiaria * 1.2;
		}
		
		if(this.getKmRodado() > 20.000) {
			valorDiaria -= valorDiaria*0.15;
		}else if (this.getKmRodado() > 10.000) {
			valorDiaria -= valorDiaria*0.10;
		}
		
		return valorDiaria;
	}
	 
	public String exportar() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("V");
		sb.append("P");
		sb.append(super.exportar());
		sb.append(this.isLuxo());
		sb.append(";");
		sb.append(this.getNroPortas());
		sb.append(";");
		sb.append(this.getPotencia());
		
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Passageiro: ");
		sb.append(super.toString());
		sb.append("Luxo: ").append(this.isLuxo());
		sb.append("Nº Portas: ").append(this.getNroPortas());
		sb.append("Potencia; ").append(this.getPotencia());
		
		return sb.toString();
	}
	 
	 
}
