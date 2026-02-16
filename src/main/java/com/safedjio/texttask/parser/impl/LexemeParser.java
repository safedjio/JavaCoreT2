package com.safedjio.texttask.parser.impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.impl.TextComposite;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.TextParser;

public class LexemeParser extends TextParser {

    private static final String LEXEME_DELIMITER = "\\s+";

    public LexemeParser(TextParser nextParser) {
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {

        TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);

        String[] lexemes = text.split(LEXEME_DELIMITER);

        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = nextParser.parse(lexeme);
            sentenceComposite.add(lexemeComponent);
        }

        return sentenceComposite;
    }
}