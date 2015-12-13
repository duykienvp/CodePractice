package com.duykien.practice.algo.hmm;

import java.util.Arrays;

/**
 * A demo HMM with 2 states 1, 2 and 4 emitted characters 'A', 'C', 'G', 'T'
 * For convenience, cast A -> 0; C -> 1; G -> 2; T-> 3;
 * @author kiennd
 *
 */
public class HMM {
	public static final int NUM_STATES = 2 + 1;
	public static final int NUM_COMPONENTS = 4 + 1;
	
	private double[][] a; //transition matrix
	private double[][] e; //emission matrix 
	private String x; //observed sequence
	
	private double[][] f;
	private double px;
	private double[][] b;
	private double[][] v;
	private int[][] trace;
	
	public HMM() {
		initializeTest();
	}

	private void initializeTest() {
		a = new double[NUM_STATES][NUM_STATES];
		a[0][0] = 0;
		a[0][1] = 0.5;
		a[0][2] = 0.5;
		a[1][0] = 0;
		a[1][1] = 0.8;
		a[1][2] = 0.2;
		a[2][0] = 0;
		a[2][1] = 0.2;
		a[2][2] = 0.8;
		
		
		e = new double[NUM_STATES][NUM_COMPONENTS];
		e[1][0] = 0.4;
		e[1][1] = 0.1;
		e[1][2] = 0.4;
		e[1][3] = 0.1;
		
		e[2][0] = 0.1;
		e[2][1] = 0.4;
		e[2][2] = 0.1;
		e[2][3] = 0.4;
		
		x = "123102";
	}

	public void runForward() {
		f = new double[NUM_STATES][x.length() + 1];
		for (int i = 0; i < NUM_STATES; i++)
			f[i][0] = 0;
		f[0][0] = 1;
		
		for (int i = 1; i < x.length() + 1; i++) {
			int ch = Integer.parseInt(x.charAt(i-1) + "");
//			System.out.println("char at " + i + " = " + ch); 
			for (int l = 0; l < NUM_STATES; l++) {
				f[l][i] = 0;
				for (int k = 0; k < NUM_STATES; k++) {
					f[l][i] += f[k][i-1] * a[k][l];
				}
				f[l][i] *= e[l][ch];
				
				System.out.println("f" + l + "(" + i + ")=" + f[l][i]);
			}
		}
		
//		px = 0;
//		for (int i = 0; i < NUM_STATES; i++) {
//			px += f[i][x.length()];
//		}
//		System.out.println(px);
	}
	
	public void runBackward() {
		b = new double[NUM_STATES][x.length() + 1];
		for (int l = 0; l < NUM_STATES; l++)
			b[l][x.length()] = 1;
		
//		double p1 = f[1][x.length()] / px;
//		System.out.println("P(p" + 1 + " = " + x.length() + "|x) = " + p1);
//		double p2 = f[2][x.length()] / px;
//		System.out.println("P(p" + 2 + " = " + x.length() + "|x) = " + p2);
		
		for (int i = x.length() - 1; 0 < i; i--) {
			for (int l = 1; l < NUM_STATES; l++) {
				b[l][i] = 0;
				for (int k = 1; k < NUM_STATES; k++) {
					int ch = Integer.parseInt(x.charAt(i) + "");
					b[l][i] += e[k][ch] * a[l][k] * b[k][i+1];
				}
				
//				System.out.println("b" + l + "(" + i + ") = " + b[l][i]);
				double p = f[l][i] * b[l][i] / px;
//				System.out.println("P(p" + i + " = " + l + "|x) = " + p);
			}
//			System.out.println();
		}
	}
	
	public void runViterbi() {
		v = new double[NUM_STATES][x.length() + 1];
		for (int i = 0; i < NUM_STATES; i++)
			v[i][0] = 0;
		v[0][0] = 1;
		
		trace = new int[NUM_STATES][x.length() + 1];
		
		for (int i = 1; i < x.length() + 1; i++) {
			int ch = Integer.parseInt(x.charAt(i-1) + "");
			for (int l = 0; l < NUM_STATES; l++) {
				v[l][i] = Double.MIN_VALUE;
				
				for (int k = 0; k < NUM_STATES; k++) {
					if (v[l][i] < v[k][i-1] * a[k][l]) {
						v[l][i] = v[k][i-1] * a[k][l];
						trace[l][i] = k;
					}
				}
				v[l][i] *= e[l][ch];
				
				System.out.println("v" + l + "(" + i + ")=" + v[l][i]);
				System.out.println("trace" + l + "(" + i + ")=" + trace[l][i]);
			}
		}
		
		int pos = 1;
		if (v[1][x.length()] < v[2][x.length()]) {
			pos = 2;
		}
		
		String path = pos + "";
		int i = x.length();
		while (pos != 0) {
			pos = trace[pos][i];
			i--;
			path = pos + " " + path;
		}
		
		System.out.print(path);
	}
	
	public static void main(String[] args) {
		HMM hmm = new HMM();
		hmm.runForward();
		hmm.runBackward();
		hmm.runViterbi();
	}
}
