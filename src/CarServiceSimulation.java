import java.util.Random;
import java.util.Scanner;

public class CarServiceSimulation {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();

		CarServer sedanServer = new CarServer("Sedan");
		sedanServer.addServiceTableRow(30, 2);
		sedanServer.addServiceTableRow(28, 3);
		sedanServer.addServiceTableRow(25, 4);
		sedanServer.addServiceTableRow(17, 5);

		CarServer SUVServer = new CarServer("SUV");
		SUVServer.addServiceTableRow(35, 3);
		SUVServer.addServiceTableRow(25, 4);
		SUVServer.addServiceTableRow(20, 5);
		SUVServer.addServiceTableRow(20, 6);

		int prevArrivalTime = 0;
		int totalWaitingTime = 0;
		int totalServiceTime = 0;
		int customersWhoWait = 0;
		int simulationTime = 0;

		System.out.print("Enter the number of cars to simulate for: ");
		int totalCustomers = sc.nextInt();

		System.out.println(
				"\n-------------------------------------------------------------------------------------------------------------");
		System.out.println("No\tRD_Car\tCar\tRD_IAT\tIAT\tAT\tServer\tRD_ST\tST\tSB\tSE\tWT\tIdle");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------");

		for (int i = 0; i < totalCustomers; i++) {
			CarServer servicedBy = null;

			int randomDigitForInterArrivalTime = rand.nextInt(100);
			int interArrivalTime = 0;
			if (i == 0)
				interArrivalTime = 0;
			else if (randomDigitForInterArrivalTime < 26)
				interArrivalTime = 1;
			else if (randomDigitForInterArrivalTime < 66)
				interArrivalTime = 2;
			else if (randomDigitForInterArrivalTime < 86)
				interArrivalTime = 3;
			else
				interArrivalTime = 4;

			prevArrivalTime += interArrivalTime;

			int randomDigitForCarType = rand.nextInt(100);
			String carType = "";

			if (randomDigitForCarType < 50) {
				carType = "Sedan";
				if (sedanServer.busyTill <= prevArrivalTime)
					servicedBy = sedanServer;
				else if (SUVServer.busyTill <= prevArrivalTime)
					servicedBy = SUVServer;
				else
					servicedBy = sedanServer;
			} else {
				carType = "SUV";
				if (SUVServer.busyTill <= prevArrivalTime)
					servicedBy = SUVServer;
				else if (sedanServer.busyTill <= prevArrivalTime)
					servicedBy = sedanServer;
				else
					servicedBy = SUVServer;
			}

			int randomDigitForServiceTime = rand.nextInt(100);
			int serviceTime = 0, waitingTime, idleTime, serviceBegin, serviceEnd;
			for (int j = 0; j < servicedBy.higherLimits.size(); j++)
				if (randomDigitForServiceTime <= servicedBy.higherLimits.get(j)) {
					serviceTime = servicedBy.serviceValues.get(j);
					break;
				}

			if (servicedBy.busyTill <= prevArrivalTime) {
				waitingTime = 0;
				serviceBegin = prevArrivalTime;
				idleTime = prevArrivalTime - servicedBy.busyTill;
			} else {
				customersWhoWait++;
				waitingTime = servicedBy.busyTill - prevArrivalTime;
				serviceBegin = servicedBy.busyTill;
				idleTime = 0;
			}

			servicedBy.idleTime += idleTime;
			serviceEnd = servicedBy.busyTill = serviceBegin + serviceTime;

			System.out.println((i + 1) + "\t" + randomDigitForCarType + "\t" + carType + "\t"
					+ randomDigitForInterArrivalTime + "\t" + interArrivalTime + "\t" + prevArrivalTime + "\t"
					+ servicedBy.getType() + "\t" + randomDigitForServiceTime + "\t" + serviceTime + "\t" + serviceBegin
					+ "\t" + serviceEnd + "\t" + waitingTime + "\t" + idleTime);

			totalWaitingTime += waitingTime;
			totalServiceTime += serviceTime;
			simulationTime = serviceEnd;
		}

		System.out.println("\n\n-----------------------------------------------------");
		System.out.println("Simulation statistics");
		System.out.println("-----------------------------------------------------");

		System.out.printf("Average waiting time:\t\t\t\t%.2f\n", (totalWaitingTime * 1.0 / totalCustomers));
		System.out.printf("Probability that a customer has to wait:\t%.2f\n",
				(customersWhoWait * 1.0 / totalCustomers));
		System.out.printf("Probability of sedan server being idle:\t\t%.2f\n",
				(sedanServer.idleTime * 1.0 / simulationTime));
		System.out.printf("Probability of SUV server being idle:\t\t%.2f\n",
				(SUVServer.idleTime * 1.0 / simulationTime));
		System.out.printf("Average service time:\t\t\t\t%.2f\n", (totalServiceTime * 1.0 / totalCustomers));
		System.out.printf("Average time between intervals:\t\t\t%.2f\n", (prevArrivalTime * 1.0 / totalCustomers));
		System.out.printf("Average waiting time of those who wait:\t\t%.2f\n",
				(totalWaitingTime * 1.0 / customersWhoWait));

		sc.close();
	}
}
