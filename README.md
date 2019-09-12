# Queueing Simulation
A Java program that simulates the working of a multi-server queueing model.

# Overview
Cars (sedans or SUV's) arrive at random at a service station, where they are serviced by one of the following two servers:
* Sedan server
* SUV server

The sedan server provides faster service as compared to the SUV server.

By default, a car will be serviced by a server of its own type. However, if that server is currently busy, the following may occur:
* If the other server is currently free, the car will begin service at that server
* If the other server is also busy, the car will join the queue of its own server (i.e. a sedan cannot join the SUV server's queue)

The arrival time of the cars is distributed as:

| Arrival time  | Probability   |
| ------------- |:-------------:|
| 1             | 25            |
| 2             | 40            |
| 3             | 20            |
| 4             | 15            |

The service times of the sedan and SUV servers are distributed as:

| Service time  | Probability   |
| ------------- |:-------------:|
| 2             | 30            |
| 3             | 28            |
| 4             | 25            |
| 5             | 17            |

| Service time  | Probability   |
| ------------- |:-------------:|
| 3             | 35            |
| 4             | 25            |
| 5             | 20            |
| 6             | 20            |

# Input:
The number of cars to run the simulation for

# Output:
The statistics for each car which includes:
* Car number
* Random digit for car type
* Car type
* Random digit for inter arrival time
* Inter arrival time
* Arrival time
* Served by
* Random digit for service time
* Service time
* Service start time
* Service end time
* Waiting time of the car
* Idle time of the server

The simulation statistics are also calculated, which includes:
* Average waiting time
* Probability that a customer has to wait
* Probability of sedan server being idle
* Probability of SUV server being idle
* Average service time
* Average time between intervals
* Average waiting time of those who wait
