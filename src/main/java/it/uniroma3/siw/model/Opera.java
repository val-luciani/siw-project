package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Opera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Column
	private String titolo;
	
	@NotNull
	@Column
	private Integer anno;
	
	@NotNull
	@Column
	private String tecnica;
	
	@NotNull
	@Column
	private String dimensioni;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "autore_id")
	private Autore autore;
	
	
	public Opera(){
		
	}

	public Opera(String titolo, Integer anno, String tecnica, String dimensioni, Autore autore) {
		super();
		this.titolo = titolo;
		this.anno = anno;
		this.tecnica = tecnica;
		this.dimensioni = dimensioni;
		this.autore = autore;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public String getDimensioni() {
		return dimensioni;
	}

	public void setDimensioni(String dimensioni) {
		this.dimensioni = dimensioni;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anno == null) ? 0 : anno.hashCode());
		result = prime * result + ((autore == null) ? 0 : autore.hashCode());
		result = prime * result + ((dimensioni == null) ? 0 : dimensioni.hashCode());
		result = prime * result + ((tecnica == null) ? 0 : tecnica.hashCode());
		result = prime * result + ((titolo == null) ? 0 : titolo.hashCode());
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
		Opera other = (Opera) obj;
		if (anno == null) {
			if (other.anno != null)
				return false;
		} else if (!anno.equals(other.anno))
			return false;
		if (autore == null) {
			if (other.autore != null)
				return false;
		} else if (!autore.equals(other.autore))
			return false;
		if (dimensioni == null) {
			if (other.dimensioni != null)
				return false;
		} else if (!dimensioni.equals(other.dimensioni))
			return false;
		if (tecnica == null) {
			if (other.tecnica != null)
				return false;
		} else if (!tecnica.equals(other.tecnica))
			return false;
		if (titolo == null) {
			if (other.titolo != null)
				return false;
		} else if (!titolo.equals(other.titolo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opera [titolo=" + titolo + ", anno=" + anno + ", tecnica=" + tecnica + ", dimensioni=" + dimensioni
				+ ", autore=" + autore + "]";
	}
	
	
}
