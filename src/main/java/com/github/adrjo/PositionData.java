package com.github.adrjo;

import java.util.Objects;

public class PositionData {
    public static final PositionData INVALID = new PositionData(-1, -1);
    public int line;
    public int index;

    public PositionData(int line, int index) {
        this.line = line;
        this.index = index;
    }


    public boolean exists() {
        return !this.equals(INVALID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionData that = (PositionData) o;
        return line == that.line && index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, index);
    }
}
