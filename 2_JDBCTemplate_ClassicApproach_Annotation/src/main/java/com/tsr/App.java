package com.tsr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tsr.dao.ProductsDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
    	ProductsDAO dao = applicationContext.getBean("productsDAO", ProductsDAO.class);
    	dao.getAllProducts().forEach(System.out::println);
    }
}
