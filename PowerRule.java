/**
    class that handles functions that require power rule to solve the derivative for
*/

public class PowerRule{
	private String inputString;
    private String answer;
    protected final char[] alphabet = {'a', 'b', 'c', 'd', 'e',
    'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
    's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
    'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final char carrot = '^';
    private int variableSubstringValue;
    
    private String coefficientValue = "";
    private String powerValue = "";
    private int powerStart;
    private int coefficientStart;
    private int powerEnd;
    private int coefficientEnd;
    private double coefficientStartingValue;
    private double powerStartingValue;
    private double newCoefficient;
    private double newPower;

    private boolean isNumberFound = false;
    private boolean isCoefficientEnd = false;
    private boolean isPowerEnd = false;



    /**
		Constructor (this is needed because of how inheritance works)
    */
    public PowerRule(){

    }

    /**
		Constructor
		@param input the input string that will be calculated
    */
    public PowerRule(String input){
    	inputString = input;
    }

    /**
		gets inputString (mainly for the inherited classes)
		@param input the string to become inputString
    */
    public void getString(String input){
        inputString = input;
    }

    /**
		Gets the answer string for output porposes
		@return the answer
    */
    public String getAnswer(){
        return answer;
    }

    /**
		gets the inputString for output purposes and calculation purposes in classes that inherit this class
		@return the inputString
    */
    public String getInputString(){
        return inputString;
    }

    /**
		changes answer to the input String
		@param input the String to change answer to
    */
    public void setAnswer(String input){
        answer = input;
    }

    /**
		changes the inputString
		@param input the string to change inputString to
    */
    public void setInputString(String input){
        inputString = input;
    }  

    /**
		algorithm for adding coefficients to the variables of a function
    */
    private void addCoefficient(){

        //scans through inputString
        for(int stringIterator = 0; stringIterator < inputString.length(); stringIterator++){

            //scans through the alphabet
            for(int alphabetIterator = 0; alphabetIterator < alphabet.length; alphabetIterator++){

                //checks if the current char is a letter
                if(inputString.charAt(stringIterator) == alphabet[alphabetIterator]){
                    variableSubstringValue = stringIterator;
                    //is the variable at position 0? else add the 1 elsewhere
                    if(stringIterator == 0){
                        inputString = "1" + inputString;
                        stringIterator++;
                    }else{

                        //runs through every number to see if what is before the variable is a number. 
                        for(int numberIterator = 0; numberIterator < numbers.length; numberIterator++){
                            if(Character.getNumericValue(inputString.charAt(variableSubstringValue - 1)) == numbers[numberIterator]){
                                isNumberFound = true;
                            }

                        }

                        //adds a one in front of a variable if a number is not already found. otherwise just increments i and moves on
                        if(!isNumberFound){
                            inputString = inputString.substring(0, variableSubstringValue) + "1" + inputString.substring(variableSubstringValue, inputString.length());
                            stringIterator++;  
                        }else{
                            stringIterator++;
                        }

                        //reset isNumberFound for next iteration
                        isNumberFound = false;
                        
                    }
                }
            }
        }
        answer = inputString;
    }

    /**
        algorithm for finding the power and the coefficient and multiplying them. then 
    */

    public void multiplyPower(){
        //runs through the input strings
        for(int stringIterator = 0; stringIterator < inputString.length(); stringIterator++){
            //runs through the alphabet
            for (int alphabetIterator = 0; alphabetIterator < alphabet.length; alphabetIterator++){
                //determines if the current position of the string is a variable.
                 if(inputString.charAt(stringIterator) == alphabet[alphabetIterator]){

                    //System.out.println("current char iterator" + inputString.charAt(stringIterator) + "\n");

                    variableSubstringValue = stringIterator;

                    isCoefficientEnd = false;
                    isPowerEnd = false; 
                    coefficientValue = "";
                    powerValue = "";
                    powerStart = variableSubstringValue + 2;
                    coefficientStart = 0;
                    powerEnd = 0;
                    coefficientEnd = variableSubstringValue;
                    coefficientStartingValue = 0;
                    powerStartingValue = 0;
                    newCoefficient = 0;
                    newPower = 0;

                    //System.out.println("length:" + inputString.length());
                    //System.out.println("power start: " + powerStart);
                    //System.out.println("coefficientEnd: " + coefficientEnd);

                    //finds the coefficient
                    for(int coefficientIterator = coefficientEnd - 1; coefficientIterator >= 0; coefficientIterator--){


                        for(int coefficientNumberIterator = 0; coefficientNumberIterator < numbers.length; coefficientNumberIterator++){


                            if(Character.getNumericValue(inputString.charAt(coefficientIterator)) == numbers[coefficientNumberIterator]){

                                coefficientStart = coefficientIterator;

                            }
                        }
                    }

                    coefficientValue = inputString.substring(coefficientStart, coefficientEnd);



                    System.out.println("coefficientValue: " + coefficientValue);

                    //finds power
                    for(int powerIterator = powerStart; powerIterator < inputString.length(); powerIterator++){
                        for(int powerNumberIterator = 0; powerNumberIterator < numbers.length; powerNumberIterator++){
                            if(Character.getNumericValue(inputString.charAt(powerIterator)) == numbers[powerNumberIterator]){
                                powerEnd = powerIterator;
                            }
                        }
                    }

                    powerValue = inputString.substring(powerStart, powerEnd + 1);
                    //finds new coefficient and new power
                    coefficientStartingValue = Double.parseDouble(coefficientValue);
                    powerStartingValue = Double.parseDouble(powerValue);
                    System.out.println("coefficientStartingValue: " + coefficientStartingValue);
                    System.out.println("powerValue:" + powerValue);
                    System.out.println("powerStartingValue: " + powerStartingValue);
                    newPower = powerStartingValue - 1;
                    System.out.println("newPower: " + newPower);
                    System.out.println("powerEnd: " + powerEnd);
                    newCoefficient = coefficientStartingValue * powerStartingValue;

                    inputString = inputString.substring(0, coefficientStart) + newCoefficient + inputString.substring(variableSubstringValue, powerStart-1) + newPower + inputString.substring(powerEnd+1, inputString.length());
                    //System.out.println("input string: " + inputString + "\n");
                    stringIterator = inputString.substring(0, coefficientEnd).length() + 2;
                    //System.out.println("string iterator: " + stringIterator + "\n");

                }
            }
        }
        answer = inputString;
    }

    /**
		accesses private calcPower to calculate answer
		@param input the String that the derivative will be calculated from
		@return the answer
    */
    public String calcAnswer(){
    	this.addCoefficient();
        this.multiplyPower();
        return answer;
    }

    /**
	 	this will identify when there is a trigonomic function in an equation and find the derivative
	 	@param input String to check for trigonomic functions
	 	@return the derived trigonomic function
    */
    public String calcTrigDerive(String input){
    	return answer;
    }


}