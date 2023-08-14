package com.tsr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tsr.bo.ProductBO;
import com.tsr.config.JavaConfig;
import com.tsr.dao.ProductsDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    	ProductsDAO dao = context.getBean("dao", ProductsDAO.class);
    	//System.out.println("No.Of Products Count : "+dao.getNoOfProducts());
    	//System.out.println("Max Amount in Products : "+dao.maxProductAmount());
    	//System.out.println("Product Name : "+dao.getProductNameById(3));
    	//System.out.println("Product Details : "+dao.getProductById(3));
    	//System.out.println("Product Greater than 1LK : "+dao.getProductsGreaterThanGivenPrice(100000.0));
    	//System.out.println("Product Greater than 80000K : "+dao.getProductsByPrice(80000.0));
//    	ProductBO bo = new ProductBO();
//    	bo.setpId(4);
//    	bo.setpName("IMac");
//    	bo.setpPrice(120400.56);
//    	System.out.println("Product Insert Status : "+dao.saveProduct(bo));
    	//System.out.println("Product Update Status : "+dao.updateProduct("Iphone-15",1));
    	System.out.println("Product Delete Status : "+dao.deleteProduct(4));
    }
}
