import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoRepository repository = new ProdutoRepository();
        Scanner scan = new Scanner(System.in);
        Produto novoProduto;
        boolean continuar = true;
        
        while(continuar){
            System.out.println("---------------------------");
            System.out.println("Digite a opcao dejesada");
            System.out.println("1- Cadastar produto");
            System.out.println("2- Listar Produtos");
            System.out.println("3- Editar produto");
            System.out.println("4- Deletar produto");
            System.out.println("5- Sair");
            System.out.println("---------------------------");
            
            int escolha = scan.nextInt();
            scan.nextLine();
            switch(escolha){
                case 1:
                       novoProduto = new Produto();
                       System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                       System.out.println("Digite o nome do produto");
                       novoProduto.setNome(scan.nextLine());
                        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                       System.out.println("Digite o valor do produto");
                       novoProduto.setPreco(scan.nextDouble());
                       scan.nextLine();
                       repository.salvarProduto(novoProduto);
                       break;
                case 2:
                       List<Produto> produtos = repository.lerProdutos();
                       for (Produto produto : produtos) {
                           System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                           System.out.println(produto.getId() + ": " + produto.getNome() + " - $" + produto.getPreco());
                       }
                       break;
                case 3:
                        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                       System.out.println("informe o id do produto que deseja editar");
                       int id = scan.nextInt();
                       scan.nextLine();
                        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                       System.out.println("Digite o novo nome do produto");
                       String produtoEditado = scan.nextLine();
                        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                       System.out.println("Digite o novo nome do produto");
                       produtoEditado += "," + scan.nextLine();
                       repository.editarProduto(id, produtoEditado);
                        break;
                case 4: 
                        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°");
                      System.out.println("informe o id do produto que sera removido");
                      id = scan.nextInt();
                      repository.apagarProduto(id);
                      break;
                case 5:
                      continuar = false;
                      break;
            }
        }
        
    }
}
