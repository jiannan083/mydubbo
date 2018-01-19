package cn.wangjiannan.test.mytest;

import java.io.Serializable;

public class People implements Serializable {

	private static final long serialVersionUID = 1L;

	// private Integer age;
	// private String des;

	public People() {
		this(null, null);
	}

	public People(Integer age, String des) {
		System.out.println("I am People(Integer age, String des)");
		aaa();
		bbb();
	}

	public void aaa() {
		System.out.println("I am aaa");
	}

	public void bbb() {
		System.out.println("I am bbb");
	}

}
