# Kubernetes Network Simulator

The main goal of this project is to provide Java API to control network simulation on top of Kubernetes.
[KubernetesOperator](https://github.com/jsmadis/kubernetes-network-simulator-operator) does the network simulation. 
The operator defines 2 CRDs (Network and Device), representing simulated network and device deployed inside the
simulated network.

This API provides all necessary functions to simulate the network and uses the operator to manage the simulation.

## Getting started

To use this library, you need to deploy your own Kubernetes cluster following the [operator guide]. Once you
have the Kubernetes up and running, you can set up the `Controller`, which will communicate with the Kubernetes cluster.

```java
    Config config=new ConfigBuilder()
        .withMasterUrl("<Kubernetes URL>")
        .build();
    KubernetesClient client=new DefaultKubernetesClient(config);
    Controller controller=new KubernetesController(client);
```

### KubeNetwork

KubeNetwork represents a simulated device, which can be deployed to the Kubernetes cluster. By default, when you create
the object, it won't create anything in the Kubernetes. To create the simulated network inside Kubernetes, you
need to use the `Controller` interface. The following example creates KubeNetwork object and deploys it to the Kubernetes:

```java
    KubeNetwork network = new KubeNetwork("network-foo");
    controller.createNetwork(network);
```

### KubeDevice

KubeDevice represents devices deployed inside a simulated network.
The class is abstract and is extended with the following classes:

* **Application** - represents a general application that can be deployed to the Kubernetes
* **DataGenerator** - represents deployed emulated **Device** from the [Data Generator Library](https://github.com/PatrIoT-Framework/patriot-data-generator). 
* **ActiveDataGenerator** - represents deployed emulated **Active** (device) from the [Data Generator Library](https://github.com/PatrIoT-Framework/patriot-data-generator). 

The following example shows how to deploy emulated Device to the simulated network:

```java
DataFeed df = new NormalDistVariateDataFeed(18, 2);
Device device = new Thermometer("simple-thermometer", df);

KubeDevice kubeDevice = 
        new DataGenerator("simple-thermometer", network, device);

controller.deployDevice(kubeDevice);
```

The KubeDevice needs to be configured with the KubeNetwork, to deploy the device. 

## Running tests

This project contains integration tests that test the integration with Kubernetes (with running operator).
By default, the tests are disabled because they need to have the Kubernetes instance running.

To run integration tests, you need to expose the following environment variables:

* **PATRIOT_KUBERNETES_URL** - specifies the URL of the kubernetes cluster
* **PATRIOT_LOCAL_IP_ADDR** - specifies the local IP address of your machine, where you are running those tests.

You can easily export the **PATRIOT_KUBERNETES_URL** with the following command:
```shell
$ export PATRIOT_KUBERNETES_URL="https://$(minikube ip):8443"
```

Since the simulation blocks traffic to the deployed devices,
we need to specify the machine's IP address from which we are running the tests.
The tests are communicating with the deployed devices.

If you are running minikube and using calico CNI, you can easily export the **PATRIOT_LOCAL_IP_ADDR** with the following command:
```shell
$ export PATRIOT_LOCAL_IP_ADDR=$(kubectl cluster-info dump | \
  grep "projectcalico.org/IPv4Address" | \
  awk '{print $2}' | \
  sed 's/\("\|,\)//g')
```

Then you can run the test with:

```shell
$ mvn test -Dskip-tests=false
```