
public class q3G {

	static boolean v4 = true;
	static boolean v1 = true;
	static boolean v2 = false;
	static boolean v3 = false;
	static boolean v5 = false;

	public static void main(String[] args) throws Exc5 {
		
		// TODO Auto-generated method stub
		System.out.println("main enter");
	    a();
	    try {
	        c();
	        if (v4 == true) throw new Exc4();
	    } catch (Exc4 ex) {
	    	System.out.println("main caught Exc4");
	    } catch (Exc2 ex) {
	    	System.out.println("main caught Exc2");
	    } catch (Exc1 ex) {
	    	System.out.println("main caught Exc1");
	    }
	    System.out.println("main exit");

	}
	
	static void a( ) {
		System.out.println("a enter");
	    try {
	        b();
	    } catch (Exc1 ex) {
	    	System.out.println("a caught Exc1");
	    }
	    System.out.println("a exit");
	}
	   
	static void b( ) {
		System.out.println("b enter");
	    if (v2 == true) throw new Exc2();
	    System.out.println("b exit");
	}
	   
	static void c( ) throws Exc5 {
		System.out.println("c enter");
	    try {
	        d();
	        if (v4 == true) throw new Exc3();
	    } catch (Exc3 ex) {
	    	System.out.println("c caught Exc3");
	        if (v3 == true) throw new Exc4();
	    } catch (Exc4 ex) {
	    	System.out.println("c caught Exc4");
	    }
	    System.out.println("c exit");
	}

	static void d( ) throws Exc5 {
		System.out.println("d enter");
	    if (v1 == true) throw new Exc1();
	    if (v3 == true) throw new Exc3();
	    if (v5 == true) throw new Exc5();
	    System.out.println("d exit");
	}

}
