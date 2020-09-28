package utility;

public class passwordValidation {
        public boolean passValid(String pass1,String pass2) {
			
        	if(pass1.equals(pass2)) {
        		System.out.println("Sign up completed");
        		return true;
        	}
        	else
        	return false;
        	
        }
}
