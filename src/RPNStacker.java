package RPNStacker.src;

import java.io.File; 
import java.io.FileNotFoundException;
import java.rmi.UnexpectedException;
import java.util.Scanner; 
import java.util.Stack;

import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

import java.util.ArrayList;


class Main {
   
    public static void main(String args[]) throws Exception  { 

        try {
            File RPN = new File("./resources/Calc1.stk");
            Scanner reader = new Scanner(RPN);
            
            ArrayList<Token> tokensList = new ArrayList<Token>();
            Token temp = new Token(TokenType.EOF, ""); /*Criando token vazio*/
            boolean valid = true;

            while (reader.hasNext()) { /*Leitura do arquivo de entrada*/
                if (reader.hasNextInt()){
                    temp = new Token(TokenType.NUM, (String.valueOf(reader.nextInt()))); 
                    tokensList.add(temp); /*Inserção na lista (numeros)*/
                } else {
                    String operator = String.valueOf(reader.next().charAt(0));
                    switch(operator) {  /*Associação de tokens*/ 
                        case "+":
                            temp = new Token(TokenType.PLUS, operator);
                            break;
                        case "-":
                            temp = new Token(TokenType.MINUS, operator);
                            break;
                        case "*":
                            temp = new Token(TokenType.STAR, operator);
                            break;
                        case "/":
                            temp = new Token(TokenType.SLASH, operator);
                            break;
                        default:
                            valid = false;
                            throw new Exception("Error: Unexpected Character: " + operator + ".");
                    }
                    if (valid) {
                        tokensList.add(temp); /*Inserção na lista (operandos)*/
                    }
                }
            }
            reader.close();
            if (valid) {
                System.out.println(tokensList.toString()); /*Imprimindo lista de tokens*/
                System.out.print(operation(tokensList)); /*Chamada para função*/
            }
          } catch (FileNotFoundException e) {
            System.out.println("Some error occurred.");
            e.printStackTrace();
          }
}

public static int operation(ArrayList<Token> tokensList){ /*Função para interpretação das expressões*/
   
    Stack<Integer> stack = new Stack<Integer>();
    
    for(Token t : tokensList) {
        if(t.type == TokenType.NUM) {
            stack.push(Integer.parseInt(t.lexeme));
        } else {
            int y = stack.pop();
            int x = stack.pop();

            switch(t.type) {  /*Retorno de resultados*/ 
                case PLUS:
                    stack.push(x+y);
                    break;
                case MINUS:
                    stack.push(x-y);
                    break;
                case STAR:
                    stack.push(x*y);
                    break;
                case SLASH:
                    stack.push(x/y);
                    break;
                default:
                    break; 
            }
        }
    }

    return stack.pop(); /*Como previamente, resultado estará na pilha*/
}

    }