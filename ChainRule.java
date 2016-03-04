public class ChainRule extends QuotientRule{

	/**
		Constructor (this is needed because of how inheritance works)
	*/
	public ChainRule(){

	}

	/**
		Contructor
		@param input String that will later be derived
	*/
	public ChainRule(String input){
		this.setInputString(input);
	}

	/**
		algorithm for finding the derivative using chain rule
		@param input the String that the derivative is calculated from
		@return the answer
	*/
	private String calcChainRule(String input){
		return this.getAnswer();
	}

	/**
		accesses private calcChainRule to calculate answer
		@return the answer
	*/
	public String calcAnswer(){
		return this.getAnswer();
	}
	
}