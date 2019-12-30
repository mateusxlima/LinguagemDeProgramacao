/*
Programa que pega um arquivo.sf e interpreta seguindo o conceito de 
linguagem de programação.

@Autores:
Mateus Lima
*/

class Main {

    public static void main(String[] sf){

        Interpretador interpretador = new Interpretador();
        interpretador.analizaTexto(sf[0]); 
        
    }    
} 
