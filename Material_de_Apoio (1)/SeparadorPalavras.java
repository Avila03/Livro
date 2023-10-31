import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap; // Importe esta classe para ordenar o mapa
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class SeparadorPalavras {
    private BufferedReader file;
    private int currentPage = 1;
    private int currentCharCount = 0;
    private final int maxCharsPerPage = 2500;
    private Map<String, List<Integer>> palavraEPage;
    private Map<String, List<Integer>> palavrasOrdenadas; // Mapa ordenado

    public SeparadorPalavras(String nomeArquivo) throws IOException {
        file = new BufferedReader(new FileReader(nomeArquivo));
        if (file == null) {
            throw new RuntimeException("Could not open file: " + nomeArquivo);
        }
        palavraEPage = new HashMap<>();
        palavrasOrdenadas = new TreeMap<>(); // Inicialize o mapa ordenado
    }

    public void encontrarTodasPalavrasExceto(List<String> palavrasExcluidas) throws IOException {
        int c;
        StringBuilder palavra = new StringBuilder();

        while ((c = file.read()) != -1) {
            char character = (char) c;
            currentCharCount++;

            if (Character.isLetterOrDigit(character)) {
                palavra.append(character);
            } else if (palavra.length() > 0) {
                String palavraEncontrada = palavra.toString();
                if (!palavrasExcluidas.contains(palavraEncontrada)) {
                    int currentPageForWord = (currentCharCount / maxCharsPerPage) + 1;
                    if (!palavraEPage.containsKey(palavraEncontrada)) {
                        palavraEPage.put(palavraEncontrada, new ArrayList<>());
                    }
                    palavraEPage.get(palavraEncontrada).add(currentPageForWord);
                }
                palavra.setLength(0);
            }
        }
    }

    public Map<String, List<Integer>> getResultado() {
        // Ordena o mapa alfabeticamente
        palavrasOrdenadas.putAll(palavraEPage);
        return palavrasOrdenadas;
    }

    public void fecharArquivo() throws IOException {
        file.close();
    }
}
