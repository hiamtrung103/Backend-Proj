package fi.projecttest.testProject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.projecttest.testProject.domain.ProductInfo;
import fi.projecttest.testProject.domain.ProductInfoRepository;
import fi.projecttest.testProject.domain.ProductPrice;
import fi.projecttest.testProject.domain.ProductPriceRepository;
import fi.projecttest.testProject.domain.ShoppingList;
import fi.projecttest.testProject.domain.Supermarket;
import fi.projecttest.testProject.domain.SupermarketRepository;

@Controller
public class UserController {
	@Autowired
	private SupermarketRepository suprep;
	
	@Autowired
	private ProductPriceRepository pprep;
	
	@Autowired
	private ProductInfoRepository pirep;
	
	private List<ShoppingList> forShoppingList = new ArrayList<ShoppingList>();
	
	//Shopping list page
	@RequestMapping(value="/shoppinglist")
    public String shoppingList(Model model) {
		model.addAttribute("respond", forShoppingList);
        return "shoppinglist";
    }
	
	 // Index page
    @RequestMapping(value="/index")
    public String supermarketList() {	
        return "index";
    }
    @RequestMapping(value="/")
    public String supermarketList2() {	
        return "index";
    }
	
	 // Show all supermarkets
    @RequestMapping(value="/supermarkets")
    public String supermarketList(Model model) {	
        model.addAttribute("supermarket", suprep.findAll());
        return "supermarkets";
    }
    
    // Page with products
    @RequestMapping(value="/products")
    public String productList(Model model) {
    	model.addAttribute("product", pprep.findAll());
    	return "products";
    }
    
    //RESTful for products
    @RequestMapping(value="/productsrest")
    public @ResponseBody List<ProductPrice> productListRest() {	
        return (List<ProductPrice>) pprep.findAll();
    } 
    
    //Adding products to shopping list
    @RequestMapping("/add")
    public String addProductToShList(@RequestParam(name="pname") Long pid, @RequestParam(name="sname") Long sid, Model model) {
    	//String forReply = pid.toString() + " " + sid.toString();
    	//model.addAttribute("respond", "Hello " + forReply);
    	
    	ShoppingList itemToList;
    	
    	Supermarket sID = suprep.findById(sid).get();
    	ProductInfo pID = pirep.findById(pid).get();
    	ProductPrice objectWithData = pprep.findBySIDAndPID(sID, pID).get(0);
    	
    	String itemName = objectWithData.getpID().getpName();
    	String itemBrand = objectWithData.getpID().getpBrand();
    	double itemPrice = objectWithData.getPrice();
    	String itemShop = objectWithData.getsID().getsName();
    	
    	itemToList = new ShoppingList(itemName, itemBrand, itemShop, itemPrice);
    	
    	forShoppingList.add(itemToList);
    	
    	model.addAttribute("respond", forShoppingList);
    	
    	return "redirect:products";
    }
    
    //Clear shopping list
    @RequestMapping("/clear")
    public String clearShoppingList(Model model) {
    	forShoppingList.removeAll(forShoppingList);
    	model.addAttribute("clear", "Shopping list is cleared");
    	return "redirect:shoppinglist";
    }
    
    //remove item from shopping list
    @RequestMapping("/remove")
    public String reoveItemFromShoppingList(@RequestParam(name="iname") String in, @RequestParam(name="ishop") String is, @RequestParam(name="ibrand") String ib, @RequestParam(name="iprice") String ip, Model model) {
    	
    	int answer = 0;
    	
    	for (int i = 0; i < forShoppingList.size(); i++) {
    		if (forShoppingList.get(i).getName().equals(in)) {
    			if (forShoppingList.get(i).getBrand().equals(ib)) {
    				if (forShoppingList.get(i).getSupermarket().equals(is)) {
    					answer = i;
    				}
    			}
    		}
    	}
    	
    	forShoppingList.remove(answer);
    	model.addAttribute("respond", forShoppingList);
    	return "redirect:shoppinglist";
    }
}
