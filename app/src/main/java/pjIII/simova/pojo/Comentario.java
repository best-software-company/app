package pjIII.simova.pojo;

import java.sql.Blob;
import java.util.Date;

public class Comentario {

	private int idComentario;
	private String texto;
	private Blob midia;
	private String data;
	private int idTarefa;
	private String idUsuario;

	public Comentario(int idComentario, String texto, Blob midia, String data, int idTarefa, String idUsuario) {
		this.idComentario = idComentario;
		this.texto = texto;
		this.midia = midia;
		this.data = data;
		this.idTarefa = idTarefa;
		this.idUsuario = idUsuario;
	}

	public Comentario(String texto, Blob midia, String data, int idTarefa, String idUsuario) {
		this.texto = texto;
		this.midia = midia;
		this.data = data;
		this.idTarefa = idTarefa;
		this.idUsuario = idUsuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Blob getMidia() {
		return midia;
	}

	public void setMidia(Blob midia) {
		this.midia = midia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "Comentario{" +
				"texto='" + texto + '\'' +
				", midia=" + midia +
				", data='" + data + '\'' +
				", idTarefa=" + idTarefa +
				", idUsuario='" + idUsuario + '\'' +
				'}';
	}
}
