package com.safedjio.texttask.parser;

import com.safedjio.texttask.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TextParser {

    protected static final Logger logger = LogManager.getLogger();

    protected TextParser nextParser;

    public TextParser(){

    }

    public TextParser(TextParser nextParser){
        this.nextParser = nextParser;
    }

    public void setNextParser(TextParser nextParser){
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);

}
