package nutri.api.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PageImpl<T> implements Page {

    private List<T> list;

    public PageImpl(List<T> list) {
        this.list = list;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public long getTotalElements() {
        return 0;
    }

    @Override
    public Page map(Function converter) {
        return null;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public int getNumberOfElements() {
        return list.size();
    }

    @Override
    public List getContent() {
        return list;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable getPageable() {
        return Page.super.getPageable();
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Pageable nextOrLastPageable() {
        return Page.super.nextOrLastPageable();
    }

    @Override
    public Pageable previousOrFirstPageable() {
        return Page.super.previousOrFirstPageable();
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Page.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Page.super.spliterator();
    }

    @Override
    public Stream stream() {
        return Page.super.stream();
    }

    @Override
    public Streamable filter(Predicate predicate) {
        return Page.super.filter(predicate);
    }

    @Override
    public boolean isEmpty() {
        return Page.super.isEmpty();
    }

    @Override
    public Streamable and(Object[] others) {
        return Page.super.and(others);
    }

    @Override
    public Streamable and(Iterable iterable) {
        return Page.super.and(iterable);
    }

    @Override
    public Streamable and(Streamable streamable) {
        return Page.super.and(streamable);
    }

    @Override
    public List toList() {
        return Page.super.toList();
    }

    @Override
    public Set toSet() {
        return Page.super.toSet();
    }

    @Override
    public Stream get() {
        return Page.super.get();
    }

    @Override
    public Streamable and(Supplier stream) {
        return Page.super.and(stream);
    }

    @Override
    public Streamable flatMap(Function mapper) {
        return Page.super.flatMap(mapper);
    }
}
