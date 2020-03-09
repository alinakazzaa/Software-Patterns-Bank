package factory;

public class GetUserFactory {
	
	public GetUserFactory() {
		
	}

	public static UserFactory getUserFactory(String userType){ 
		
        if(userType == null){  
         return null;  
        }  
        
      if(userType.equals("Administrator")) { 
             return new AdminFactory();  
       }   
       else {
    	   
    	   if(userType.equals("New Customer")) {

    		   CustomerFactory cus = new CustomerFactory();
    		   cus.setIsNew(true);
    		   return cus;
    		   
    	   }  else {
    		   return new CustomerFactory();
    	   }
    	   
    	   
       }
    	   
	}

}
