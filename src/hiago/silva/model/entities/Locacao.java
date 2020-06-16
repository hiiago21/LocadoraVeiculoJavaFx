package hiago.silva.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Locacao {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private Date dataLocacao;
	private String nome;
	private Veiculo veiculoLocado;
	private double vlrDiaria;

	public Locacao(Date dataLocacao, String nome, Veiculo veiculoLocado) {
		this.dataLocacao = dataLocacao;
		this.nome = nome;
		this.veiculoLocado = veiculoLocado;
		this.vlrDiaria = veiculoLocado.calcularDiaria();
	}


	public Date getDataLocacao() {
		return dataLocacao;
	}

	public String getNome() {
		return nome;
	}

	public Veiculo getVeiculoLocado() {
		return veiculoLocado;
	}

	public double getVlrDiaria() {
		return vlrDiaria;
	}

	public double calculaValor(int dias) {
		return this.vlrDiaria * dias;
	}

	public double devolver(int dias, long kmRodados) {
		long kmAtualizado = veiculoLocado.getKmRodado() + kmRodados;
		veiculoLocado.atualizarKm(kmAtualizado);

		return this.vlrDiaria * dias;
	}

	public String exportar() {
		StringBuilder sb = new StringBuilder();

		sb.append("L");
		sb.append("L");
		sb.append(sdf.format(this.getDataLocacao()));
		sb.append(";");
		sb.append(this.getVlrDiaria());
		sb.append(";");
		sb.append(this.getNome());
		sb.append(";");
		sb.append(this.veiculoLocado.getValorPadrao());
		sb.append(";");
		sb.append(this.veiculoLocado.getPlaca());

		return sb.toString();
	}

	public void valorDiariaImportacao(double vlrdiaria) {
		this.vlrDiaria= vlrdiaria;
	}
}
