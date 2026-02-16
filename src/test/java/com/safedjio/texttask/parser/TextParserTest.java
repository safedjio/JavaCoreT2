package com.safedjio.texttask.parser;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.impl.LexemeParser;
import com.safedjio.texttask.parser.impl.ParagraphParser;
import com.safedjio.texttask.parser.impl.SentenceParser;
import com.safedjio.texttask.parser.impl.SymbolParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {
    private static final Logger logger = LogManager.getLogger();
    @Test
    void parse_And_Reconstruct_Text() {

        String originalText = "Hello world.\nIt is a test.";

        SymbolParser symbolParser = new SymbolParser();

        LexemeParser lexemeParser = new LexemeParser(symbolParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

        TextComponent textComposite = paragraphParser.parse(originalText);

        assertEquals(2, textComposite.size(), "Should have 2 paragraphs");

        TextComponent firstParagraph = textComposite.getChild(0);
        assertEquals(1, firstParagraph.size(), "First paragraph should have 1 sentence");

        String reconstructedText = textComposite.toString();

        logger.info("Original: {}", originalText);
        logger.info("Restored: {}", reconstructedText);

        assertTrue(reconstructedText.contains("Hello world."));
        assertTrue(reconstructedText.contains("It is a test."));
    }
}