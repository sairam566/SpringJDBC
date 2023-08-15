package com.tsr;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tsr.bo.ProductBO;
import com.tsr.config.JavaConfig;
import com.tsr.dao.ProductDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    	ProductDAO dao = context.getBean("productDAO", ProductDAO.class);
    	List<ProductBO> productsList = dao.getProductsList(2, 3, "");
    	productsList.forEach(System.out::println);
    }
}
