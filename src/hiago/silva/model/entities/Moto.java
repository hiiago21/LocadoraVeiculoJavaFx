package hiago.silva.model.entities;

import hiago.silva.model.entities.enums.Estilo;

public class Moto extends Veiculo {

	private Estilo estiloMoto;
	private int potencia;

	public Moto(int ano, long kmRodado, String marca, String modelo, String placa, Estilo estiloMoto, int potencia) {
		super(ano, kmRodado, marca, modelo, placa);
		this.estiloMoto = estiloMoto;
		this.potencia = potencia;
	}

	public Estilo getEstiloMoto() {
		return estiloMoto;
	}

	public int getPotencia() {
		return potencia;
	}

	@Override
	public double calcularDiaria() {
		double valorDiaria = 0;

		valorDiaria = (this.getValorPadrao() * this.getPotencia());

		if (estiloMoto == Estilo.CUSTOM) {
			valorDiaria = valorDiaria * 1.3;
		} else if (estiloMoto == Estilo.ESPORTIVA) {
			valorDiaria = valorDiaria * 1.5;
		}

		if (this.getKmRodado() > 20.000) {
			valorDiaria -= valorDiaria * 0.15;
		} else if (this.getKmRodado() > 10.000) {
			valorDiaria -= valorDiaria * 0.10;
		}

		return valorDiaria;
	}

	public String exportar() {
		StringBuilder sb = new StringBuilder();

		sb.append("V");
		sb.append("M");
		sb.append(super.exportar());
		sb.append(this.getEstiloMoto());
		sb.append(";");
		sb.append(this.getPotencia());

		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Moto: ");
		sb.append(super.toString());
		sb.append("Estilo: ").append(this.getEstiloMoto());
		sb.append("Potencia; ").append(this.getPotencia());

		return sb.toString();
	}
}
