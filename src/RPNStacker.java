package RPNStacker.src;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Stack;


class Main {
   
    public static void main(String args[])  { 
        Stack<Integer> stack = new Stack<Integer>();
                
        try {
            File RPN = new File("./resources/Calc1.stk");
            Scanner reader = new Scanner(RPN);
            
            while (reader.hasNextLine()) { /*Leitura do arquivo de entrada*/
                if (reader.hasNextInt()){
                    int number = reader.nextInt();
                    stack.push(number); /*Inserção na pilha*/
                } else {
                    char operator = reader.next().charAt(0);
                    stack.push(operation(stack, operator)); /*Execução das operações*/
                }
            }
            reader.close();
          } catch (FileNotFoundException e) {
            System.out.println("Some error occurred.");
            e.printStackTrace();
          }
          //stack.forEach(System.out::println);
          System.out.print(stack.pop()); /*Resultado final estará na pilha*/
}

public static int operation(Stack<Integer> a, char op){
    int y = a.pop();
    int x = a.pop();

    switch(op) {  /*Retorno de resultados*/ 
        case '+':
            return (x+y);
        case '-':
            return (x-y);
        case '*':
            return (x*y);
        case '/':
            return (x/y);
        default:
            return 0;
    }
}

    }