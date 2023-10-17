import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private static final String NOME_ARQUIVO = "agenda.txt";
    private static int id;
    
    public void salvar(Agenda agenda){
        //tenta abrir o arquivo txt caso nao exista ele cria e abre para escrita
        try(FileWriter criar = new FileWriter(NOME_ARQUIVO, true);PrintWriter escrever = new PrintWriter(criar);BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO));
           ){
               String linha;
               
               while ((linha = br.readLine()) != null){
                   String[] dados = linha.split(",");
                   if(this.id <= Integer.parseInt(dados[0])){
                       this.id += 1;
               }
               }
               
               escrever.println(this.id+","+agenda.getNome()+","+agenda.getTelefone()+","+agenda.getEmail());
               System.out.println("Produto Cadastrado com sucesso");
            
        }catch( IOException e){
            e.printStackTrace();
        }
    }
    
    public void editar(Agenda a){
        
        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            //cria a string linha para salvar a linha e a variavel para saber se o produto ja foi cadastrado
            boolean itemLocalizado = false;
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(",");
                if(a.getId() == Integer.parseInt(dados[0])){
                    linha = dados[0]+","+a.getNome()+","+a.getTelefone()+","+a.getEmail();
                    itemLocalizado = true;
                }
                    conteudo.append(linha).append("\n");
            }
            if(itemLocalizado){
                try (PrintWriter printWriter = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
                    printWriter.print(conteudo.toString());
                }
                
            }else{
                System.out.println("Item nao localizado");
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public void deletar(Agenda a){
        
        //cria uma lista para salvar os dados escristos no arquivo
        List <String> item = new ArrayList<>();
        //le o arquivo de texto
        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            //cria a string linha para salvar a linha
            String linha;
            while ((linha = br.readLine()) != null){
                //divide a linha pela ',' e salva no array dados
                String[] dados = linha.split(",");
                //quando o id que vai ser deletado for localizado pula a linha
                if(a.getId() == Integer.parseInt(dados[0])){
                   
                    continue;
                }
                //adiciona as linhas restantes no array
                item.add(linha);
            }
            
        }catch (IOException e){
            e.printStackTrace();
            return;// casa aconteca algum erro nao altera o arquivo
        }
        // carrega um novo arquivo com a linha deletada
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
    
    public List<Agenda> listar(){
        
       List<Agenda> agenda =  new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            String linha;
            while((linha = br.readLine()) != null){
                String[] dados = linha.split(",");
                
                agenda.add(new Agenda(Integer.parseInt(dados[0]), dados[1],dados[2],dados[3]));
            }
        }catch(IOException e){
            System.out.println("Arquivo nao localizado");
        }
        
        return agenda;
    }
    
    public Agenda pegarItem(int id){
        
        Agenda item = null;
        boolean localizado = false;
        
        try(BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            //cria a string linha para salvar a linha e a variavel para saber se o produto ja foi cadastrado
            String linha;
            
            //execulta o laco pegando cada linha e salvando na lista dados sepando pela ','
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                //se o id ja estiver cadastrado finaliza o laco e define o produtoCadastrado com true
                if(id == Integer.parseInt(dados[0])){
                    item = new Agenda(Integer.parseInt(dados[0]), dados[1],dados[2],dados[3]);
                        localizado = true;
                        break;
                }
            }
            
            if(!localizado){
                System.out.println("Item nao localizado");
            return null;
            }
            
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Erro");
        }
       return item;
    }
}