package it.polito.tdp.metroparis.model;

public class StazioniConnesse {
	int id_Partenza;
	int id_Arrivo;
	
	public StazioniConnesse(int id_Partenza, int id_Arrivo) {
		super();
		this.id_Partenza = id_Partenza;
		this.id_Arrivo = id_Arrivo;
	}
	public int getId_Partenza() {
		return id_Partenza;
	}
	public void setId_Partenza(int id_Partenza) {
		this.id_Partenza = id_Partenza;
	}
	public int getId_Arrivo() {
		return id_Arrivo;
	}
	public void setId_Arrivo(int id_Arrivo) {
		this.id_Arrivo = id_Arrivo;
	}
	
}
