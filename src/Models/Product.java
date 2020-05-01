package Models;

public class Product {

	String TITL, DSCP, TITL_TYPE, FRMT_TYPE, Genre, Director, Band, Organiser, Manufacturer, Model;
	int RELS_YEAR, Quantity;
	
	public Product(String tITL, String dSCP, String tITL_TYPE, int rELS_YEAR, int quantity) {
		super();
		TITL = tITL;
		DSCP = dSCP;
		TITL_TYPE = tITL_TYPE;
		RELS_YEAR = rELS_YEAR;
		Quantity = quantity;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getOrganiser() {
		return Organiser;
	}

	public void setOrganiser(String organiser) {
		Organiser = organiser;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public String getTITL() {
		return TITL;
	}

	public void setTITL(String tITL) {
		TITL = tITL;
	}

	public String getDSCP() {
		return DSCP;
	}

	public void setDSCP(String dSCP) {
		DSCP = dSCP;
	}

	public String getTITL_TYPE() {
		return TITL_TYPE;
	}

	public void setTITL_TYPE(String tITL_TYPE) {
		TITL_TYPE = tITL_TYPE;
	}

	public String getFRMT_TYPE() {
		return FRMT_TYPE;
	}

	public void setFRMT_TYPE(String fRMT_TYPE) {
		FRMT_TYPE = fRMT_TYPE;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getDirector() {
		return Director;
	}

	public void setDirector(String director) {
		Director = director;
	}

	public String getBand() {
		return Band;
	}

	public void setBand(String band) {
		Band = band;
	}

	public int getRELS_YEAR() {
		return RELS_YEAR;
	}

	public void setRELS_YEAR(int rELS_YEAR) {
		RELS_YEAR = rELS_YEAR;
	}

	@Override
	public String toString() {
		return "Product [TITL=" + TITL + ", DSCP=" + DSCP + ", TITL_TYPE=" + TITL_TYPE + ", FRMT_TYPE=" + FRMT_TYPE
				+ ", Genre=" + Genre + ", Director=" + Director + ", Band=" + Band + ", Organiser=" + Organiser
				+ ", Manufacturer=" + Manufacturer + ", Model=" + Model + ", RELS_YEAR=" + RELS_YEAR + "]";
	}

}
