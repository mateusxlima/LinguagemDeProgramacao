import java.util.ArrayList;
import java.util.List;

public abstract class Mat {

    // função para correr todo o vetor e saber quais operações fazer e na ordem que
    // deve fazer
    // Diego Leandro Mazega Duarte
    public static Double math(String arg) {
        char[] c = lerLinha(arg).toCharArray();
        Double total = 0.0;
        List<String> number = new ArrayList<>();
        int j = 0;
      // for para preenchar a lista com apenas o numeros e operadores.
        for (int i = j; i < c.length; i++) {
            String value2 = "";
            if (c[i] == ' ' || c[i] == '=')
                continue;
            else if (Character.isAlphabetic(c[i]) == true) {
                System.out.printf("%n'%c' Não pode ser usado para operações matematicas %n", c[i]);
                return -1.0;
            } 
            else if(c[i] == '(' || c[i] == ')' ){
                number.add(String.valueOf(c[i]));
            }
            else if(i != 0 && c[i-1] == ')') number.add(String.valueOf(c[i]));          
            else if (isNumber(c[i]) != 0){
                 String value = "";
                for(j = i; j<c.length;j++){
                    if(isNumber(c[j])!=0 || c[j] == '.' || c[j] == ','){ 
                        if(c[j] == '.' || c[j] == ','){ 
                            value += String.valueOf(c[j]);  
                        }else value +=isNumber(c[j]);
                    }   
                    else{
                        number.add(value);
                        number.add(String.valueOf(c[j]));
                        break;
                    }
                }
                i = j;
                value2 = value;
            }
            if(i == c.length){
                number.add(value2);
            }
        }
            
        while (number.size() != 1) {
            // precedencia de parenteses
            if(number.indexOf("(") != -1 && number.indexOf(")") != -1){
                number = parenteses(number);
            }
            else if (number.indexOf("*") != -1 || number.indexOf("/") != -1) {
                while ((number.indexOf("*") != -1) || (number.indexOf("/") != -1)) {
                        
                    //verificação da precedencia entre multiplicação e divisão
                        if (number.indexOf("*") !=-1 && number.indexOf("/") == -1) {
                            number = multiply(number);
                        }

                        else if (number.indexOf("*") !=-1 && number.indexOf("/") != -1) {
                            if(number.indexOf("*") < number.indexOf("/")){
                            number = multiply(number);
                        } 
                        
                        else{
                            number = divide(number);
                        }
                        
                    }
                        
                    else if (number.indexOf("/") != -1 && number.indexOf("*") == -1){
                            number = divide(number);
                    }
                    }
            }


            // verificação da precidencia entre soma e subtração
            else if (number.indexOf("+") != -1 && number.indexOf("-") == -1) {
                number  = plus(number);
            }
            else if(number.indexOf("+") != -1 && number.indexOf("-") != -1){
                if(number.indexOf("+") < number.indexOf("-")){
                    number = plus(number);
                }
                else{
                    number = less(number);
            }}
            else if(number.indexOf("-") != -1 && number.indexOf("+") == -1){
                number = less(number);
            }    
        }
        // fim das operações e retorno do resultado final
        total = Double.parseDouble(number.get(0));
        return total;
    }

    // validação para verificar se a posição atual é um numero ou não
    public static int isNumber(char c) {
        String num = "";
        int total = 0;
        if (Character.isDigit(c) == true) {
            num += String.valueOf(c);
        }

        if (!num.equals("")) {
            total = Integer.parseInt(num);
            return total;
        } else
            return total = 0;
    }

    // me retorna apenas os valores após o '=' e apaga os espaços
    public static String lerLinha(String arg){
        String[] args = arg.split("");
        String linha = "";
        int i = 0;
        for(i = 0; i<args.length; i++){
            if(args[i].equals("=")) break;           
        }
        for(int j = i+1;j<args.length;j++){
            if(args[j].equals(" "))continue;
            linha += args[j];
        }
        return linha;
    }

