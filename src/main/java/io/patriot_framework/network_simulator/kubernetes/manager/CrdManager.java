package io.patriot_framework.network_simulator.kubernetes.manager;

public interface CrdManager<T> {

    T create(T crd);

    T update(T crd);

    boolean delete(T crd);

    boolean delete(String name);

    T get(String name);
}
