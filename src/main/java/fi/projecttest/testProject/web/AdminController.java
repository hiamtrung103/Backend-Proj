package fi.projecttest.testProject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.projecttest.testProject.domain.ProductAndPrice;
import fi.projecttest.testProject.domain.ProductInfo;
import fi.projecttest.testProject.domain.ProductInfoRepository;
import fi.projecttest.testProject.domain.ProductPrice;
import fi.projecttest.testProject.domain.ProductPriceID;
import fi.projecttest.testProject.domain.ProductPriceRepository;
import fi.projecttest.testProject.domain.SupermarketRepository;

@Controller
public class AdminController {

	@Autowired
	private SupermarketRepository srep;
	
	@Autowired
	private ProductPriceRepository pprep;
	
	@Autowired
	private ProductInfoRepository pirep;
	
	//Defines a manager and respective shop
	private Long sessionAdmin = new Long(1);
	
	private String getUserDetails() {
		   UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().
		   getAuthentication().getPrincipal();
		   String username = userDetails.getUsername();
		   return username;
		} 
	
	// Login to admin
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	//Info about the shop
	@RequestMapping(value="/admin/info")
	public String supermarketInfo(Model model) {
		
		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		model.addAttribute("shopInfo", srep.findById(sessionAdmin).get());
		return "admin/shopAdminInfo";
	}
	
	//Product list of the shop
	@RequestMapping(value="/admin/products")
	public String supermarketProducts(Model model) {
		
		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		model.addAttribute("products", pprep.findBySID(srep.findById(sessionAdmin).get()));
		return "admin/shopAdminProductList";
	}
	
	//RESTful for products of shop
    @RequestMapping(value="/admin/productsrest")
    public @ResponseBody List<ProductPrice> productListRest() {	
        return (List<ProductPrice>) pprep.findBySID(srep.findById(sessionAdmin).get());
    } 
	
	//Add new product to shop's product list
	@RequestMapping(value = "/admin/add")
	public String addProduct(Model model) {
		model.addAttribute("productprice", new ProductAndPrice());
		return "admin/shopAdminAddProduct";
	}
	
	//Save new product with help of ProductAndPrice object
	@RequestMapping(value="admin/save", method = RequestMethod.POST)
	public String saveProduct(ProductAndPrice parsedProduct) {
		
		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		ProductInfo newProductInfo = new ProductInfo(parsedProduct.getName(), parsedProduct.getBrand());
		pirep.save(newProductInfo);
		Long newProductID = pirep.findByPNameAndPBrand(parsedProduct.getName(), parsedProduct.getBrand()).get(0).getpID();
		ProductPriceID newProductPriceID = new ProductPriceID(sessionAdmin, newProductID);
		double newProductPrice = parsedProduct.getPrice();
		ProductPrice newProduct = new ProductPrice(newProductPriceID, srep.findById(sessionAdmin).get(), pirep.findByPNameAndPBrand(parsedProduct.getName(), parsedProduct.getBrand()).get(0), newProductPrice);
		pprep.save(newProduct);
		return "redirect:products";
	}
	
	// Delete product from Productprice entity, but not from ProductInfo, 
	// because ProductInfo is used other supermarkets too!
	@RequestMapping(value="/admin/delete/{id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") Long pid, Model model) {
		
		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		ProductPriceID deleteProductID = new ProductPriceID();
		deleteProductID.setpID(pid);
		deleteProductID.setsID(sessionAdmin);
		pprep.deleteById(deleteProductID);
		return "redirect:../products";
	}
	
	//Edit product
	@RequestMapping(value="/admin/edit/{id}")
	public String editProduct(@PathVariable("id") Long pid, Model model) {

		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		ProductAndPrice editProduct = new ProductAndPrice();
		String eBrand = pirep.findById(pid).get().getpBrand();
		String eName = pirep.findById(pid).get().getpName();
		double ePrice = pprep.findBySIDAndPID(srep.findById(sessionAdmin).get(), pirep.findById(pid).get()).get(0).getPrice();
		editProduct.setId(pid);
		editProduct.setBrand(eBrand);
		editProduct.setName(eName);
		editProduct.setPrice(ePrice);
		
		model.addAttribute("productForEdit", editProduct);
		return "admin/shopAdminEditProduct";
	}
	
	//Save edited product
	@RequestMapping(value="admin/edit/editsave", method = RequestMethod.POST)
	public String saveEditedProduct(ProductAndPrice editedProduct) {
		
		if(!getUserDetails().equals("adminPrisma")) {
			sessionAdmin = (long) 2;
		}
		
		Long pID = editedProduct.getId();
		String editedBrand = editedProduct.getBrand();
		String editedName = editedProduct.getName();
		double editedPrice = editedProduct.getPrice();
		
		//For editing brand and name of product in ProductInfo entity
		ProductInfo editedProductInfo = pirep.findById(pID).get();
		editedProductInfo.setpBrand(editedBrand);
		editedProductInfo.setpName(editedName);
		pirep.save(editedProductInfo);
		
		//For editing price in ProductPrice entity
		ProductPrice editedProductPrice = pprep.findBySIDAndPID(srep.findById(sessionAdmin).get(), pirep.findById(pID).get()).get(0);
		editedProductPrice.setPrice(editedPrice);
		pprep.save(editedProductPrice);
		
		return "redirect:../products";
			
	}
}
