package com.safedjio.texttask.entity.Impl;

import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.entity.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {

    private final List<TextComponent> components = new ArrayList<>();
    private final ComponentType type;

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public TextComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = type.getDelimiter();

        for (TextComponent component : components) {
            sb.append(component.toString()).append(delimiter);
        }
        return sb.toString();
    }
}