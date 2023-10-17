import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static final String FILE_NAME = "produtos.txt";
    
    public void salvarProduto(Produto produto){
        //Cria o arquivo txt
        try (FileWriter writer = new FileWriter(FILE_NAME, true)){
            //le o arquivo salvando na variavel br
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            //cria a string linha para salvar a linha e a variavel para saber se o produto ja foi cadastrado
            String linha;
            boolean produtoCadastrado = false;
            //execulta o laco pegando cada linha e salvando na lista dados sepando pela ','
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                //se o id ja estiver cadastrado finaliza o laco e define o produtoCadastrado com true
                if(produto.getId() == Integer.parseInt(dados[0])){
                    System.out.println("Produto já cadastrado");
                    produtoCadastrado = true;
                    break;
                }
            }
            //fecha o leitor
            br.close();
            // escreve se o produto nao estiver cadastrado
            if(!produtoCadastrado){
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.println(produto.getId() + "," + produto.getNome() + "," + produto.getPreco());
                printWriter.close();
                System.out.println("Produto Cadastrado com sucesso");
            }
            
            
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void apagarProduto(int id){
        //cria uma lista para salvar os dados escristos no arquivo
        List <String> produto = new ArrayList<>();
        //le o arquivo de texto
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            //cria a string linha para salvar a linha
            String linha;
            while ((linha = br.readLine()) != null){
                //divide a linha pela ',' e salva no array dados
                String[] dados = linha.split(",");
                //quando o id que vai ser deletado for localizado pula a linha
                if(id == Integer.parseInt(dados[0])){
                    System.out.println(dados[0]);
                    continue;
                }
                //adiciona as linhas restantes no array
                produto.add(linha);
            }
            
        }catch (IOException e){
            e.printStackTrace();
            return;// casa aconteca algum erro nao altera o arquivo
        }
        // carrega um novo arquivo com a linha deletada
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String linha : produto) {
                bw.write(linha);
                bw.newLine();
            }
            System.out.println("Produto apagado com sucesso.");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("erro");
        }
        
    }
    
    public void editarProduto(int id, String editado){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            //cria a string linha para salvar a linha e a variavel para saber se o produto ja foi cadastrado
            boolean produtoLocalizado = false;
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(",");
                String[] produto = editado.split(",");
                if(Integer.parseInt(dados[0]) == id){
                    dados[1] = produto[0];
                    dados[2] = produto[1];
                    linha = dados[0] +","+dados[1]+","+dados[2];
                    produtoLocalizado = true;
                }
                conteudo.append(linha).append("\n");
            }
            if(!produtoLocalizado){
                System.out.println("Produto nao localizado");
            }else{
                try (PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_NAME))) {
            printWriter.print(conteudo.toString());
        }
            }
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    

// Este método lê informações de produtos a partir de um arquivo e retorna uma lista de produtos.
public List<Produto> lerProdutos() {
    // Cria uma lista vazia para armazenar os produtos lidos.
    List<Produto> produtos = new ArrayList<>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
        String linha;
        
        // Lê cada linha do arquivo até o final.
        while ((linha = br.readLine()) != null) {
            // Divide a linha em partes usando ',' como delimitador.
            String[] dados = linha.split(",");
            
            // Extrai os dados de cada parte da linha.
            int id = Integer.parseInt(dados[0]);
            String nome = dados[1];
            double preco = Double.parseDouble(dados[2]);
            
            // Cria um novo objeto Produto com os dados extraídos e adiciona à lista.
            produtos.add(new Produto(id, nome, preco));
        }
        
    } catch (IOException e) {
       // e.printStackTrace(); 
        System.out.println("Arquivo nao localizado");// Em caso de erro de leitura, imprime o erro.
    }
    if(produtos.isEmpty()){
        System.out.println("Nenhum produto cadastrado");// se o arquivo estiver vazio, imprime a mensagem.
        return produtos;
    }else{
        // Retorna a lista de produtos lidos do arquivo.
        return produtos;
    }
}
}

