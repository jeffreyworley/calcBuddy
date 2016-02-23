public class ProductRule extends PowerRule{  

     /**
           constructor (this is needed because of how inheritance works)
     */
     public ProductRule(){

     }

     /**
          constructor
          @param input the input string that will eventually be solved
     */
     public ProductRule(String input){
          this.setInputString(input);
     }
     
     /**
          calculates the derivative using product rule and all other inherited rules
          @param input the String to evaluate
          @return the answer
     */
     private String calcProduct(String input){
     	return this.getAnswer();
     }

     /**
          gets the calculated derivative because calcProduct is private
          @return the answer
     */
     public String calcAnswer(){
     	return this.getAnswer();
     }

}