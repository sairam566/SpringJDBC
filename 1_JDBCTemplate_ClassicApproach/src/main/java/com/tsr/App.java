package com.tsr;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tsr.bo.ProductBO;
import com.tsr.dao.ProductDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/tsr/config/application-context.xml");
    	
    	ProductDAO productDAO = applicationContext.getBean("productDAO", ProductDAO.class);
    	
    	List<ProductBO> selectAll = productDAO.selectAll();
    	
    	for (ProductBO productBO : selectAll) {
			System.out.println(productBO);
		}
    }
}
