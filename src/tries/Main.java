package tries;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"Car", "Carpet", "Cart", "Cat", "Dog", "Doll","Carpooling", "Carpool","coder","coding","code"};

        for (String word : words) {
            trie.insert(word);
        }

        trie.remove("carpooling");
        System.out.println("Done "+trie.search("c"));
    }
}
