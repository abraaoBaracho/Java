import java.util.Scanner;
import java.time.LocalDate;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean sair = false;

		try {
			Consulta c = new Consulta();
			Paciente p = new Paciente();

			while (!sair) {
				System.out.println("|*****************************|");
				System.out.println("|----Escolha a opção desejada-|");
				System.out.println("|1 - Cadastrar Paciente-------|");
				System.out.println("|2 - Marcar Consultas---------|");
				System.out.println("|3 - Cancelamento de Consultas|");
				System.out.println("|4 - Sair---------------------|");
				System.out.println("|*****************************|");

				byte escolha = scan.nextByte();
				scan.nextLine();
				switch (escolha) {
				case 1:
					System.out.println("|Informe o nome do paciente: ----|");
					String nome = scan.nextLine();
					System.out.println("|Informe o telefone do paciente: |");
					String fone = scan.nextLine();
					p.setNome(nome);
					p.setTelefone(fone);
					p.salvar(p);
					break;
				case 2:
					System.out.println("|Informe numero do paciente que deseja marcar a consulta: |");
					List<Paciente> listaPacientes = p.listar();
					for (Paciente listar : listaPacientes) {
						System.out.println(listar.toString());
					}
					String paciente = listaPacientes.get(scan.nextInt() - 1).getNome();
					scan.nextLine();

					System.out.println("|Informe a data que deseja marcar a consulta: |");
					System.out.println("|Obs: digite a data nesse formato 01/01/2024--|");
					String data = scan.nextLine();
					String[] dataFormatada = data.split("/");
					LocalDate consulta = LocalDate.of(Integer.parseInt(dataFormatada[2]),
							Integer.parseInt(dataFormatada[1]), Integer.parseInt(dataFormatada[0]));
					if (c.checarDisponbilidade(consulta)) {

						String hora = scan.nextLine();

						System.out.println("Informe a especialidada mediaca desejada: ");
						String especialidade = scan.nextLine();

						System.out.println("Os dados estão corretos? ");
						System.out.println("Paciente " + paciente + " Data: " + data + " Horario: " + hora
								+ " Especialidade: " + especialidade + ".");
						System.out.println("Digite sim ou nao. ");
						String conf = scan.nextLine().toLowerCase();
						if (conf.equals("sim")) {
							c.marcarConsulta(paciente, consulta, hora, especialidade);
						} else {
							System.out.println("Corrija as informaçoes.");
						}

					} else {
						System.out.println("Data não disponivel");
					}
					break;
				case 3:
					List<Consulta> listaConsulta = c.listarConsultas();
					for (Consulta listar : listaConsulta) {
						System.out.println(listar.toString());
					}
					System.out.println("Informe o numero da consulta que deseja remover:");
					int remover = scan.nextInt();
					scan.nextLine();
					c.deletar(listaConsulta.get(remover - 1));
					break;

				case 4:
					sair = true;
					break;
				default:
					System.out.println("Opção invalida");
				}
			}
		} catch (IOException e) {
			System.out.println("Algo deu errado");
			e.printStackTrace();
		} finally {
			System.out.print("Encerrando");
			scan.close();
		}
	}
}
