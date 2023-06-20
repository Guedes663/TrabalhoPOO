import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
    GerenciadorTxt gerenciadorTxt = new GerenciadorTxt();
    
    private String usuario;
    private String senha;
    
    public void verificarUsuario(){
       System.out.println("Digite o nome de usuario:");
       this.usuario = scanner.nextLine();
       System.out.println("Digite sua senha:");
       this.senha = scanner.nextLine();
       gerenciadorTxt.verificarUsuario(usuario, senha, gerenciador);
    }    
    
    public void adicionarNovaTarefa(){
      System.out.println("Digite o título da tarefa:");
      String titulo = scanner.nextLine();
      System.out.println("Digite a descrição da tarefa:");
      String descricao = scanner.nextLine();
      gerenciador.adicionarTarefa(titulo, descricao);
    }
    
    public void marcarTarefaComoConcluida(){
      System.out.println("Digite o número da tarefa a ser marcada como concluída:");
      int numeroTarefa = scanner.nextInt();
      scanner.nextLine();
      gerenciador.marcarTarefaComoConcluida(numeroTarefa);
    }
    
    public void marcarSubtarefaComoConcluida(){
      System.out.println("Digite o numero da tarefa pai para marcar a subtarefa como concluída");
      int indiceSubtarefa = scanner.nextInt();
      scanner.nextLine();
      gerenciador.marcarSubtarefaComoConcluida(indiceSubtarefa);
    }
    
    public void sairSalvar(){
      System.out.println("Salvando e Saindo...");
      gerenciadorTxt.saveFile(usuario, senha, gerenciador.getLista());
    }
    
    public void adicionarCategoria(){
      int contador = 0;
      System.out.println("Selecione a tarefa que deseja adicionar categoria:\n- - - - - - - - - - - - - - - - - - - - - - -");
      for(Tarefa tarefa : gerenciador.getLista()){
         System.out.println( contador + " - " + tarefa + "\n- - - - - - - - - - - - - - - - - - - - - - -" );
         contador++;
      }
      int tarefaSelecionada = scanner.nextInt();
      scanner.nextLine(); // Consumir a nova linha pendente
      System.out.println("Digite a categoria:");
      String categoriaDigitada = scanner.nextLine();
      
      gerenciador.getLista().get(tarefaSelecionada).setCategoria(categoriaDigitada); 
    }
    
    public void adicionarCategoriaSubtarefa(){
      int contador = 0;
      System.out.println("Selecione a tarefa pai que deseja adicionar categoria a sua subtarefa:\n- - - - - - - - - - - - - - - - - - - - - - -");
      for(Tarefa tarefa : gerenciador.getLista()){
         System.out.println( contador + " - " + tarefa + "\n- - - - - - - - - - - - - - - - - - - - - - -" );
         contador++;
      }
      int tarefaSelecionada = scanner.nextInt();
      scanner.nextLine(); // Consumir a nova linha pendente
      System.out.println("Digite a categoria:");
      String categoriaDigitada = scanner.nextLine();
      
      gerenciador.getLista().get(tarefaSelecionada).getSubtarefa().setCategoria(categoriaDigitada); 
    }

    
    public void adicionarSubtarefa(){
      System.out.println("Digite o titulo da subtarefa");
      String titulo = scanner.nextLine();
      
      System.out.println("Digite a descricao da subtarefa");
      String descricao = scanner.nextLine();
      
      System.out.println("Digite o indice da tarefa que voce deseja adicionar a subtarefa");
      int indiceSubtarefa = scanner.nextInt();
      
      ArrayList<Tarefa> tarefas = gerenciador.getLista();
      int contador = 0;
      
      for(Tarefa tarefa: tarefas){
         if( contador == indiceSubtarefa ){
            gerenciador.adicionarSubtarefa(tarefa, titulo, descricao);
         }
         contador ++;
      }
    }
    
    
    public void menu(){
       int opcao = 0;
       
       while (opcao != 12) {
            System.out.println("1 - Adicionar nova tarefa");
            System.out.println("2 - Adicionar subtarefa");
            System.out.println("3 - Adicionar categoria a tarefa");
            System.out.println("4 - Adicionar categoria a subtarefa");
            System.out.println("5 - Exibir lista de tarefas");
            System.out.println("6 - Exibir lista de tarefas por categoria");
            System.out.println("7 - Exibir lista de tarefas filtradas");
            System.out.println("8 - Exibir tarefas pendentes");
            System.out.println("9 - Exibir tarefas concluídas");
            System.out.println("10 - Marcar tarefa como concluída");
            System.out.println("11 - Marcar subtarefa como concluída");
            System.out.println("12 - Sair e Salvar");
            
            try {       
               opcao = scanner.nextInt();
               scanner.nextLine(); // O comando scanner.nextInt() lê um inteiro , o código utiliza um bloco try-catch para tratar exceções.
               
            } catch (Exception e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.nextLine(); // Consumir nova linha deixada pela entrada inválida
                continue; // Voltar para o início do loop
                
            }
            switch (opcao) {
               case 1:
                  adicionarNovaTarefa();
                  break;
                  
               case 2:
                  adicionarSubtarefa();
                  break;
                     
               case 3:
                  adicionarCategoria();
                  break;
                  
               case 4:
                  adicionarCategoriaSubtarefa();
                  break;
                  
               case 5:
                  gerenciador.exibirTarefas();  
                  break;
               
               case 6:
                  gerenciador.exibirTarefasCategoria();
                  break;
                  
               case 7:
                  gerenciador.exibirTarefasFiltradas();
                  break;
                        
               case 8:
                  gerenciador.exibirTarefasPendentes();
                  break;
                  
               case 9:
                  gerenciador.exibirTarefasConcluidas();
                  break;
                  
               case 10:
                  marcarTarefaComoConcluida();
                  break;
                  
               case 11:
                  marcarSubtarefaComoConcluida();
                  break;
                  
               case 12:
                  sairSalvar();
                  break;
                  
               default:
                  System.out.println("Opção inválida. Tente novamente.");
                  break;
            }
       }
       scanner.close();
    }
}