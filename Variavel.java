/*
@Author Mateus Lima
E-mail mateus.limacg@gmail.com
*/
import java.util.ArrayList;
import java.util.Scanner;


public class Variavel{

    private static ArrayList<String> variavel = new ArrayList<>();


    /*
    O método trataVariaveis() é chamado na classe interpretador quando este
    identificar um uma linha de código em que é necessario fazer uma atribuição ou 
    alteração de valor de variavel; 
    O método recebe a linha em que ocorre a manipulação da variavel, trata a linha e só 
    então chama o método setVariavel() que é quem vai fazer a adição ou troca da variavel
    na lista das variaveis.
    */
    public static void trataVariaveis(String linha){
        String[] vetLinha = linha.split(" ");
        String linhaSvar = "";
        if(vetLinha[0].equals("var")){
            for(int i = 1; i < vetLinha.length; i++){
                linhaSvar = linhaSvar.concat(vetLinha[i]);
            }
        }
        else{
            linhaSvar = linha.replace(" ", "");
        }
        String[] vetorLinha = linhaSvar.trim().replace("=", " = ").split(" ");
        if(getVariavel(vetorLinha[2]) != null){
            vetorLinha[2] = getVariavel(vetorLinha[2]);
        }
        if (vetorLinha[2].equals("input()")){
            vetorLinha[2] = input();
        }
        setVariavel(vetorLinha[0], vetorLinha[2]);
    }
    
    /*
    O método getVariavel() recebe o nome de uma variavel, e retorna o valor dela;
    se não existir uma variavel com este nome o método retorna null;
    */
    public static String getVariavel(String string){
        for(int i = 0; i < variavel.size(); i += 2){
            if(variavel.get(i).equals(string)){
                return variavel.get(i + 1);
            }
        }
        return null;
    }

    /*
    O método setVariavel recebe nome de variavel e valor consecutivamente;
    Então ele chama o método getVariavel() para testar se ja existe uma variavel com este nome,
    se não existir ele adiciona nome e valor na lista, se existir ele chama o método trocaVariavel()
    que vai apenas substituir o valor da variavel existente.
     */
    public static void setVariavel(String nome, String valor){
        if(getVariavel(nome) != null){
            trocaValorVariavel(nome, valor);
        }
        else{
            variavel.add(nome);
            variavel.add(valor);
        }
    }

    /*
        Método trocaVariavel() recebe um nome de uma variavel existente e também um valor 
    que será substituido;
        Para adicionar o novo valor na lista o método percorre toda ela em busca do nome
    da variavel que precisa ser alterada, ao encontrar remove o elemento do proximo índice
    que é o valor da variavel em questão, e só após ira inserir o novo valor no índice
    (nome + 1).
    */
    public static void trocaValorVariavel(String nome, String valor){
        for(int i = 0; i<variavel.size(); i += 2){
            if(variavel.get(i).equals(nome)){
                variavel.remove(i+1);
                variavel.add(i+1, valor);
            }
        }
    }

    public static String input(){
        Scanner scn = new Scanner(System.in);
        String str = scn.nextLine();
        return str;
    }

    //Método que imprime todas as variaveis e seus respectivos valores  
    public static void printVariaveis(){
        for(int i = 0; i < variavel.size(); i += 2){
            System.out.printf("%s = %s\n", variavel.get(i), variavel.get(i+1));
        }
    }
}