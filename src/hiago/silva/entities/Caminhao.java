package hiago.silva.entities;

import hiago.silva.entities.enums.TipoCaminhao;

public class Caminhao extends Veiculo {
	
	private String tipoVeiculo = "Caminhão";
	private long cargaMaxima;
	private int nrEixos;
	private TipoCaminhao tipo;
	
	public Caminhao(int ano, long kmRodado, String marca, String modelo, String placa,
			long cargaMaxima, int nrEixos, TipoCaminhao tipo) {
		super(ano, kmRodado, marca, modelo, placa);
		this.cargaMaxima = cargaMaxima;
		this.nrEixos = nrEixos;
		this.tipo = tipo;
		this.tipoVeiculo = "Caminhão";
	}

	public long getCargaMaxima() {
		return cargaMaxima;
	}

	public int getNrEixos() {
		return nrEixos;
	}

	public TipoCaminhao getTipo() {
		return tipo;
	}
	

	 public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	@Override
		public double calcularDiaria() {
			double valorDiaria = 0;
			
			valorDiaria = (this.getValorPadrao() * this.getCargaMaxima())/ this.getNrEixos();
			
			if(tipo == TipoCaminhao.CAVALOTRUCKADO) {
				valorDiaria = valorDiaria * 1.05;
			}else if (tipo == TipoCaminhao.BITREM) {
				valorDiaria = valorDiaria * 1.1;
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
			sb.append("C");
			sb.append(super.exportar());
			sb.append(this.getNrEixos());
			sb.append(";");
			sb.append(this.getCargaMaxima());
			sb.append(";");
			sb.append(this.getTipo());
			
			return sb.toString();
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Caminhão: ");
			sb.append(super.toString());
			sb.append("Carga Máxima: ").append(this.getCargaMaxima());
			sb.append("Nº de Eixos: ").append(this.getNrEixos());
			sb.append("Tipo Caminhão; ").append(this.getTipo());
			
			return sb.toString();
		}
}
