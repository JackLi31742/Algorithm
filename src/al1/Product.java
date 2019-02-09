package al1;

public class Product {

	private String productId;
	private int productRating;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getProductRating() {
		return productRating;
	}
	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productRating=" + productRating + "]";
	}
	public Product(String productId, int productRating) {
		super();
		this.productId = productId;
		this.productRating = productRating;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
