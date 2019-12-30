import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class Leitor{

    private ArrayList<String> linhas = new ArrayList<>();
    
    public ArrayList<String> leArquivo(String sf) {
        try {
            File file = new File(sf);
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String line = input.nextLine();
                line = line.trim();
                linhas.add(line);
            }
            input.close();
            return linhas;
        } catch (Exception e) {
            System.out.println(
                    "Nao foi possivel abrir o arquivo,verifique se o mesmo existe ou se esta no mesmo diretório do programa raiz");
            e.printStackTrace();
        }
        return null;
    }
}
