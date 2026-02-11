package com.safedjio.texttask.parser;

import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.Impl.LexemeParser;
import com.safedjio.texttask.parser.Impl.ParagraphParser;
import com.safedjio.texttask.parser.Impl.SentenceParser;
import com.safedjio.texttask.parser.Impl.SymbolParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

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

        System.out.println("Original: " + originalText);
        System.out.println("Restored: " + reconstructedText);

        assertTrue(reconstructedText.contains("Hello world."));
        assertTrue(reconstructedText.contains("It is a test."));
    }
}