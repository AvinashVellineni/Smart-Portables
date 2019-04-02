import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productName;
	private String userName;
	private String productType;
	private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerpin;
	private String price;
	private String retailercity;
	private String retailername;
	private String userage;
	private String usergender;
	private String useroccupation;
	private String productonsale;
	private String manufacturerrebate;
	private String retailerstate;
//userid,productname,producttype,productprice,retailername,manufacturername,reviewrating,userage,usergender,useroccupation,productonsale,manufacturerrebate,zipcode,retailercity,retailerstate,reviewdate,reviewtext
	public Review (String userName,String productName,String productType,String price,String retailername,String productMaker,String reviewRating,String userage,String usergender,String useroccupation,String productonsale,String manufacturerrebate,String retailerpin,String retailercity,String retailerstate,String reviewDate,String reviewText){
		this.productName=productName;
		this.userName=userName;
		this.productType=productType;
		this.productMaker=productMaker;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.retailerpin=retailerpin;
		this.price= price;
		this.retailercity= retailercity;
		this.retailername=retailername;
		this.userage=userage;
		this.usergender=usergender;
		this.useroccupation=useroccupation;
		this.productonsale=productonsale;
		this.manufacturerrebate=manufacturerrebate;
		this.retailerstate=retailerstate;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
       this.productName = productName;
       this.retailerpin = retailerpin;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }

	public String getProductName() {
		return productName;
	}
	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
    
		public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}
			public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}
	
			public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	//adding new

	public String getretailername() {
		return retailername;
	}

	public void setretailername(String retailername) {
		this.retailername = retailername;
	}

	public String getuserage() {
		return userage;
	}

	public void setuserage(String userage) {
		this.userage = userage;
	}

	public String getusergender() {
		return usergender;
	}

	public void setusergender(String usergender) {
		this.usergender = usergender;
	}

	public String getuseroccupation() {
		return useroccupation;
	}

	public void setuseroccupation(String useroccupation) {
		this.useroccupation = useroccupation;
	}
	public String getproductonsale() {
		return productonsale;
	}

	public void setproductonsale(String productonsale) {
		this.productonsale = productonsale;
	}
	public String getmanufacturerrebate() {
		return manufacturerrebate;
	}

	public void setmanufacturerrebate(String manufacturerrebate) {
		this.manufacturerrebate = manufacturerrebate;
	}
	public String getretailerstate() {
		return retailerstate;
	}

	public void setretailerstate(String retailerstate) {
		this.retailerstate = retailerstate;
	}

}
