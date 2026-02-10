package com.safedjio.texttask.service;

public interface TextService {

    long findMaxSentencesWithRepeatedWord(TextComponent text);

    List<TextComponent> sortSentencesByLexemeCount(TextComponent text);

    void swapFirstAndLastLexemes(TextComponent text);

}
