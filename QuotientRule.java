
public class QuotientRule extends ProductRule{ 

    /**
        Contructor (this is needed because of how inheritance works)
    */
    public QuotientRule(){

    }

    /**
        Constructor
        @param input the string to be evaluated
    */
    public QuotientRule(String input){
        this.setInputString(input);
    }

    /**
        Calculates the derivative using Quotient rule and all other inherited rules
        @param input the string to calculate the derivative from
        @return the answer
    */
    private String calcQuotient(String input){
        return this.getAnswer(); 
    }

    /**
        calculates the answer since calcQuotient is private
        @return the answer
    */
    public String calcAnswer() {
        return this.getAnswer();
    }
}