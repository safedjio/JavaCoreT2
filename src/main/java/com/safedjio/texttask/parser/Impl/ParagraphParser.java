package com.safedjio.texttask.parser.Impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.Impl.TextComposite;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.TextParser;

public class ParagraphParser extends TextParser {

    private static final String PARAGRAPH_DELIMITER = "\\R";

    public ParagraphParser(TextParser nextParser){
        super(nextParser);
    }

    @Override
    public TextComponent parse(String text) {

        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = text.split(PARAGRAPH_DELIMITER);

        for(String paragraph : paragraphs){
            if(!paragraph.isEmpty()){
                TextComponent paragraphComponenet = nextParser.parse(paragraph);
                textComposite.add(paragraphComponenet);
            }
        }

        return textComposite;
    }
}