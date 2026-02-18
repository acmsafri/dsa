package tries;

import java.util.*;

class TrieNode {
    private char value;
    private Map<Character,TrieNode> children;
    boolean isEndOfWord;

    public TrieNode(char value) {
        this.value = value;
        children = new HashMap<>(); // Assuming only lowercase a-z
        isEndOfWord = false;
    }

    public boolean hasChild(char ch) {
        return children.containsKey(ch);
    }

    public TrieNode getChild(char ch) {
        return children.get(ch);
    }

    public Set<Character> getChildren() {
        return children.keySet();
    }

    public void addChild(char ch) {
        children.put(ch, new TrieNode(ch));
    }

    @Override
    public String toString() {
        return "value=" + value ;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode(' '); // Root node doesn't hold any character
    }

    public void insert(String word) {
        String wordLowerCase = word.toLowerCase();
        TrieNode current = root;
        for (char ch : wordLowerCase.toCharArray()) {
            if (!current.hasChild(ch)) {
                current.addChild(ch);
            }
            current = current.getChild(ch);
        }
        current.isEndOfWord = true;
    }

    public List<String> search(String word) {
        List<String> result = new ArrayList<>();
        String wordLowerCase = word.toLowerCase();

        TrieNode lastNode = getLastNode(wordLowerCase);
        if (lastNode!= null) {
            // Perform DFS from lastNode to find all words
            dfs(lastNode, wordLowerCase, result);
        }

        return result;
    }

    private void dfs(TrieNode lastNode, String wordLowerCase, List<String> result) {
        if (lastNode.isEndOfWord) {
            result.add(wordLowerCase);
        }
        for (char ch : lastNode.getChildren()) {
            dfs(lastNode.getChild(ch), wordLowerCase+ch, result);
        }
    }

    public TrieNode getLastNode(String prefix) {
        String prefixLowerCase = prefix.toLowerCase();
        TrieNode current = root;
        for (char ch : prefixLowerCase.toCharArray()) {
            if (!current.hasChild(ch)) {
                return null;
            }
            current = current.getChild(ch);
        }
        return current;
    }

    private void remove(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return; // Word not found
            }
            current.isEndOfWord = false; // Unmark the end of word
            return;
        }

        char ch = word.charAt(index);
        TrieNode child = current.getChild(ch);
        if (child == null) {
            return; // Word not found
        }

        remove(child, word, index + 1);

        // After returning from recursion, check if child can be removed
        if (!child.isEndOfWord && child.getChildren().isEmpty()) {
            current.getChildren().remove(ch); // Remove the child node
        }
    }

    public void remove(String word) {
        remove(root, word.toLowerCase(), 0);
    }
}
