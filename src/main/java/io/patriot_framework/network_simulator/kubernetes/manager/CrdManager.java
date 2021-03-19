package io.patriot_framework.network_simulator.kubernetes.manager;

import io.patriot_framework.network_simulator.kubernetes.crd.Crd;

public interface CrdManager<T> {

    T create(T crd);

    T update(T crd);

    boolean delete(T crd);
}
