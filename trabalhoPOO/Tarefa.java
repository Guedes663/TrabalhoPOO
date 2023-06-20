import java.util.Date;

public class Tarefa {
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date dataConclusao;
    private String categoria;
    private boolean concluida; 
    private Tarefa subtarefa;
    
    public Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = new Date();
        this.concluida = false;
    }
    
    public void setSubtarefa(Tarefa subtarefa){
      this.subtarefa = subtarefa;
    }
    
    public Tarefa getSubtarefa(){
      return subtarefa;
    }
    
    public String getTitulo() {
      return titulo;
    }

    public String getDescricao() {
      return descricao;
    }

    public Date getDataCriacao() {
      return dataCriacao;
    }

    public Date getDataConclusao() {
      return dataConclusao;
    }

    public boolean getConcluida() {
      return concluida;
    }
    
    public String getCategoria(){
      return this.categoria;
    }
    
    public void setCategoria(String categoria) {
      this.categoria = categoria;
    }

    public void marcarComoConcluida() {
      this.concluida = true;
      this.dataConclusao = new Date();
    }
    
    @Override
    public String toString() {
      String status = this.concluida ? "Concluida" : "Pendente";
      return String.format("%s;%s;%s;%s;%s", this.titulo, this.descricao, this.categoria, status, this.subtarefa);
    }
}