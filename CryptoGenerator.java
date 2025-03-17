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
        return isVisible ? " " : String.valueOf(index);
    }
}

class Word {
    List<Symbol> symbols;

    Word(String word, int startIndex, Random random) {
        symbols = new ArrayList<>();
        int index = startIndex;
        for (char c : word.toCharArray()) {
            boolean isVisible = !Character.isLetter(c) || random.nextBoolean();
            symbols.add(new Symbol(c, isVisible ? -1 : index, isVisible));
            if (Character.isLetter(c)) {
                index++;
            }
        }
    }

    public String getWordString() {
        StringBuilder sb = new StringBuilder();
        for (Symbol s : symbols) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }

    public String getIndexString() {
        StringBuilder sb = new StringBuilder();
        for (Symbol s : symbols) {
            sb.append(s.getIndexString()).append(" ");
        }
        return sb.toString().trim();
    }
}

class Generator {
    List<String> quotes;
    Random random;

    Generator() {
        quotes = Arrays.asList(
            "Когда ты думаешь, что хуже уже быть не может, это может случиться.",
            "Если ты не знаешь, куда ты идешь, любые дороги выведут тебя туда, куда тебе нужно.",
            "Навсегда ничего не бывает.",
            "Злых людей нет на свете, есть только люди несчастливые.",
            "У всего есть своя красота, но не каждый может ее увидеть."
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
        
        for (Word word : wordObjects) {
            System.out.print(word.getWordString() + " ");
        }
        System.out.println();
        for (Word word : wordObjects) {
            System.out.print(word.getIndexString() + " ");
        }
        System.out.println();
    }
}

