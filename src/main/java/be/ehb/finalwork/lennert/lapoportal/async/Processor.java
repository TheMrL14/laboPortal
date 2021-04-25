package be.ehb.finalwork.lennert.lapoportal.async;

public interface Processor<T, G> {
    G process(T toProcess);

}
