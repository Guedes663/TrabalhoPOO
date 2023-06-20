import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeTarefas {
    private ArrayList<Tarefa> tarefas;
    
    public GerenciadorDeTarefas() {
      tarefas = new ArrayList<>();
    }
    
    public void setLista(ArrayList<Tarefa> lista) {
      this.tarefas = lista;
    }
    
    public ArrayList<Tarefa> getLista(){
      return this.tarefas;
    }

    public void adicionarTarefa(String titulo, String descricao) {
      Tarefa tarefa = new Tarefa(titulo, descricao);
      tarefas.add(tarefa);
      System.out.println("Tarefa adicionada: " + tarefa);
    }
    
    public void adicionarSubtarefa(Tarefa tarefaPai, String titulo, String descricao){
      Tarefa subtarefa = new Tarefa(titulo, descricao);
      tarefaPai.setSubtarefa(subtarefa);
      System.out.println("Subtarefa adicionada" + tarefaPai.getSubtarefa());
    }
    
    public void exibirTarefas(){
      int contador = 0;
      
      for(Tarefa tarefa: tarefas){
         System.out.println(contador + " - " + tarefa);
      }
    } 
      
    public void exibirTarefasCategoria(){
      Scanner scanner = new Scanner(System.in);
      
      System.out.println("Digite a categoria desejada");
      String categoriaDigitada = scanner.nextLine(); 
            
      for(Tarefa tarefa : tarefas){
         if( tarefa.getCategoria() != null && tarefa.getCategoria().equalsIgnoreCase(categoriaDigitada) ){
            System.out.println(tarefa);
         }     
      }            
    }
    
    public void exibirTarefasFiltradas(){
      Scanner scanner = new Scanner(System.in);
      System.out.println("Digite uma palavra para buscar em algum item da lista de tarefas");
      String resposta = scanner.nextLine();
      
      for(Tarefa tarefa : tarefas){
         if( tarefa.getTitulo().toLowerCase().contains(resposta.toLowerCase()) || tarefa.getDescricao().toLowerCase().contains(resposta.toLowerCase()) || tarefa.getCategoria().toLowerCase().contains(resposta.toLowerCase()) ){
            System.out.println(tarefa);
         }
      }
    } 
    
    public void exibirTarefasPendentes() {
      System.out.println("Tarefas pendentes:");
      
      for (int i = 0; i < tarefas.size(); i++) {
         Tarefa tarefa = tarefas.get(i);
         if (!tarefa.getConcluida()) {
            System.out.println((i + 1) + ". " + tarefa);
        }
      }
    }
    
    public void exibirTarefasConcluidas() {
      System.out.println("Tarefas concluídas:");
      
      for (int i = 0; i < tarefas.size(); i++) {
         Tarefa tarefa = tarefas.get(i);
         if (tarefa.getConcluida()) {
            System.out.println((i + 1) + ". " + tarefa);
         }
      }
    }
    
    public void marcarSubtarefaComoConcluida(int indiceSubtarefa){
      if (indiceSubtarefa < 0 || indiceSubtarefa >= tarefas.size()) {
         System.out.println("Índice inválido. Tente novamente.");
         return;
         
      } else{
         int contador = 0;
         
         for(Tarefa tarefa: this.tarefas){
            if(contador == indiceSubtarefa){
               tarefa.getSubtarefa().marcarComoConcluida();
            }
            contador++;
         }
      }   
    }
    
    public void marcarTarefaComoConcluida(int indice) {
      if (indice < 0 || indice >= tarefas.size()) {
         System.out.println("Índice inválido. Tente novamente.");
         return;
      } else {
         Tarefa administradorTarefa = tarefas.get(indice);
         administradorTarefa.marcarComoConcluida();      
         System.out.println("A tarefa " + administradorTarefa.getDescricao() + " está concluida!");
      }
      
    }
}