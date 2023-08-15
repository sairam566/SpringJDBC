package com.tsr;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tsr.bo.CustomerLeaseBo;
import com.tsr.config.JavaConfig;
import com.tsr.dao.VehicleSDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    	VehicleSDAO dao = context.getBean("vehicleSDAO", VehicleSDAO.class);
    	List<CustomerLeaseBo> cus = dao.getCustomerWithLeasedVehicles();
    	cus.forEach(System.out::println);
    }
}
