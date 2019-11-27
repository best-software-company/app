package pjIII.simova.pojo;

public class Tarefa {
	private  int idTarefa;
	private String nome;
	private String descricao;
	private String idResponsavel;
	private String idRelator;
	private String estado;
	private String data;
	private int valor;

	public Tarefa(int idTarefa, String nome, String descricao, String idResponsavel, String idRelator, String estado, String data, int valor) {
		this.idTarefa = idTarefa;
		this.nome = nome;
		this.descricao = descricao;
		this.idResponsavel = idResponsavel;
		this.idRelator = idRelator;
		this.estado = estado;
		this.data = data;
		this.valor = valor;
	}

    public Tarefa() {
    }

    public Tarefa(String nome, String descricao, String idResponsavel, String idRelator, String estado, String data, int valor) {
		this.nome = nome;
		this.descricao = descricao;
		this.idResponsavel = idResponsavel;
		this.idRelator = idRelator;
		this.estado = estado;
		this.data = data;
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(String idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getIdRelator() {
		return idRelator;
	}

	public void setIdRelator(String idRelator) {
		this.idRelator = idRelator;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Tarefa{" +
				"idTarefa=" + idTarefa +
				", nome='" + nome + '\'' +
				", descricao='" + descricao + '\'' +
				", idResponsavel='" + idResponsavel + '\'' +
				", idRelator='" + idRelator + '\'' +
				", estado='" + estado + '\'' +
				", data='" + data + '\'' +
				", valor=" + valor +
				'}';
	}
}
