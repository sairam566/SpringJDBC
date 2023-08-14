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
    	ProductBO bo = new ProductBO();
    	bo.setpId(4);
    	bo.setpName("Imac Studio Display");
    	bo.setpPrice(500000.0);
    	System.out.println(dao.saveProduct(bo));
    }
}
