package cn.wangjiannan.quartz;

public class MyJob01 {

	public void execute() {

		System.out.println("I am Job01");
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
