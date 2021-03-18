package io.patriot_framework.network_simulator.kubernetes.control;


import io.patriot_framework.network.simulator.api.control.Controller;
import io.patriot_framework.network.simulator.api.model.devices.Device;
import io.patriot_framework.network.simulator.api.model.network.Network;

import java.io.File;
import java.util.List;

/**
 * Implementation of the Controller interface for kubernetes
 */
public class KubernetesController implements Controller {
    @Override
    public void connectDeviceToNetwork(Device device, Network network) {

    }

    @Override
    public void connectDeviceToNetwork(Device device, List<Network> list) {

    }

    @Override
    public void stopDevice(Device device) {

    }

    @Override
    public void disconnectDevice(Device device, Network network) {

    }

    @Override
    public void destroyDevice(Device device) {

    }

    @Override
    public void createNetwork(Network network) {

    }

    @Override
    public void destroyNetwork(Network network) {

    }

    @Override
    public void deployDevice(Device device, String s) {

    }

    @Override
    public void deployDevice(Device device, String s, List<String> list) {

    }

    @Override
    public void deployDevice(Device device, String s, String s1, int i) {

    }

    @Override
    public void deployDevice(Device device, String s, String s1, int i, List<String> list) {

    }

    @Override
    public void deployDevice(Device device, File file) {

    }

    @Override
    public void buildImage(File file, String s) {

    }

    @Override
    public String findGWNetworkIPAddress(Device device) {
        return null;
    }

    @Override
    public String findGWIPAddress(Device device) {
        return null;
    }

    @Override
    public Integer findGWMask(Device device) {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public void executeCommand(Device device, String s) {

    }

    @Override
    public void startDevice(Device device) {

    }
}
