package de.informatik.monopoly.visualization;

public class FieldLocations {

	static FieldLocation[] fLs = new FieldLocation[40];
	
	public FieldLocations(int h, int a, int factor) {
		int counter = 8;
		int c = (int) ((h-2*a) / (factor-2));
		
		
		fLs[0] = new FieldLocation(h-a, h-a, a, a);
		for(int i = 1; i < 10; i++) {
			fLs[i] = new FieldLocation(a+counter*c, h-a, c, a);
			counter--;
		}
		
		counter = 8;
		fLs[10] = new FieldLocation(0, h-a, a, a);
		for(int i = 11; i < 20; i++) {
			fLs[i] = new FieldLocation(0, a+counter*c, a, c);
			counter--;
		}
		
		counter = 0;
		fLs[20] = new FieldLocation(0, 0, a, a);
		for(int i = 21; i < 30; i++) {
			fLs[i] = new FieldLocation(a+counter*c, 0, c, a);
			counter++;
		}
		
		counter = 0;
		fLs[30] = new FieldLocation(h-a, 0, a, a);
		for(int i = 31; i < 40; i++) {
			fLs[i] = new FieldLocation(h-a, a+counter*c, a, c);
			counter++;
		}
	}
	
	public FieldLocation[] getFieldLocations() {
		return fLs;
	}

}
