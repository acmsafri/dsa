package tries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
/**
 * JUnit tests for the Trie data structure
 */
public class TrieTest {
    private Trie trie;
    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }
    // ==================== Insert Tests ====================
    @Test
    public void testInsertSingleWord() {
        trie.insert("hello");
        assertNotNull(trie.getLastNode("hello"));
    }
    @Test
    public void testInsertMultipleWords() {
        trie.insert("hello");
        trie.insert("world");
        trie.insert("hi");
        assertNotNull(trie.getLastNode("hello"));
        assertNotNull(trie.getLastNode("world"));
        assertNotNull(trie.getLastNode("hi"));
    }
    @Test
    public void testInsertDuplicateWord() {
        trie.insert("apple");
        trie.insert("apple");
        List<String> results = trie.search("apple");
        assertEquals(1, results.size());
        assertTrue(results.contains("apple"));
    }
    @Test
    public void testInsertWordsWithCommonPrefix() {
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("careful");
        assertNotNull(trie.getLastNode("car"));
        assertNotNull(trie.getLastNode("card"));
        assertNotNull(trie.getLastNode("care"));
        assertNotNull(trie.getLastNode("careful"));
    }
    @Test
    public void testInsertEmptyString() {
        trie.insert("");
        // Empty string should be allowed
        assertNotNull(trie.getLastNode(""));
    }
    @Test
    public void testInsertLowercaseConversion() {
        trie.insert("HELLO");
        trie.insert("Hello");
        trie.insert("hello");
        // All should map to the same node due to toLowerCase
        List<String> results = trie.search("hello");
        assertEquals(1, results.size());
    }
    // ==================== Search/Autocomplete Tests ====================
    @Test
    public void testSearchExactWord() {
        trie.insert("hello");
        List<String> results = trie.search("hello");
        assertEquals(1, results.size());
        assertTrue(results.contains("hello"));
    }
    @Test
    public void testSearchWithPrefix() {
        trie.insert("hello");
        trie.insert("help");
        trie.insert("hem");
        List<String> results = trie.search("he");
        assertEquals(3, results.size());
        assertTrue(results.contains("hello"));
        assertTrue(results.contains("help"));
        assertTrue(results.contains("hem"));
    }
    @Test
    public void testSearchSingleCharacterPrefix() {
        trie.insert("apple");
        trie.insert("apricot");
        trie.insert("application");
        List<String> results = trie.search("a");
        assertEquals(3, results.size());
        assertTrue(results.contains("apple"));
        assertTrue(results.contains("apricot"));
        assertTrue(results.contains("application"));
    }
    @Test
    public void testSearchNonExistentPrefix() {
        trie.insert("hello");
        trie.insert("world");
        List<String> results = trie.search("xyz");
        assertTrue(results.isEmpty());
    }
    @Test
    public void testSearchCompleteWord() {
        trie.insert("cat");
        trie.insert("catalog");
        trie.insert("category");
        List<String> results = trie.search("cat");
        assertEquals(3, results.size());
        assertTrue(results.contains("cat"));
        assertTrue(results.contains("catalog"));
        assertTrue(results.contains("category"));
    }
    @Test
    public void testSearchCaseInsensitivity() {
        trie.insert("hello");
        List<String> results1 = trie.search("HELLO");
        List<String> results2 = trie.search("HeLLo");
        assertEquals(1, results1.size());
        assertEquals(1, results2.size());
        assertTrue(results1.contains("hello"));
        assertTrue(results2.contains("hello"));
    }
    @Test
    public void testSearchEmptyTrie() {
        List<String> results = trie.search("hello");
        assertTrue(results.isEmpty());
    }
    // ==================== Get Last Node Tests ====================
    @Test
    public void testGetLastNodeExistingWord() {
        trie.insert("test");
        TrieNode node = trie.getLastNode("test");
        assertNotNull(node);
        assertTrue(node.isEndOfWord);
    }
    @Test
    public void testGetLastNodeExistingPrefix() {
        trie.insert("testing");
        TrieNode node = trie.getLastNode("test");
        assertNotNull(node);
        assertFalse(node.isEndOfWord); // "test" is a prefix but not a complete word
    }
    @Test
    public void testGetLastNodeNonExistentWord() {
        trie.insert("hello");
        TrieNode node = trie.getLastNode("world");
        assertNull(node);
    }
    @Test
    public void testGetLastNodePartialMatch() {
        trie.insert("hello");
        TrieNode node = trie.getLastNode("hel");
        assertNotNull(node);
        assertFalse(node.isEndOfWord);
    }
    @Test
    public void testGetLastNodeEmptyString() {
        trie.insert("hello");
        TrieNode node = trie.getLastNode("");
        assertNotNull(node); // Should return root or similar
    }
    @Test
    public void testGetLastNodeCaseInsensitive() {
        trie.insert("World");
        TrieNode node = trie.getLastNode("WORLD");
        assertNotNull(node);
        assertTrue(node.isEndOfWord);
    }
    // ==================== Complex Scenario Tests ====================
    @Test
    public void testComplexAutoComplete() {
        // Simulate autocomplete for programming keywords
        trie.insert("if");
        trie.insert("else");
        trie.insert("for");
        trie.insert("foreach");
        trie.insert("function");
        List<String> results = trie.search("for");
        assertEquals(2, results.size());
        assertTrue(results.contains("for"));
        assertTrue(results.contains("foreach"));
      //  assertTrue(results.contains("function"));
    }
    @Test
    public void testLargeDataSet() {
        // Insert many words
        String[] words = {"apple", "application", "apply", "apricot", "aquarium",
                          "banana", "band", "bank", "battery", "beautiful",
                          "create", "cream", "creative", "criteria", "critical"};
        for (String word : words) {
            trie.insert(word);
        }
        // Test various prefixes
        assertEquals(3, trie.search("app").size());
        assertEquals(3, trie.search("ban").size());
        assertEquals(3, trie.search("cre").size());
        assertEquals(1, trie.search("aqu").size());
    }
    @Test
    public void testSingleCharacterWords() {
        trie.insert("a");
        trie.insert("b");
        trie.insert("c");
        List<String> results = trie.search("");
        assertEquals(3, results.size());
        assertTrue(results.contains("a"));
        assertTrue(results.contains("b"));
        assertTrue(results.contains("c"));
    }
    @Test
    public void testLongWord() {
        String longWord = "supercalifragilisticexpialidocious";
        trie.insert(longWord);
        List<String> results = trie.search(longWord);
        assertEquals(1, results.size());
        assertTrue(results.contains(longWord));
    }
    @Test
    public void testPrefixWithNoResults() {
        trie.insert("cat");
        trie.insert("dog");
        List<String> results = trie.search("z");
        assertTrue(results.isEmpty());
    }
    @Test
    public void testWordAsSubstringOfAnotherWord() {
        trie.insert("testing");
        trie.insert("test");
        List<String> results = trie.search("test");
        assertEquals(2, results.size());
        assertTrue(results.contains("test"));
        assertTrue(results.contains("testing"));
    }
    @Test
    public void testMultiplePrefixLevels() {
        trie.insert("a");
        trie.insert("ab");
        trie.insert("abc");
        trie.insert("abcd");
        assertEquals(4, trie.search("a").size());
        assertEquals(3, trie.search("ab").size());
        assertEquals(2, trie.search("abc").size());
        assertEquals(1, trie.search("abcd").size());
    }
    @Test
    public void testNoFalsePositives() {
        trie.insert("hello");
        // Something completely different should not be found
        assertNull(trie.getLastNode("xyz"));
    }
    @Test
    public void testSpecialCaseAllLowercaseInput() {
        trie.insert("java");
        List<String> results = trie.search("java");
        assertEquals(1, results.size());
        assertTrue(results.contains("java"));
    }

    // ==================== Remove Tests ====================
    @Test
    public void testRemoveSingleWord() {
        trie.insert("hello");
        trie.remove("hello");
        List<String> results = trie.search("hello");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRemoveNonExistentWord() {
        trie.insert("hello");
        trie.remove("world");
        // Should not throw exception, and hello should still be searchable
        List<String> results = trie.search("hello");
        assertEquals(1, results.size());
        assertTrue(results.contains("hello"));
    }

    @Test
    public void testRemoveWordFromMultipleWords() {
        trie.insert("hello");
        trie.insert("help");
        trie.insert("heap");
        trie.remove("help");

        List<String> results = trie.search("he");
        assertEquals(2, results.size());
        assertTrue(results.contains("hello"));
        assertTrue(results.contains("heap"));
        assertFalse(results.contains("help"));
    }

    @Test
    public void testRemoveOneWordWithCommonPrefix() {
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.remove("car");

        // "car" should be removed but "card" and "care" should still exist
        List<String> results = trie.search("ca");
        assertEquals(2, results.size());
        assertTrue(results.contains("card"));
        assertTrue(results.contains("care"));
        assertFalse(results.contains("car"));
    }

    @Test
    public void testRemoveWordThatIsAPrefixOfAnother() {
        trie.insert("test");
        trie.insert("testing");
        trie.remove("test");

        List<String> results = trie.search("test");
        assertEquals(1, results.size());
        assertTrue(results.contains("testing"));
        assertFalse(results.contains("test"));
    }

    @Test
    public void testRemoveWordThatHasAnotherAsPrefix() {
        trie.insert("test");
        trie.insert("testing");
        trie.remove("testing");

        List<String> results = trie.search("test");
        assertEquals(1, results.size());
        assertTrue(results.contains("test"));
        assertFalse(results.contains("testing"));
    }

    @Test
    public void testRemoveAllWords() {
        trie.insert("apple");
        trie.insert("apricot");
        trie.insert("application");

        trie.remove("apple");
        trie.remove("apricot");
        trie.remove("application");

        List<String> results = trie.search("a");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRemoveCaseInsensitive() {
        trie.insert("Hello");
        trie.remove("hello");

        List<String> results = trie.search("hello");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRemoveAlreadyRemovedWord() {
        trie.insert("test");
        trie.remove("test");
        trie.remove("test"); // Removing again should not cause issues

        List<String> results = trie.search("test");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRemoveFromLargeDataSet() {
        String[] words = {"apple", "application", "apply", "apricot", "aquarium"};
        for (String word : words) {
            trie.insert(word);
        }

        trie.remove("application");
        trie.remove("apricot");

        List<String> results = trie.search("a");
        assertEquals(3, results.size());
        assertTrue(results.contains("apple"));
        assertTrue(results.contains("apply"));
        assertTrue(results.contains("aquarium"));
        assertFalse(results.contains("application"));
        assertFalse(results.contains("apricot"));
    }

    @Test
    public void testRemoveAndReinsertWord() {
        trie.insert("hello");
        trie.remove("hello");
        trie.insert("hello");

        List<String> results = trie.search("hello");
        assertEquals(1, results.size());
        assertTrue(results.contains("hello"));
    }

    @Test
    public void testRemovePartialWord() {
        trie.insert("hello");
        trie.remove("hel"); // "hel" is not a complete word in the trie

        // "hello" should still exist
        List<String> results = trie.search("hello");
        assertEquals(1, results.size());
        assertTrue(results.contains("hello"));
    }

    @Test
    public void testRemoveEmptyString() {
        trie.insert("");
        trie.remove("");

        List<String> results = trie.search("");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testRemoveSingleCharacterWord() {
        trie.insert("a");
        trie.insert("apple");
        trie.remove("a");

        List<String> results = trie.search("a");
        assertEquals(1, results.size());
        assertTrue(results.contains("apple"));
        assertFalse(results.contains("a"));
    }

    @Test
    public void testRemoveWordAndVerifyPrefixStillWorks() {
        trie.insert("programming");
        trie.insert("program");
        trie.insert("progress");
        trie.remove("program");

        List<String> results = trie.search("pro");
        assertEquals(2, results.size());
        assertTrue(results.contains("programming"));
        assertTrue(results.contains("progress"));
        assertFalse(results.contains("program"));
    }

    @Test
    public void testRemoveComplexHierarchy() {
        // Build a complex word hierarchy
        trie.insert("c");
        trie.insert("ca");
        trie.insert("cat");
        trie.insert("cats");
        trie.insert("car");
        trie.insert("card");

        // Remove "cat" - should not affect siblings
        trie.remove("cat");

        List<String> results = trie.search("c");
        assertEquals(5, results.size());
        assertTrue(results.contains("c"));
        assertTrue(results.contains("ca"));
        assertTrue(results.contains("cats"));
        assertTrue(results.contains("card"));
        assertFalse(results.contains("cat"));
    }

    @Test
    public void testGetLastNodeAfterRemove() {
        trie.insert("hello");
        trie.remove("hello");

        TrieNode node = trie.getLastNode("hello");
        // After removal, the node might still exist but isEndOfWord should be false
        if (node != null) {
            assertFalse(node.isEndOfWord);
        }
    }
}
