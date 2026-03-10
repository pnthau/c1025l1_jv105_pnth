package module_2.src.ss10_arraylist_linkedlist.thuc_hanh;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.lang.System.arraycopy;

public class MyArrayList<E> implements MyList<E> {
    public static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elements;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Set set = new HashSet(Arrays.asList(this.elements));
//        for (int i = 0; i < this.size; i++) {
//            if (this.elements[i] == null && o == null) {
//                return true;
//            }
//            if (this.elements[i] == null) {
//                continue;
//            }
//            if (this.elements[i].equals(o)) {
//                return true;
//            }
//        }
        return set.contains(o);
    }

    @Override
    public @NotNull Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) elements[index++];
            }
        };
    }

    @Override
    public @NotNull Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] a) throws ArrayStoreException {
        if (a.length < this.size) {
            return (T[]) Arrays.copyOf(this.elements, this.size, a.getClass());
        }
        arraycopy(this.elements, 0, a, 0, this.size);
        if (a.length > this.size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (this.size == this.elements.length) {
            ensureCapa();
        }
        elements[this.size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        E element = (E) o;
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)) {
                index = i;
                break;
            }
        }
        for (int i = index; i < this.size; i++) {
            this.elements[i] = this.elements[i + 1];
        }
        return true;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends E> c) {
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        for (Object o : c) {
            this.remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E get(int i) {
        if (i > this.size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size" + i);
        }
        return (E) elements[i];
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (int i = 0; i < this.size; i++) {
            joiner.add(elements[i].toString());
        }
        return joiner.toString();
    }

    private void ensureCapa() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
}
