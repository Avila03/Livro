import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            SeparadorPalavras separador = new SeparadorPalavras("Livros/alice.txt");
            List<String> palavrasExcluidas = new ArrayList<>();
            palavrasExcluidas.add("a");
            palavrasExcluidas.add("about");
            palavrasExcluidas.add("i've");
            // Adicione todas as outras palavras a serem excluídas aqui

            separador.encontrarTodasPalavrasExceto(palavrasExcluidas);

            Map<String, List<Integer>> resultado = separador.getResultado();

            for (Map.Entry<String, List<Integer>> entry : resultado.entrySet()) {
                System.out.print(entry.getKey() + " : Páginas ");
                List<Integer> pages = entry.getValue();
                for (int i = 0; i < pages.size(); i++) {
                    System.out.print(pages.get(i));
                    if (i < pages.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            separador.fecharArquivo();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
        }
    }
}
