
/*
    @Author Mateus Lima
    E-mail: mateus.limacg@gmail.com
    
    A classe Validador.java recebe uma linha com operções lógicas ex: "while (100 == n1 && 200 > n2)"
    ou "if (n1 <= 20 || n2 =! 100)" faz a comparação lógica e retorna true ou false.

    Para facilitar o desenvolvimento do código, eu altero em tempo de execução as tradiçionais duplas de caracteres
    lógicos por outros.

    != ->  !
    == ->  =
    <  ->  <
    >  ->  >
    <= ->  @
    >= ->  #
    || ->  |
    && ->  &
*/

public class Validador {

    /*
    O método valida() recebe uma linha inteira contendo um teste lógico, ex: "if (a > b)",
    então faz uma verificação se existe os operadores "&&" ou "||", salva o resultado e vai para o 
    próximo passo que é tratar aquela linha de string chamando a função trataString(), tirando os caracteres inúteis
    e padronizando os caracteres lógicos, a função trataString() retorna a string que vai para o próximo passo, este passo é 
    chamar a função criaVetor() que recebe a linha de string e retorna um vetor de string pronto para passar por o último
    passo que é quando a comparação lógica realmente vai ser feita pela função comparaNumero().
    */

    public static boolean valida(String string){

        boolean variavelBoll = false, and = false;

        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == '&' || string.charAt(i) == '|'){
                variavelBoll = true;
                if(string.charAt(i) == '&'){
                    and = true;
                }
            }
        }
        string = trataString(string);
        if(!variavelBoll){
            return comparaNumero(criaVetor(string));
        }
        if(variavelBoll){
            String[] str = string.replace(")(", ") (").split(" ");
            if(and){
                for (String s : str) {
                    if(!comparaNumero(criaVetor(s))){
                        return false;
                    }
                }
                return true;
            }
            else{
                for (String s : str) {
                    if(comparaNumero(criaVetor(s))){
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public static String[] criaVetor(String string){

        String[] vetorString = string.replace("(", "").replace(")", "").replace("=", " = ").replace("!", " ! ")
        .replace("@", " @ ").replace("#", " # ").replace("<", " < ").replace(">", " > ").split(" ");

        return vetorString;
    }

    public static boolean verificaVariavel(String string){
        if(Variavel.getVariavel(string) != null){
            return true;
        }
        return false;
    }
    
    public static String trataString(String string){

        String stringTratada = string.replace("while", "").replace("if", "").replace("{", "").replace(" ", "")
        .replace("&&", ")(").replace("||", ")(").replace("!=", "!").replace("==", "=").replace("<=", "@").replace(">=", "#");

        return stringTratada;
    }


    
    public static boolean comparaNumero(String[] string){        
        
        double primeira = 0, ultima = 0;

        if(verificaVariavel(string[0])){
            primeira = Double.valueOf(Variavel.getVariavel(string[0]));
        }
        else{
            primeira = Double.valueOf(string[0]);
        }
        if(verificaVariavel(string[2])){
            ultima = Double.valueOf(Variavel.getVariavel(string[2]));
        }
        else{
            ultima = Double.valueOf(string[2]);
        }
        
        char operador = string[1].charAt(0);   

        if(operador == '='){
            if(primeira == ultima){
                return true;
            }
            else{
                return false;
            }
        }
        else if(operador == '!'){
            if(primeira != ultima){
                return true;
            }
            else{
                return false;
            }
        }
        else if(operador == '<'){
            if(primeira < ultima){
                return true;
            }
            else{
                return false;
            }
        }
        else if(operador == '>'){
            if(primeira > ultima){
                return true;
            }
            else{
                return false;
            }
        }
        else if(operador == '@'){
            if(primeira <= ultima){
                return true;
            }
            else{
                return false;
            }
        }
        else if(operador == '#'){
            if(primeira >= ultima){
                return true;
            }
            else{
                return false;
            }
        }
        System.out.println("ERRO no método comparaNumero()");
        return false;
    }
}