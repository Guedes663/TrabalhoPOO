import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;

public class GerenciadorTxt {
   
  public void verificarUsuario(String usuario, String senha, GerenciadorDeTarefas listaMenu){
   String diretorioAtual = System.getProperty("user.dir");
   String caminhoDoUsuario = diretorioAtual +"\\dadosPOO\\" + usuario + ".txt";
   File arquivo = new File(caminhoDoUsuario);

   try {
      if (arquivo.isFile()) {
         ArrayList<Tarefa> lista = readTxt(usuario);
         boolean senhaEncontrada = validarSenha(senha, usuario);
         if(lista != null){
            if(senhaEncontrada){
               System.out.println("Usuario encontrado e senha correta!");
               listaMenu.setLista(lista);
            } else {
               System.out.println("Senha incorreta!");
               System.exit(0);
            }
         }
      
      }else{
         arquivo.createNewFile();
         System.out.println("Usuario criado");
         
      }        
   } catch(IOException e) {
      System.out.println("Erro ao criar arquivo: " + e.getMessage()); 
   }
  }
  
  public boolean validarSenha(String senha, String usuario) {
      String diretorioAtual = System.getProperty("user.dir");
      String caminhoDoArquivoTxt = diretorioAtual + "\\dadosPOO\\" + usuario + ".txt";
      
      try{
         FileReader abrirArquivoTxt = new FileReader(caminhoDoArquivoTxt);
         BufferedReader lerArquivoTxt = new BufferedReader(abrirArquivoTxt);
         String linha;
         boolean encontrada = false;
         String palavraProcurada = senha; 
         
         while ((linha = lerArquivoTxt.readLine()) != null) {
            if (linha.contains(palavraProcurada)) {
               encontrada = true;
               break;
            }
         }
         if(encontrada == true){
            return true;
            
         } else {
            return false;
         }
         
      } catch (IOException e) {
         System.out.println("Erro ao ler o arquivo: " + e.getMessage());
      } 
      return false;        
  }
  
  public void saveFile(String usuario, String senha, ArrayList<Tarefa> lista){
      try {
         String diretorioAtual = System.getProperty("user.dir");
         String caminhoDoUsuario = diretorioAtual + "//dadosPOO//" + usuario + ".txt";
         File file = new File(caminhoDoUsuario);
         PrintWriter pw = new PrintWriter(new FileWriter(file));
     
         for(int i = 0; i < lista.size(); i++){
            pw.println(lista.get(i));
         }
         
         pw.println(senha);
         pw.close();
      }catch (IOException e) {
         System.out.println("Erro ao salvar arquivo: " + e.getMessage()); 
         e.printStackTrace();
      }
  }
  
   public ArrayList<Tarefa> readTxt(String usuario) {
       ArrayList<Tarefa> lista = new ArrayList<>();
       try {
           String diretorioAtual = System.getProperty("user.dir");
           String caminhoDoUsuario = diretorioAtual + "\\dadosPOO\\" + usuario + ".txt";
           BufferedReader reader = new BufferedReader(new FileReader(caminhoDoUsuario));
           String linha;
           
           while ((linha = reader.readLine()) != null) {
               if (!linha.isEmpty()) {
                   String[] campos = linha.split(";");
                   
                  if(campos.length > 1){  
                     Tarefa tarefa = new Tarefa(campos[0], campos[1]);
                     tarefa.setCategoria(campos[2]);
                     
                     if(campos.length > 5){
                        Tarefa subtarefa = new Tarefa(campos[4], campos[5]);
                        subtarefa.setCategoria(campos[6]);
                        String subtarefaStatus = campos[7];
                        
                        if(subtarefaStatus.equals("Concluida")){
                           subtarefa.marcarComoConcluida();
                        }
                        tarefa.setSubtarefa(subtarefa);
                     }
                     
                     String concluidaStr = campos[3];
                     
                     if (concluidaStr.trim().equals("Concluida")) {
                        tarefa.marcarComoConcluida();
                     }
                   lista.add(tarefa);
               }
           }
         }
         reader.close();     
                 
       } catch (IOException e) {
           System.out.println("Erro ao ler o arquivo: " + e.getMessage());
       }
       return lista;
   }
             
}