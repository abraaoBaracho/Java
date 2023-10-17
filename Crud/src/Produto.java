public class Produto {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private double preco;

    public Produto(){
        this.id = proximoId++;
    }
    
    public Produto(int id, String nome, double preco){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    public String toString(){
        return this.id+","+this.nome+","+this.preco;
    }
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public double getPreco(){
        return this.preco;
    }
    public void setPreco(double p){
        this.preco = p;
    }
}

