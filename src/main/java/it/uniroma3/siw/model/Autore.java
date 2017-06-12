package it.uniroma3.siw.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Autore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private String nome;
	
	@NotNull
	@Column
	private String cognome;
	
	@NotNull
	@Column
	private String nazionalita;
	
	//@NotNull
	@Column
	@Temporal(value=TemporalType.DATE)
	private Date dataDiNascita;
	
	//@NotNull
	@Column
	@Temporal(value=TemporalType.DATE)
	private Date dataDiMorte;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autore")
	private List<Opera> opere;
	
	public Autore(){
		
	}

	public Autore(String nome, String cognome, String nazionalita, Date dataDiNascita, Date dataDiMorte) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.dataDiNascita = dataDiNascita;
		this.dataDiMorte = dataDiMorte;
		this.opere = new ArrayList<Opera>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Date getDataDiMorte() {
		return dataDiMorte;
	}

	public void setDataDiMorte(Date dataDiMorte) {
		this.dataDiMorte = dataDiMorte;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataDiMorte == null) ? 0 : dataDiMorte.hashCode());
		result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
		result = prime * result + ((nazionalita == null) ? 0 : nazionalita.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((opere == null) ? 0 : opere.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataDiMorte == null) {
			if (other.dataDiMorte != null)
				return false;
		} else if (!dataDiMorte.equals(other.dataDiMorte))
			return false;
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null)
				return false;
		} else if (!dataDiNascita.equals(other.dataDiNascita))
			return false;
		if (nazionalita == null) {
			if (other.nazionalita != null)
				return false;
		} else if (!nazionalita.equals(other.nazionalita))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (opere == null) {
			if (other.opere != null)
				return false;
		} else if (!opere.equals(other.opere))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  nome + " " + cognome;
	}
	
	
	
	
}
