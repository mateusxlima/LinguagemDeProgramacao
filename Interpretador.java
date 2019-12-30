/*
@Author Mateus Lima
E-mail mateus.limacg@gmail.com

Classe principal que gerencia todas as outras
*/

import java.util.ArrayList;


public class Interpretador {


    boolean boll = false;
    int n = 0;
    private Leitor leitor = new Leitor();
    Say print = new Say();


    public void analizaTexto(String sf){


        ArrayList<String> codEmLista = leitor.leArquivo(sf);
        int i = 0;

        //WHILE QUE VAI GERENCIAR O TODO O SISTEMA
        while (i < codEmLista.size()) {
            String linha = codEmLista.get(i);
            String[] vetLinha = linha.split(" ");
            String[] vetParente = linha.split("(");

            if(vetLinha[0].equals("var") || Variavel.getVariavel(vetLinha[0]) != null){
                Variavel.trataVariaveis(linha);
            }

            if (vetParente[0].equals("say") || vetParente[0].equals("lnsay")){
                print.sayIt(linha);
            }

            if(vetLinha[0].equals("if")){
                int j = 0;
                if (!Validador.valida(linha)){
                    while (j < codEmLista.size()){
                        String l = codEmLista.get(j);
                        String[] vet = l.split(" ");
                        if (vet[0].equals("endif")){
                            i = j;
                            break;
                        }
                        j++;
                    }
                }
            }

            if (vetLinha[0].equals("while")){
                int j = 0;
                boll = false;
                if (Validador.valida(linha)){
                    n = i;
                    boll = true;
                }
                else{
                    while (j < codEmLista.size()){
                        String l = codEmLista.get(j);
                        String[] vet = l.split(" ");
                        if (vet[0].equals("endwhile")){
                            i = j;
                            break;
                        }
                        j++;
                    } 
                }
            }

            if (boll){
                if (vetLinha[0].equals("endwhile")){
                    i = n-1;
                    boll = false;
                }
            }

            if(vetLinha[0].equals("print")){
                System.out.println("Comando print");
            }

            i++;    
        }


        Variavel.printVariaveis();
        
    }   
}
