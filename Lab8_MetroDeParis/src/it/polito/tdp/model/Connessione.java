package it.polito.tdp.model;

public class Connessione {
	
	private int codice;
	private int codiceLinea;
	private int codiceFermataPartenza;
	private int codiceFermataArrivo;
	
	public Connessione(int codice) {
		this.codice = codice;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getCodiceLinea() {
		return codiceLinea;
	}

	public void setCodiceLinea(int codiceLinea) {
		this.codiceLinea = codiceLinea;
	}

	public int getCodiceFermataPartenza() {
		return codiceFermataPartenza;
	}

	public void setCodiceFermataPartenza(int codiceFermataPartenza) {
		this.codiceFermataPartenza = codiceFermataPartenza;
	}

	public int getCodiceFermataArrivo() {
		return codiceFermataArrivo;
	}

	public void setCodiceFermataArrivo(int codiceFermataArrivo) {
		this.codiceFermataArrivo = codiceFermataArrivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Connessione other = (Connessione) obj;
		if (codice != other.codice)
			return false;
		return true;
	}
	
	
	
	

}
