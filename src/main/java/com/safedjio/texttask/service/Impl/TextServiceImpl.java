package com.safedjio.texttask.service.Impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.Impl.TextComposite;
import com.safedjio.texttask.entity.TextComponent;
import com.safedjio.texttask.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextServiceImpl implements TextService {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<TextComponent> sortSentencesByLexemeCount(TextComponent text) {

        List<TextComponent> sentences = findAllSentences(text);

        sentences.sort(Comparator.comparingInt(TextComponent::size));

        logger.info("Sentences sorted by lexeme count. Total: {}", sentences.size());
        return sentences;
    }

    @Override
    public void swapFirstAndLastLexemes(TextComponent text) {
        List<TextComponent> sentences = findAllSentences(text);

        for (TextComponent sentence : sentences) {

            if (sentence instanceof TextComposite) {
                TextComposite sentenceComposite = (TextComposite) sentence;

                List<TextComponent> lexemes = sentence.getChildren(); // Это копия

                if (lexemes.size() > 1) {

                    TextComponent first = lexemes.get(0);
                    TextComponent last = lexemes.get(lexemes.size() - 1);

                    lexemes.set(0, last);
                    lexemes.set(lexemes.size() - 1, first);

                    for (TextComponent lexeme : new ArrayList<>(sentence.getChildren())) {
                        sentence.remove(lexeme);
                    }

                    for (TextComponent lexeme : lexemes) {
                        sentence.add(lexeme);
                    }
                }
            }
        }
        logger.info("Swapped first and last lexemes in all sentences");
    }

    private List<TextComponent> findAllSentences(TextComponent component) {
        List<TextComponent> result = new ArrayList<>();

        if (component.getType() == ComponentType.SENTENCE) {
            result.add(component);
        } else {
            for (TextComponent child : component.getChildren()) {
                result.addAll(findAllSentences(child));
            }
        }
        return result;
    }

    @Override
    public long findMaxSentencesWithRepeatedWord(TextComponent text) {

        List<TextComponent> sentences = findAllSentences(text);

        Map<String, Integer> wordCounts = new HashMap<>();

        for (TextComponent sentence : sentences) {

            Set<String> uniqueWordsInSentence = new HashSet<>();

            for (TextComponent lexeme : sentence.getChildren()) {

                String word = lexeme.toString().trim().toLowerCase();
                uniqueWordsInSentence.add(word);
            }

            for (String word : uniqueWordsInSentence) {

                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        if (wordCounts.isEmpty()) {
            return 0;
        }

        int maxCount = Collections.max(wordCounts.values());

        logger.info("Max sentences with repeated word found: {}", maxCount);
        return maxCount;
    }

}