    // multiplicação
    public static List<String> multiply(List<String> number){
                            int ocorrencia = number.indexOf("*");
                            double valor;
                            String b, d; // posições da ocorrencia -1 e ocorrencia +1.
                            
                            b = number.get(ocorrencia - 1);
                            d = number.get(ocorrencia + 1);

                            valor = Double.parseDouble(b) * Double.parseDouble(d);
                            number.add(ocorrencia - 1, Double.toString(valor));

                            int position = number.indexOf("*") - 2;

                            if (number.indexOf("*") == number.size() - 2) {
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                            } else {
                                number.remove(position + 1);
                                number.remove(position + 1);
                                number.remove(position + 1);
                            }
        return number;
    }

    // divisão
    public static List<String> divide(List<String> number){
        int ocorrencia = number.indexOf("/");
        double valor;
                            String b, d; // posições da ocorrencia -1 e ocorrencia +1.

                            b = number.get(ocorrencia - 1);
                            d = number.get(ocorrencia + 1);

                            valor = Double.parseDouble(b) / Double.parseDouble(d);
                            number.add(ocorrencia - 1, Double.toString(valor));

                            int position = number.indexOf("/") - 2;

                            if (number.indexOf("/") == number.size() - 2) {
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                            } else {
                                number.remove(position + 1);
                                number.remove(position + 1);
                                number.remove(position + 1);
                            }
        return number;
    }

    // soma
    public static List<String> plus(List<String> number){
        int ocorrencia = number.indexOf("+");
        double valor;
                            String b, d; // posições da ocorrencia -1 e ocorrencia +1.

                            b = number.get(ocorrencia - 1);
                            d = number.get(ocorrencia + 1);

                            valor = Double.parseDouble(b) + Double.parseDouble(d);
                            number.add(ocorrencia - 1, Double.toString(valor));

                            int position = number.indexOf("+") - 2;

                            if (number.indexOf("+") == number.size() - 2) {
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                            } else {
                                number.remove(position + 1);
                                number.remove(position + 1);
                                number.remove(position + 1);
                            }
        return number;
    }

    // subtração
    public static List<String> less(List<String> number){
        int ocorrencia = number.indexOf("-");
        double valor;
                            String b, d; // posições da ocorrencia -1 e ocorrencia +1.

                            b = number.get(ocorrencia - 1);
                            d = number.get(ocorrencia + 1);

                            valor = Double.parseDouble(b) - Double.parseDouble(d);
                            number.add(ocorrencia - 1, Double.toString(valor));

                            int position = number.indexOf("-") - 2;

                            if (number.indexOf("-") == number.size() - 2) {
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                                number.remove(number.size() - 1);
                            } else {
                                number.remove(position + 1);
                                number.remove(position + 1);
                                number.remove(position + 1);
                            }
        return number;
    }

    // operação entre paresentes
    public static List<String> parenteses(List<String> number){
        int ocorrencia = number.indexOf("(");
        double valor = 0;
        String b, c;
        b = number.get(ocorrencia+1);
        c = number.get(ocorrencia+3);

        
        if(number.get(ocorrencia+2).equals("+"))
            valor = Double.parseDouble(b) + Double.parseDouble(c);
        else if(number.get(ocorrencia+2).equals("-"))
        valor = Double.parseDouble(b) - Double.parseDouble(c);
        else if(number.get(ocorrencia+2).equals("*"))
        valor = Double.parseDouble(b) * Double.parseDouble(c);
        else if(number.get(ocorrencia+2).equals("/"))
        valor = Double.parseDouble(b) / Double.parseDouble(c);
        number.add(ocorrencia, Double.toString(valor));
        
                            int position = ocorrencia+1;
                
                            do{
                            number.remove(position);
                            }while(number.indexOf(")") != -1);
                            
        return number;
    }
}