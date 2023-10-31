import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
    private BufferedReader file;

    public LeitorArquivo(String nome) throws IOException {
        file = new BufferedReader(new FileReader(nome));
        if (file == null) {
            throw new RuntimeException("Could not open file: " + nome);
        }
    }

    public String proximaPalavra() throws IOException {
        StringBuilder word = new StringBuilder();
        int c;
        while ((c = file.read()) != -1) {
            char character = (char) c;
            if (Character.isWhitespace(character)) {
                if (word.length() > 0) {
                    return word.toString();
                }
            } else if (Character.isLetterOrDigit(character)) {
                word.append(character);
            }
        }
        return word.toString();
    }
    
    public void close() throws IOException {
        file.close();
    }
}
