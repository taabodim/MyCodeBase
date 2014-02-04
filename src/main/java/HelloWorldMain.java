public class HelloWorldMain {
	public static void main(String[] args) {
		HelloWorld hello = new HelloWorld();
		hello.displayMessage();
	}
}

class HelloWorld {
	public native void displayMessage();

	static {
		System.loadLibrary("HelloWorldImp");
	}
}
