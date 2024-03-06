package com.quarkton.sort;

import java.util.Collection;

public interface CanSort<T extends CanCompare<T>> extends Length, CanSwap<T>{
    void sort();
}
