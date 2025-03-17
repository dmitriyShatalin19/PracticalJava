import java.util.*;

public class CryptoGenerator {
    public static void main(String[] args) {
        new Generator().generate();
    }
}

class Symbol {
    char character;
    int index;
    boolean isVisible;

    Symbol(char character, int index, boolean isVisible) {
        this.character = character;
        this.index = index;
        this.isVisible = isVisible;
    }

    public String toString() {
        return isVisible ? String.valueOf(character) : "_";
    }

    public String getIndexString() {
        return isVisible && index > 0 ? String.valueOf(index) : " ";
    }
}

class Word {
    List<Symbol> symbols;

    Word(String word, int startIndex, Random random) {
        symbols = new ArrayList<>();
        int index = startIndex;
        for (char c : word.toCharArray()) {
            boolean isVisible = !Character.isLetter(c) || random.nextBoolean();
            symbols.add(new Symbol(c, Character.isLetter(c) ? index : -1, isVisible));
            if (Character.isLetter(c)) {
                index++;
            }
        }
    }

    public String[] getWordWithIndex() {
        StringBuilder wordLine = new StringBuilder();
        StringBuilder indexLine = new StringBuilder();
        for (Symbol s : symbols) {
            wordLine.append(s).append(" ");
            indexLine.append(s.getIndexString()).append(" ");
        }
        return new String[]{wordLine.toString().trim(), indexLine.toString().trim()};
    }
}

class Generator {
    List<String> quotes;
    Random random;

    Generator() {
        quotes = Arrays.asList(
            "Ты можешь больше.",
            "Все пройдет.",
            "Боль неизбежна. Страдание — личный выбор каждого"
);
        random = new Random();
    }

    void generate() {
        String quote = quotes.get(random.nextInt(quotes.size()));
        String[] words = quote.split(" ");
        List<Word> wordObjects = new ArrayList<>();
        int letterIndex = 1;
        
        for (String word : words) {
            Word w = new Word(word, letterIndex, random);
            wordObjects.add(w);
            for (char c : word.toCharArray()) {
                if (Character.isLetter(c)) {
                    letterIndex++;
                }
            }
        }
        
        int wordsPerLine = 0;
        StringBuilder wordOutput = new StringBuilder();
        StringBuilder indexOutput = new StringBuilder();
        
        for (Word word : wordObjects) {
            String[] wordAndIndex = word.getWordWithIndex();
            wordOutput.append(wordAndIndex[0]).append("  ");
            indexOutput.append(wordAndIndex[1]).append("  ");
            wordsPerLine++;
            
            if (wordsPerLine >= 3 + random.nextInt(3)) { // 3-5 слов в строке
                System.out.println(wordOutput.toString().trim());
                System.out.println(indexOutput.toString().trim());
                System.out.println();
                wordOutput.setLength(0);
                indexOutput.setLength(0);
                wordsPerLine = 0;
            }
        }
        
        if (wordOutput.length() > 0) {
            System.out.println(wordOutput.toString().trim());
            System.out.println(indexOutput.toString().trim());
        }
    }
}

