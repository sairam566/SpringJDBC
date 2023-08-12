package com.tsr;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tsr.bo.ProductBO;
import com.tsr.dao.ProductDAO;
import com.tsr.dao.ProductDAO2;
import com.tsr.dao.ProductDAO3;
import com.tsr.dao.ProductDAO4;
import com.tsr.dao.ProductDAO5;
import com.tsr.dao.ProductDAO6;
import com.tsr.dao.ProductDAO7;
import com.tsr.dao.ProductDAO8;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/tsr/config/application-context.xml");
    	
    	//ProductDAO productDAO = applicationContext.getBean("productDAO", ProductDAO.class);
    	//printProducts(productDAO.selectAll());
    	
    	//ProductDAO2 productDAO2 = applicationContext.getBean("productDAO2", ProductDAO2.class);
    	//printProducts(productDAO2.selectAll());
    	
    	//ProductDAO3 productDAO3 = applicationContext.getBean("productDAO3", ProductDAO3.class);
    	//printProducts(productDAO3.selectAll());
    	
    	//ProductDAO4 productDAO4 = applicationContext.getBean("productDAO4", ProductDAO4.class);
    	//productDAO4.createRegistrationTable();
    	
    	//ProductDAO5 productDAO5 = applicationContext.getBean("productDAO5", ProductDAO5.class);
    	//ProductBO bo = productDAO5.selectById(3);
    	//System.out.println(bo);
    	
    	//ProductDAO6 productDAO6 = applicationContext.getBean("productDAO6", ProductDAO6.class);
    	//printProducts(productDAO6.selectAllProducts());
    	
    	//ProductDAO7 productDAO7 = applicationContext.getBean("productDAO7", ProductDAO7.class);
    	//System.out.println(productDAO7.getPriceById(1));
    	
    	ProductDAO8 productDAO8 = applicationContext.getBean("productDAO8", ProductDAO8.class);
    	System.out.println(productDAO8.getAllproductsCount());
    }
    
    private static void printProducts(List<ProductBO> bos){
    	for (ProductBO productBO : bos) {
			System.out.println(productBO);
		}
    }
}
