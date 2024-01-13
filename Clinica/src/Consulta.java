import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

public class Consulta {
	@Override
	public String toString() {
		return Id +"- Paciente: " + NomePaciente +" no dia " + Dia + " no horario " + Hora + " para o " + Especialidade;
	}

	private static final String NOME_ARQUIVO = "Arquivos/Consulta.txt";

	private String Dia;
	private String Hora;
	private String Especialidade;
	private ArrayList<String> Horarios;
	private FileWriter Criar;
	private int Id = 1;
	private String NomePaciente;

	public Consulta() throws IOException {
		Criar = new FileWriter(NOME_ARQUIVO, true);
		Horarios = new ArrayList<>();
		setHorarios();
		setId();
	}

	public Consulta(int id, String nomePaciente, String dia, String hora, String especialidade) {
		Id = id;
		Dia = dia;
		Hora = hora;
		Especialidade = especialidade;
		NomePaciente = nomePaciente;
	}

	public void marcarConsulta(String nomePaciente, LocalDate data, String hora, String especialidade)
			throws IOException {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFormatada = data.format(formatoData);

		try (PrintWriter escrever = new PrintWriter(Criar);) {
			escrever.println(Id + "," + nomePaciente + "," + dataFormatada + "," + hora + "," + especialidade);
			System.out.println("Consulta marcada com sucesso");
		}

	}

	public boolean checarDisponbilidade(LocalDate data) {
		LocalDate dataLocal = LocalDate.now();
		long dia = ChronoUnit.DAYS.between(dataLocal, data);
		if (dia >= 0) {
			HorariosDisponives(dia);
			return true;
		} else {
			System.out.println("A data escolhida não está disponivel.");
			return false;
		}
	}

	public void HorariosDisponives(long dia) {

		List<String> horariosDisponives = Horarios;

		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				if (horariosDisponives.contains(dados[2])) {
					horariosDisponives.remove(dados[2]);
				}
			}
			if (dia == 0) {
				LocalTime horaAtual = LocalTime.now();
				for (int i = horaAtual.getHour(); i >= 0; i--) {
					horariosDisponives.remove(i + ":00");
				}
			}
		} catch (IOException e) {
			System.out.println("Arquivo nao localizado");
		}

		System.out.println("|Informe a hora que deseja marcar a consulta: |");
		System.out.println(horariosDisponives);

	}

	public List<Consulta> listarConsultas() {

		List<Consulta> consulta = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");

				consulta.add(new Consulta(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3], dados[4]));
			}
		} catch (IOException e) {
			System.out.println("Arquivo nao localizado");
		}

		return consulta;
	}
	
public void deletar(Consulta c){
        
        List <String> item = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
         
            String linha;
            while ((linha = br.readLine()) != null){
                  String[] dados = linha.split(",");
         
                if(c.getId() == Integer.parseInt(dados[0])){
                   
                    continue;
                }
                item.add(linha);
            }
            
        }catch (IOException e){
           System.out.println("Desculpe algo deu errado"); 
           e.printStackTrace();
           
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (String linha : item) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("Produto apagado com sucesso.");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("erro");
        }
        
    }
    

	public void setHorarios() {
		for (int i = 8; i <= 16; i++) {
			if (i != 12) {
				String horaString = i + ":00";
				Horarios.add(horaString);
			}
		}
	}

	public void setId() {
		try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				Id = Integer.parseInt(dados[0]) + 1;
			}
        }catch(IOException e){
            System.out.println("Arquivo nao localizado");
        }
		
	}
	

	public int getId() {
		return Id;
	}

	public ArrayList<String> getHorarios() {
		return Horarios;
	}

	public String getDia() {
		return Dia;
	}

	public void setDia(String dia) {
		Dia = dia;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getEspecialidade() {
		return Especialidade;
	}

	public void setEspecialidade(String especialidade) {
		Especialidade = especialidade;
	}

}
