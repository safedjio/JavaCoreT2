package com.safedjio.texttask.entity.Impl;
import com.safedjio.texttask.entity.ComponentType;
import com.safedjio.texttask.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.safedjio.texttask.entity.TextComponent;
import java.util.List;

public class SymbolLeaf implements TextComponent {

    private static final Logger logger = LogManager.getLogger(SymbolLeaf.class);
    private final char value;
    private final ComponentType type = ComponentType.SYMBOL;

    public SymbolLeaf(char value) {
        this.value = value;
    }

    @Override
    public void add(TextComponent component) {
        logger.error("Attempt to add component to a leaf (Symbol)");
        throw new TextException("Cannot add component to a Symbol (Leaf node)");
    }

    @Override
    public void remove(TextComponent component) {
        logger.error("Attempt to remove component from a leaf (Symbol)");
        throw new TextException("Cannot remove component from a Symbol (Leaf node)");
    }

    @Override
    public TextComponent getChild(int index) {
        throw new TextException("Symbol has no children");
    }

    @Override
    public List<TextComponent> getChildren() {
        throw new TextException("Symbol has no children");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}