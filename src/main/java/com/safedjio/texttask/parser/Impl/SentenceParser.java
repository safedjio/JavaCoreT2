package com.safedjio.texttask.parser.Impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.Impl.TextComposite;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.TextParser;

public class SentenceParser extends TextParser {

    private static final String SENTENCE_DELIMITER = "(?<=[.!?])\\s+";

    public SentenceParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {

        TextComposite paragraphComposite = new TextComposite(ComponentType.PARAGRAPH);

        String[] sentences = text.split(SENTENCE_DELIMITER);

        for (String sentence : sentences) {

            TextComponent sentenceComponent = nextParser.parse(sentence);
            paragraphComposite.add(sentenceComponent);
        }

        return paragraphComposite;
    }
}