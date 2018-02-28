package com.sap.producerconsumer;

public class EvenOdd {
	static Boolean flag = false;
	private static int count = 1;

	static class Produce {

		void produce() {

			System.out.println("produced.." + count);
			count++;
			if (Thread.currentThread().getName().equals("Thread-0"))
				System.out.println("Done producing odd by " + Thread.currentThread().getName());
			else
				System.out.println("Done producing even by " + Thread.currentThread().getName());

		}
	}

	public static void main(String[] args) throws InterruptedException {

		final Object KEY = new Object();

		Produce producer1 = new Produce();
		Produce producer2 = new Produce();

		Runnable produce1Task = () -> {

			while (true) {

				synchronized (KEY) {
					if (!flag) {
						producer1.produce();
						
						try {
							Thread.sleep(2000);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flag = true;
						KEY.notifyAll();
					}
				}

			}
		};

		Runnable produce2Task = () -> {
			while (true) {

				synchronized (KEY) {
					if (flag) {
						producer2.produce();
						
						try {
							Thread.sleep(2000);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						flag = false;
						KEY.notifyAll();
					}
				}

			}

		};

		Thread produce1Thread = new Thread(produce1Task);
		Thread produce2Thread = new Thread(produce2Task);

		produce1Thread.start();
		produce2Thread.start();

		produce1Thread.join();
		produce2Thread.join();

	}

}