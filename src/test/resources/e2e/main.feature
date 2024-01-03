Feature: Add Product to Cart from Main Page

	Scenario Outline: User can add product to cart from main page
	  Given user at homepage
	  When user add product <product_at_number> to cart
	  Then the product should be in cart
	  And verify the product price in cart is correct
	  Examples:
	    | product_at_number |
	    | 1 |
	    | 2 |