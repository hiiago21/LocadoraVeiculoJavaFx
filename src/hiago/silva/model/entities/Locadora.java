package hiago.silva.model.entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hiago.silva.model.entities.enums.Estilo;
import hiago.silva.model.entities.enums.TipoCaminhao;

public class Locadora {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private List<Veiculo> veiculos = new ArrayList<>();
	private List<Locacao> locacao = new ArrayList<>();

	public List<Locacao> getLocacao() {
		return locacao;
	}

	public boolean compraVeiculo(Veiculo veiculo) {

		for (Veiculo v : veiculos) {
			if (v.getPlaca() == veiculo.getPlaca()) {
				return false;
			}
		}

		veiculos.add(veiculo);
		
		return true;
	}

	public Veiculo consultarVeiculo(String placa) {
		for (Veiculo v : veiculos) {
			if (v.getPlaca().equals(placa)) {
				return v;

			}
		}

		return null;
	}

	public boolean devolverVeiculo(String placa, int dias, long kmRodado, double valorLocacao) {

		for (Locacao l : locacao) {
			if (l.getVeiculoLocado().getPlaca().equals(placa)) {
				l.devolver(dias, kmRodado);
				locacao.remove(l);
				return true;
			}
		}

		return false;
	}

	public String exportar() {

		String path = "C:\\Users\\locadora.txt";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

			for (Veiculo v : veiculos) {
				bw.write(v.exportar().toUpperCase());
				bw.newLine();
			}
			for (Locacao l : locacao) {
				bw.write(l.exportar().toUpperCase());
				bw.newLine();
			}

		} catch (IOException e) {
			e.getMessage();
			return "Erro na exportação";
		}

		return "Exportação Concluida!";
	}

	public void importar(String linha) {

		String path = linha;

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {

				String[] dados = line.split(";");

				String tipo = dados[0].substring(0, 1);
				if (tipo.equals("V")) {
					String tipoVeiculo = dados[0].substring(1, 2);
					switch (tipoVeiculo) {

					case "P":
						String placa = line.substring(2, 9);
						String ano = dados[1];
						String marca = dados[2];
						String modelo = dados[3];
						String kmRodado = dados[4];
						String luxo = dados[5];
						String nroPortas = dados[6];
						String potencia = dados[7];

						Passeio p = new Passeio(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
								Boolean.parseBoolean(luxo), Integer.parseInt(nroPortas), Integer.parseInt(potencia));

						veiculos.add(p);

						break;

					case "M":
						placa = line.substring(2, 9);
						ano = dados[1];
						marca = dados[2];
						modelo = dados[3];
						kmRodado = dados[4];
						String estiloMoto = dados[5];
						potencia = dados[6];
						Moto m = new Moto(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
								Estilo.valueOf(estiloMoto.toUpperCase()), Integer.parseInt(potencia));

						veiculos.add(m);

						break;

					case "C":
						placa = line.substring(2, 9);
						ano = dados[1];
						marca = dados[2];
						modelo = dados[3];
						kmRodado = dados[4];
						String nrEixos = dados[5];
						String cargaMaxima = dados[6];
						tipo = dados[7];

						Caminhao c = new Caminhao(Integer.parseInt(ano), Long.parseLong(kmRodado), marca, modelo, placa,
								Long.parseLong(cargaMaxima), Integer.parseInt(nrEixos),
								TipoCaminhao.valueOf(tipo.toUpperCase()));

						veiculos.add(c);
						break;

					}
				} else if (tipo.equals("L")) {
					
					String data = line.substring(2, 12);
					String nome = dados[2];
					String placa = dados[3];
					
					try {
						locar(placa, nome, sdf.parse(data));

					} catch (ParseException e) {
						e.getMessage();
					}
				}

				line = br.readLine();
			}

		} catch (IOException e) {
			e.getMessage();
		}
	}

	public boolean locar(String placa, String nome, Date dataLocacao) {

		for (Locacao l : locacao) {
			
			if (l.getVeiculoLocado().getPlaca().equals(placa)) {
				return false;
			}
		}

		for (Veiculo v : veiculos) {
			if (v.getPlaca().equals(placa)) {
				locacao.add(new Locacao(dataLocacao, nome.toUpperCase(), v));
				return true;
			}
		}
		return false;
	}

	public double simularValorDevolucao(String placa, int dias) {
		double valSimu = 0.0;

		System.out.println(placa + dias);

		for (Veiculo v : veiculos) {
			if (v.getPlaca().equals(placa)) {
				valSimu = v.calcularDiaria();
				System.out.println(valSimu);
			}
		}

		return valSimu * dias;
	}

	public List<Veiculo> veiculosDisponiveis() {

		List<Veiculo> disponiveis = new ArrayList<Veiculo>();

		veiculos.forEach(v -> disponiveis.add(v));
		locacao.forEach(l -> disponiveis.remove(l.getVeiculoLocado()));

		return disponiveis;
	}

	public String vender(String placa) {

		for (Locacao l : locacao) {
			if (l.getVeiculoLocado().getPlaca() == placa) {
				return "Veículo Locado";
			}
		}

		for (Veiculo v : veiculos) {
			if (v.getPlaca() == placa) {
				return "Veículo para ser vendido" ;
			}
		}

		return "Veiculo não consta no sistema";
	}
}