
public class Agenda {
    
    private int id;
    private String nome;
    private String telefone;
    private String email;
    
    public Agenda(){
    }
    
    public Agenda(int id, String nome, String telefone, String email){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    
    @Override
	public String toString() {
		return "Item  nome: " + nome + ", telefone: " + telefone + ", email: " + email;
	}
    
    public String exibir() {
    	return this.id+", "+this.nome+", "+this.telefone+", "+this.email;
    }

	public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getTelefone(){
        return this.telefone;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public int getId(){
        return this.id;
    }
} 