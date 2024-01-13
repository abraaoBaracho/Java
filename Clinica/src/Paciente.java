import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
	@Override
	public String toString() {
		return "Paciente : " + Id + ", Nome: " + Nome + ", Telefone: " + Telefone;
	}

	private static final String NOME_ARQUIVO = "Arquivos/Pacientes.txt";
	private int Id = 1;
	private String Nome;
	private String Telefone;

	public Paciente() {
		
	}
	public Paciente(int id, String nome, String fone) {
		Id = id;
		Nome = nome;
		String caracteresParaRemover = "[-/,.\\s]";
		Telefone = fone.replaceAll(caracteresParaRemover, "");
		
	}

	public void salvar(Paciente p) {
		try (FileWriter criar = new FileWriter(NOME_ARQUIVO, true);
				PrintWriter escrever = new PrintWriter(criar);
				BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO));) {
			
			String linha;
			boolean achou = false;

			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(",");
				if (p.getTelefone().equals(dados[2])) {
					achou = true;
					break;
				}
				Id = Integer.parseInt(dados[0]) + 1;
			}
			
			if(achou) {
				System.out.println("Paciente já está cadastrado");
			}else {
				 escrever.println(p.getId() + "," + p.getNome() + "," + p.getTelefone());
	               System.out.println("Paciente Cadastrado com sucesso");
			}

		} catch (IOException e) {
		    System.out.println("Desculpe algo deu errado " + e);
		}
	}

	

	public List<Paciente> listar(){
        
	       List<Paciente> pacientes =  new ArrayList<>();
	        
	        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
	            String linha;
	            while((linha = br.readLine()) != null){
	                String[] dados = linha.split(",");
	                
	                pacientes.add(new Paciente(Integer.parseInt(dados[0]), dados[1], dados[2]));
	            }
	        }catch(IOException e){
	            System.out.println("Arquivo nao localizado");
	        }
	        
	        return pacientes;
	    }
	
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		String caracteresParaRemover = "[-/,.\\s]";
		Telefone = telefone.replaceAll(caracteresParaRemover, "");
	}
	
	public int getId() {
		return Id;
	}
}
