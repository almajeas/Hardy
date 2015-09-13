import java.util.Date;
import java.util.Scanner;

public class Hardy {
	private int mNth;
	private long[][] mData = new long[500][500];

	public Hardy(int hardy) {
		this.mNth = hardy;
	}

	public static void main(String[] args) {
		// System.out.println(String.format("%d", (int)Math.pow((1729 - 9*9*9),
		// 1./3))); java fail.
		Scanner io = new Scanner(System.in);
		int nth = io.nextInt();
		io.close();
		Hardy mHardy = new Hardy(nth);
		Date d = new Date();
		long start = d.getTime();
		mHardy.getNthHardy();
		d = new Date();
		long duration = (d.getTime() - start) / 1000;
		System.out.println(String.format("Duration to get the %dth hardy combination is %d seconds = %.2f minutes", nth,
				duration, duration / 60.));

	}

	private void getNthHardy() {
		int currentNthHardy = 0;
		int number = 4;
		int limit = (int) Math.pow(number, 1. / 3);// a,b,c,d can not be greater
													// than
		while (currentNthHardy < this.mNth) {
			number++;
			limit = (int) Math.pow(number, 1. / 3);
			for (int a = 1; a < limit / 2; a++) {
				for (int b = limit; b > limit / 2; b--) {
					if (mData[a][b] == 0) {
						mData[a][b] = a * a * a + b * b * b;
					}
					if (mData[a][b] < number)
						break; // b is decreasing => wont reach number
					if (mData[a][b] == number) {
						for (int c = b - 1; c > a + 2; c--) {
							for (int d = (int) Math.round(Math.pow((number - c * c * c), 1. / 3)); d < c
									; d++) {
								if (mData[c][d] == 0) {
									mData[c][d] = (c * c * c + d * d * d);
								}
								if (mData[c][d] < number)
									break; // d is decreasing => wont reach number
								if (mData[c][d] == number) {
									currentNthHardy++;
									System.out.println(String.format("%04d Hardy is: %d^3 + %d^3 = %d = %d^3 + %d^3",
											currentNthHardy, a, b, number, c, d));
								}
							}
						}
					}
				}
			}
		}
	}
}
