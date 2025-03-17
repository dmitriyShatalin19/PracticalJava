import java.util.*;

public class CryptoGenerator {
    public static void main(String[] args) {
        new Generator().generate();
    }
}

class Symbol {
    char character;
    boolean isVisible;

    Symbol(char character, boolean isVisible) {
        this.character = character;
        this.isVisible = isVisible;
    }

    public String toString() {
        return isVisible ? String.valueOf(character) : "_";
    }
}

class Word {
    List<Symbol> symbols;

    Word(String word, Random random) {
        symbols = new ArrayList<>();
        for (char c : word.toCharArray()) {
            boolean isVisible = Character.isWhitespace(c) || !Character.isLetter(c) || random.nextBoolean();
            symbols.add(new Symbol(c, isVisible));
        }
    }

    public String getWordString() {
        StringBuilder sb = new StringBuilder();
        for (Symbol s : symbols) {
            sb.append(s).append(" ");
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
        
        for (String word : words) {
            Word w = new Word(word, random);
            System.out.print(w.getWordString() + " ");
        }
        System.out.println();
    }
}

