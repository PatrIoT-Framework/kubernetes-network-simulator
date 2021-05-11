package io.patriot_framework.network_simulator.kubernetes.manager;

/**
 * Interface representing manager for the custom resource definitions (CRD).
 * It provides basic method for manipulation with CRDs on the Kubernetes cluster.
 *
 * @param <T> T represents Custom Resource object
 */
public interface CrdManager<T> {


    /**
     * Creates given custom resource.
     *
     * @param crd Custom resource to create
     * @return created custom resource
     */
    T create(T crd);

    /**
     * Updates given custom resource.
     *
     * @param crd custom resource to update
     * @return updated custom resource
     */
    T update(T crd);


    /**
     * Deletes given custom resource
     *
     * @param crd custom resource to delete
     * @return true if the operation was successful, false otherwise
     */
    boolean delete(T crd);

    /**
     * Deletes given custom resource by given name
     *
     * @param name name of the custom resource to delete
     * @return true if the operation was successful, false otherwise
     */
    boolean delete(String name);

    /**
     * Deletes all custom resource inside cluster
     *
     * @return true if the operation was successful, false otherwise
     */
    boolean deleteAll();


    /**
     * Gets custom resource with given name
     *
     * @param name name of the custom resource
     * @return custom resource
     */
    T get(String name);
}
