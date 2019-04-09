package com.Application.Pages.eBay;

public interface ProductImpl{

	public boolean searchProduct(String searchText);
	public boolean selectingSearchResults();
	public boolean verifyProductPage();
	public boolean verifyCheckoutPage();
	public boolean sortProducts(String sortingType);
	public boolean calibratePriceFilter(String minPrice, String maxPrice);
}
