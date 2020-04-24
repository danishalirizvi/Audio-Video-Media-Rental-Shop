package Models;

public class Customer {
	
	int ID;
	String NME, EMAIL;
	int PHNE, ACC_CRD, LYLTY_PNTS;
	String ACCS_LVL, SBSC;
		
	public Customer(int lYLTY_PNTS) {
		LYLTY_PNTS = lYLTY_PNTS;
	}

	public Customer(int iD, String nME, String eMAIL, int pHNE, int aCC_CRD, int lYLTY_PNTS, String aCCS_LVL,String sBSC) {
		ID = iD;
		NME = nME;
		EMAIL = eMAIL;
		PHNE = pHNE;
		ACC_CRD = aCC_CRD;
		LYLTY_PNTS = lYLTY_PNTS;
		ACCS_LVL = aCCS_LVL;
		SBSC = sBSC;
	}
	
	public Customer(String nME, String eMAIL, int pHNE, int aCC_CRD, int lYLTY_PNTS, String aCCS_LVL, String sBSC) {
		NME = nME;
		EMAIL = eMAIL;
		PHNE = pHNE;
		ACC_CRD = aCC_CRD;
		LYLTY_PNTS = lYLTY_PNTS;
		ACCS_LVL = aCCS_LVL;
		SBSC = sBSC;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public String getNME() {
		return NME;
	}
	public void setNME(String nME) {
		NME = nME;
	}

	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public int getPHNE() {
		return PHNE;
	}
	public void setPHNE(int pHNE) {
		PHNE = pHNE;
	}

	public int getACC_CRD() {
		return ACC_CRD;
	}
	public void setACC_CRD(int aCC_CRD) {
		ACC_CRD = aCC_CRD;
	}

	public int getLYLTY_PNTS() {
		return LYLTY_PNTS;
	}
	public void setLYLTY_PNTS(int lYLTY_PNTS) {
		LYLTY_PNTS = lYLTY_PNTS;
	}

	public String getACCS_LVL() {
		return ACCS_LVL;
	}
	public void setACCS_LVL(String aCCS_LVL) {
		ACCS_LVL = aCCS_LVL;
	}

	public String getSBSC() {
		return SBSC;
	}
	public void setSBSC(String sBSC) {
		SBSC = sBSC;
	}


	
	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", NME=" + NME + ", EMAIL=" + EMAIL + ", PHNE=" + PHNE + ", ACC_CRD=" + ACC_CRD
				+ ", LYLTY_PNTS=" + LYLTY_PNTS + ", ACCS_LVL=" + ACCS_LVL + ", SBSC=" + SBSC + "]";
	}
	
	
}
