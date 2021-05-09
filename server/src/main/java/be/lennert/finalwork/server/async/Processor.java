package be.lennert.finalwork.server.async;

public interface Processor<T, G> {
    G process(T toProcess);

}
