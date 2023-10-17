import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio();
        Agenda agenda = new Agenda();
        Scanner scan = new Scanner(System.in);
        boolean continuar = true;
        
        System.out.println("Ola! em que posso ajuda-lo?");
        
        try{
            while(continuar){
                Menu.escolha();
                int escolha = scan.nextInt();
                scan.nextLine();
                
                switch(escolha){
                
                case 1:
                    System.out.println("Digite o nome do contato");
                    agenda.setNome(scan.nextLine());
                    
                    System.out.println("Digite o telefone do contato");
                    agenda.setTelefone(scan.nextLine());
                    
                    System.out.println("Digite o email do contato");
                    agenda.setEmail(scan.nextLine());
                    
                    System.out.println(agenda);
                    System.out.println("O item esta correto e pode ser escrito?\nS/N");
                    if(scan.nextLine().equalsIgnoreCase("s")) {
                    	repositorio.salvar(agenda);
                    }
                    
                    break;
                case 2:
                	List<Agenda> listar = repositorio.listar();
                	for(Agenda item :listar) {
                		System.out.println(item.exibir());
                	}
                    break;
                case 3:
                	System.out.println("Informe o Id do item que deseja editar");
                	agenda = repositorio.pegarItem(scan.nextInt());
                	scan.nextLine();
                	
                	System.out.println("Digite o novo nome:");
                	agenda.setNome(scan.nextLine());
                	System.out.println("Digite o novo telefone:");
                	agenda.setTelefone(scan.nextLine());
                	System.out.println("Digite o novo E-mail:");
                	agenda.setEmail(scan.nextLine());
                	
                	System.out.println("Esta correto?\n"+agenda+"\nS/N");
                	if(scan.nextLine().equalsIgnoreCase("s")) {
                    	repositorio.editar(agenda);
                    }
                    break;
                case 4:
                	System.out.println("Informe o Id do item que deseja apagar");
                	agenda = repositorio.pegarItem(scan.nextInt());
                	scan.nextLine();
                	
                	System.out.println("Deseja apagar o item \n"+agenda+"\nS/N?");
                	if(scan.nextLine().equalsIgnoreCase("s")) {
                    	repositorio.deletar(agenda);
                    }
                    break;
                case 5:
                    continuar = false;
                    break;
                }
            }
            
        }catch(Exception e){
        	e.printStackTrace();
            System.out.println("erro");
        }
        
    }
    
}