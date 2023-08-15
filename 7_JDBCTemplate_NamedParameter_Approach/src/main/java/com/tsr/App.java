package com.tsr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tsr.bo.VehicleBo;
import com.tsr.config.JavaConfig;
import com.tsr.dao.VehicleSDAO;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
    	VehicleSDAO dao = context.getBean("vehicleSDAO", VehicleSDAO.class);
    	//System.out.println("No.Of Honda Vehicle: "+dao.getVehiclesCount("honda"));
    	//System.out.println("Updating Vehicle Details: "+dao.updateVehicle(1, "Gray", 566));
    	VehicleBo bo = new VehicleBo(4, "revuelto", "lamborghini", "Orange", "Petrol", 5000);
    	dao.saveVehicle(bo);
    }
}
