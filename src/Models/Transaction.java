package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	int ProductID,CustomerID,StaffID;
	String Status,IssueDate, ReturnDate;
	
	public Transaction() {
		Status = "Issued";
		IssueDate = formatter.format(new Date());
		ReturnDate = null;
	}

	public int getProductID() {
		return ProductID;
	}

	public void setProductID(int productID) {
		ProductID = productID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getStaffID() {
		return StaffID;
	}

	public void setStaffID(int staffID) {
		StaffID = staffID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	
	
	public String getIssueDate() {
		return IssueDate;
	}

	public void setIssueDate(String issueDate) {
		IssueDate = issueDate;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Transaction [ProductID=" + ProductID + ", CustomerID=" + CustomerID + ", StaffID=" + StaffID
				+ ", Status=" + Status + ", IssueDate=" + IssueDate + ", ReturnDate=" + ReturnDate + "]";
	}

	
}
