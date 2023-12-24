package Utilities;
/**Custom Exception 
 * super(message) will call the parent class constructor 
 * and create object and perform actions
 * **/
public class FrameworkException extends RuntimeException {

	public FrameworkException (String message)
	{
		super(message);
	}
}