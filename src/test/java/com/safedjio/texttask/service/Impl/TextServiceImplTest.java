package com.safedjio.texttask.service.Impl;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.Impl.LexemeParser;
import com.safedjio.texttask.parser.Impl.ParagraphParser;
import com.safedjio.texttask.parser.Impl.SentenceParser;
import com.safedjio.texttask.parser.Impl.SymbolParser;
import com.safedjio.texttask.parser.TextParser;
import com.safedjio.texttask.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TextServiceImplTest {
    private static final Logger logger = LogManager.getLogger();

    private TextService textService;
    private TextParser parserChain;

    @BeforeEach
    void setUp() {
        textService = new TextServiceImpl();

        SymbolParser symbolParser = new SymbolParser();
        LexemeParser lexemeParser = new LexemeParser(symbolParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        parserChain = new ParagraphParser(sentenceParser);
    }

    @Test
    void sortSentencesByLexemeCount() {

        String textStr = "This is a very long sentence. Short one.";

        TextComponent text = parserChain.parse(textStr);

        List<TextComponent> sorted = textService.sortSentencesByLexemeCount(text);

        assertEquals(2, sorted.size());
        assertEquals(2, sorted.get(0).size());
        assertTrue(sorted.get(0).toString().contains("Short"));
        assertEquals(6, sorted.get(1).size());
    }

    @Test
    void swapFirstAndLastLexemes() {

        String textStr = "Hello beautiful world.";
        TextComponent text = parserChain.parse(textStr);

        textService.swapFirstAndLastLexemes(text);

        String result = text.toString();

        assertTrue(result.startsWith("world."));
        assertTrue(result.trim().endsWith("Hello"));

        logger.info("Swapped: {}", result);
    }

    @Test
    void findMaxSentencesWithRepeatedWord() {


        String textStr = "Hello world. Hello my friend. World is big. Hello again.";
        TextComponent text = parserChain.parse(textStr);

        long maxCount = textService.findMaxSentencesWithRepeatedWord(text);

        assertEquals(3, maxCount, "Word 'hello' appears in 3 sentences");
    }

}