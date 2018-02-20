package com.sap.observer;

import java.util.ArrayList;
import java.util.List;

class DoorEvent {

	int floorNum;
	int roomNum;

	DoorEvent(int floorNum, int roomNum) {
		this.floorNum = floorNum;
		this.roomNum = roomNum;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public int getRoomNum() {
		return roomNum;
	}

}

interface DoorListener {
	void on(DoorEvent de);

	void off(DoorEvent de);
}

class Light implements DoorListener {

	@Override
	public void on(DoorEvent de) {
		// TODO Auto-generated method stub
		System.out.println("light on... in floor num: " + de.floorNum + " and room num: " + de.roomNum);

	}

	@Override
	public void off(DoorEvent de) {
		// TODO Auto-generated method stub
		System.out.println("light off... in floor num: " + de.floorNum + " and room num: " + de.roomNum);
	}

}

class AC implements DoorListener {

	@Override
	public void on(DoorEvent de) {
		// TODO Auto-generated method stub
		System.out.println("AC on... in floor num: " + de.floorNum + " and room num: " + de.roomNum);

	}

	@Override
	public void off(DoorEvent de) {
		// TODO Auto-generated method stub
		System.out.println("AC off... in floor num: " + de.floorNum + " and room num: " + de.roomNum);
	}

}

class Door {

	List<DoorListener> list = new ArrayList<>();

	public void addListener(DoorListener dl) {
		list.add(dl);
	}

	public void removeListener(DoorListener dl) {
		list.remove(dl);
	}

	public void openDoor() {
		System.out.println("door opened");
		DoorEvent de = new DoorEvent(4, 3);
		for (DoorListener dl : list) {
			dl.on(de);
		}

	}

	public void closeDoor() {
		System.out.println("door closed");
		DoorEvent de = new DoorEvent(4, 3);
		for (DoorListener dl : list) {
			dl.off(de);
		}
	}

}

public class DoorObserver {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Door d = new Door();
		Light l = new Light();
		AC ac = new AC();
		d.addListener(l);
		d.addListener(ac);
		Thread.sleep(2000);
		d.openDoor();
		Thread.sleep(2000);
		d.closeDoor();

	}

}
