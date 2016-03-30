/**
    class that handles functions that require power rule to solve the derivative for
*/
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Stack;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.text.DecimalFormat;


public class PowerRule{
	private String inputString;
    private String processedString;
    private String answer = "";
    private String coef;
    private String expo;
    private int intCoefVal;
    private int intExpoVal;
    private double dubCoefVal;
    private double dubExpoVal;
    private int variableSubstringValue;
    private final static DecimalFormat df2 = new DecimalFormat(".###");

    private boolean isNumberFound = false;
    private boolean varFound = false;
    private boolean carrotFound = false;

    private Stack<String> functions = new Stack<String>();
    private Stack<String> operators = new Stack<String>();
    private String regExPattern = "(((\\-\\(|\\()?(\\-?\\d+\\.\\d+|\\-?\\d+)\\)?((\\/|\\*|\\+|\\-)(\\-?\\d+\\.\\d+|\\-?\\d+)\\)?)?)?(([a-z]|[A-Z])(\\^(\\-\\(|\\()?(\\-?\\d+\\.\\d+|\\-?\\d+)\\)?((\\/|\\*|\\+|\\-)(\\-?\\d+\\.\\d+|\\-?\\d+)\\)?)?)?))";
    private Pattern powerPattern;
    private Matcher powerMatcher;

    private ScriptEngineManager evaluatorManager;
    private ScriptEngine evaluator;

    private ArrayList<FunctionProperties> evaluatedFunction = new ArrayList<FunctionProperties>();

    /**
		Constructor
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
        returns the ArrayList of FunctionProperties
        @return the arraylist of FunctionProperties
    */
    public ArrayList<FunctionProperties> getFunctionPorperties(){
        return evaluatedFunction;
    }

    /**
		algorithm for adding coefficients to the variables of a function that dont already have a coefficient. Simplifies later processing
    */
    private void addCoefficient(){
         //scans through inputString
        for(int stringIterator = 0; stringIterator < inputString.length(); stringIterator++){

                //checks if the current char is a letter
                if(Character.toString(inputString.charAt(stringIterator)).matches("[a-z]|[A-Z]")){

                    variableSubstringValue = stringIterator;

                    //is the variable at position 0? else add the 1 elsewhere
                    if(stringIterator == 0){
                        inputString = "1" + inputString;
                        stringIterator++;
                    }else{

                    //checks to see if the variable already has a number infront of it
                    if(Character.toString(inputString.charAt(variableSubstringValue - 1)).matches("\\d|\\)")){
                        isNumberFound = true;
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
        processedString = inputString;
    }

    /**
        parses the string input from the user
    */
    private void regexForTheWin(){

        powerPattern = Pattern.compile(regExPattern);
        powerMatcher = powerPattern.matcher(processedString);

        //puts the functions in the input string into the functions stack
        while(powerMatcher.find()){
            functions.push(powerMatcher.group(0));
            System.out.println("Stack input: " + powerMatcher.group(0));
        }

        //takes the functions out of the processedString string for later processing
        for(String str : functions){
            processedString = processedString.replaceFirst(regExPattern, "");
        }
        
        //checks to see if something went wrong and puts all the operators in their own stack for putting the whole string back together
        for(char c : processedString.toCharArray()){
            System.out.println(c);
            if(Character.toString(c).matches("\\+|\\-")){
                operators.push(Character.toString(c));
            }

            if(!Character.toString(c).matches("\\+|\\-|\\/|\\*|\\(|\\)|\\s")){
                answer = "an error has occured";
            } else if(Character.toString(c).matches("\\/|\\*")){
                answer = "you need quotient or product rule to solve this";
            } else if(Character.toString(c).matches("\\(|\\)")){
                answer = "error: unmatched parenthesis";
            }
        }

    }

    /**
        finds the coefficient of a function
        @param str the function to searched for the coefficient
        @return the coefficient as a String
    */
    private String findCoefficient(String str){

        varFound = false;
        String coefString = "";

        for(int i = 0; !varFound && i < str.length(); i++){
                varFound = str.substring(i, i+1).matches("[a-z]|[A-Z]")? true:false;
                coefString = str.substring(0, i);
        }
        return coefString;
    }

    /**
        finds the ecponent of a function
        @param str the function that will be searched for the exponent
        @return the exponent as a String
    */
    private String findExponent(String str){

        carrotFound = false;
        String expoString = "";

        for(int i = 0; i < str.length() && !carrotFound; i++){
            carrotFound = str.substring(i, i+1).equals("^")? true : false;
            expoString = str.substring(i+1);
        }
        return expoString;
    }

    /**
        algorithm for processing the coefficient and exponent
    */
    private void process() throws Exception{
        evaluatorManager = new ScriptEngineManager();
        evaluator = evaluatorManager.getEngineByName("JavaScript");

        if(answer.equals("an error has occured") || answer.equals("you need quotient or product rule to solve this") || answer.equals("error: unmatched parenthesis")){
           //do nothing
        } else {
            while(!functions.empty()){
                evaluatedFunction.add(new FunctionProperties());
                String str1 = functions.pop();

                evaluatedFunction.get(evaluatedFunction.size()-1).setFunction(str1);

                coef = findCoefficient(str1);
                expo = findExponent(str1);

                evaluatedFunction.get(evaluatedFunction.size()-1).setCoef(coef);
                evaluatedFunction.get(evaluatedFunction.size()-1).setExpo(expo);

                str1 = str1.replaceFirst(Pattern.quote(coef), "");
                str1 = str1.replaceFirst(Pattern.quote(expo), "");

                coef = evaluator.eval(coef).toString();
                expo = evaluator.eval(expo).toString();

                dubCoefVal = Double.parseDouble(coef);
                dubExpoVal = Double.parseDouble(expo);

                dubCoefVal = dubCoefVal * dubExpoVal;
                dubExpoVal = dubExpoVal - 1;

                if(dubCoefVal % 1 == 0){
                    intCoefVal = (int) dubCoefVal;
                    coef = Integer.toString(intCoefVal);
                } else{
                    coef = df2.format(dubCoefVal); //Double.toString(dubCoefVal)
                }

                if(dubExpoVal % 1 == 0){
                    intExpoVal = (int) dubExpoVal;
                    expo = Integer.toString(intExpoVal);
                } else{
                    expo = df2.format(dubExpoVal); //Double.toString(dubExpoVal)
                }

                if(expo.equals("0")){
                    str1 = coef;
                    evaluatedFunction.get(evaluatedFunction.size()-1).setDerivative(str1);
                    answer = str1 + answer;
                } else{
                    str1 = coef + str1 + expo;
                    evaluatedFunction.get(evaluatedFunction.size()-1).setDerivative(str1);
                    answer = str1 + answer;
                }

                if(!operators.empty()){
                    answer = " " + operators.pop() + " " + answer;
                }
                
            }
        }
    }

    /**
		calculates the answer
		@return the answer
    */
    public String calcAnswer() throws Exception{
    	this.addCoefficient();
        this.regexForTheWin();
        this.process();
        return answer;
    }

}