package com.safedjio.texttask.entity;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);

    void remove(TextComponent component);

    TextComponent getChild(int index);

    List<TextComponent> getChildren();

    TextComponent getType();

    int size();

    @Override
    String toString();
}
