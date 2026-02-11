package com.safedjio.texttask.service;

import com.safedjio.texttask.entity.TextComponent;

import java.util.List;

public interface TextService {

    long findMaxSentencesWithRepeatedWord(TextComponent text);

    List<TextComponent> sortSentencesByLexemeCount(TextComponent text);

    void swapFirstAndLastLexemes(TextComponent text);

}