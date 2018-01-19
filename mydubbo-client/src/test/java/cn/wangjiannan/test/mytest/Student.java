package cn.wangjiannan.test.mytest;

public class Student extends People {

	private static final long serialVersionUID = 1L;

	public Student(Integer age, String des) {
		// super();// 子类的构造函数默认第一行会默认调用父类无参的构造函数，隐式语句
		System.out.println("I am Student(Integer age, String des)");
		aaa();
		bbb();
	}

}
