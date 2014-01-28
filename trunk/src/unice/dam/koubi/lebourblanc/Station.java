package unice.dam.koubi.lebourblanc;

public class Station {
	
	private int idStation;
	private String nomStation;
	private String adresse;
	private int disponibilite;
	private double longitude;
	private double latitude;
	private int capaTot;
	private int capaDisp;
	private int plaDisp;
	private int veloDisp;
	
	public Station(int idStation, String nomStation, String adresse, int dispo,
			double longitude, double latitude, int capaTot, int capaDisp,
			int plaDisp, int veloDisp) {
		super();
		this.idStation = idStation;
		this.nomStation = nomStation;
		this.adresse = adresse;
		this.disponibilite = dispo;
		this.longitude = longitude;
		this.latitude = latitude;
		this.capaTot = capaTot;
		this.capaDisp = capaDisp;
		this.plaDisp = plaDisp;
		this.veloDisp = veloDisp;
	}
	
	public int getIdStation() {
		return idStation;
	}
	public void setIdStation(int idStation) {
		this.idStation = idStation;
	}
	public String getNomStation() {
		return nomStation;
	}
	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getCapaTot() {
		return capaTot;
	}
	public void setCapaTot(int capaTot) {
		this.capaTot = capaTot;
	}
	public int getCapaDisp() {
		return capaDisp;
	}
	public void setCapaDisp(int capaDisp) {
		this.capaDisp = capaDisp;
	}
	public int getPlaDisp() {
		return plaDisp;
	}
	public void setPlaDisp(int plaDisp) {
		this.plaDisp = plaDisp;
	}
	public int getVeloDisp() {
		return veloDisp;
	}
	public void setVeloDisp(int veloDisp) {
		this.veloDisp = veloDisp;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}

}
