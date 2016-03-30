/**
	data type for the output of the details on how the equation was solved
*/
public class FunctionProperties{

	private String function;
	private String coef;
	private String expo;
	private String derivative;

	/**
		constructor
	*/
	public FunctionProperties(){

	}

	/**
		constructor
		@param function the function
		@param coef the coefficient of the function
		@param expo the exponent of the function
		@param derivative the calculated derivative of the function
	*/
	public FunctionProperties(String function, String coef, String expo, String derivative){
		this.function = function;
		this.coef = coef;
		this.expo = expo;
		this.derivative = derivative;
	}

	/**
		returns the function
		@return the function
	*/
	public String getFunction(){
		return function;
	}

	/**
		returns the coefficient
		@return the coefficient
	*/
	public String getCoef(){
		return coef;
	}

	/**
		returns the exponent
		@return the exponent
	*/
	public String getExpo(){
		return expo;
	}

	/**
		returns the calculated derivative
		@return the calculated derivative
	*/
	public String getDerivative(){
		return derivative;
	}

	/**
		passes in and sets the String of the function
		@param function the String of the function
	*/
	public void setFunction(String function){
		this.function = function;
	}

	/**
		passes in and sets the String of the coefficient
		@param coef the String of the coefficient
	*/
	public void setCoef(String coef){
		this.coef = coef;
	}

	/**
		passes in and sets the String of the exponent
		@param the String of the exponent
	*/
	public void setExpo(String expo){
		this.expo = expo;
	}

	/**
		passes in and sets the String of the Derivative
		@param the String of the derivative
	*/
	public void setDerivative(String derivative){
		this.derivative = derivative;
	}
}