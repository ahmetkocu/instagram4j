package com.github.instagram4j.instagram4j.actions.feed;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.IGPaginatedRequest;
import com.github.instagram4j.instagram4j.responses.IGPaginatedResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedIterable<T extends IGPaginatedResponse> implements Iterable<T> {
    @NonNull
    private IGClient client;
    @NonNull
    private Supplier<IGPaginatedRequest<T>> requestSupplier;

    @Override
    public Iterator<T> iterator() {
        return new FeedIterator<T>(client, requestSupplier.get());
    }

    @Override
    public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), Spliterator.IMMUTABLE);
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

}
