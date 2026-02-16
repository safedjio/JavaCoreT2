package com.safedjio.texttask.parser.impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.impl.SymbolLeaf;
import com.safedjio.texttask.entity.impl.TextComposite;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.parser.TextParser;

public class SymbolParser extends TextParser {



    @Override
    public TextComponent parse(String text) {

        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);

        char[] symbols = text.toCharArray();

        for (char symbol : symbols) {
            lexemeComposite.add(new SymbolLeaf(symbol));
        }

        return lexemeComposite;
    }
}