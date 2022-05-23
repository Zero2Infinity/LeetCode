package Others.NestedClasses;

import Others.NestedClasses.OuterClass.PublicInnerClass;
import Others.NestedClasses.OuterClass.PublicStaticNestedClass;

class OuterClass {
	static final String str = "OuterClass Variable";
	
	class InnerClass {
		void display() {
			System.out.println("InnerClass: " + str);
		}
	}
	
	private class PrivateInnerClass {
		void display() {
			System.out.println("PrivateInnerClass: " + str);
		}
	}
	
	public class PublicInnerClass {
		void display() {
			System.out.println("PublicInnerClass: " + str);
		}
	}
	
	// package-private
	static class NestedClass {
		void display() {
			System.out.println("NestedClass: " + str);
		}
		
		void privateStaticDisplay( ) {
			PrivateStaticNestedClass ps = new OuterClass.PrivateStaticNestedClass();
			ps.display();
		}
		
		void privateDisplay() {
			OuterClass o = new OuterClass();
			PrivateInnerClass pi = o.new PrivateInnerClass();
			pi.display();
		}
	}
	
	private static class PrivateStaticNestedClass {
		void display() {
			System.out.println("PrivateNestedClass: " + str);
		}
	}
	
	public static class PublicStaticNestedClass {
		void display() {
			System.out.println("PublicNestedClass: " + str);
		}
	}
	
}

public class Solution {
	
	public static void main(String[] args) {
		OuterClass oc = new OuterClass();
		OuterClass.InnerClass ic = oc.new InnerClass();
		ic.display();
		OuterClass.NestedClass n = new OuterClass.NestedClass();
		n.display();
		// Not Accessible: PrivateStaticNestedClass
		n.privateStaticDisplay();
		// Not Accessible: OuterClass.PrivateInnerClass
		n.privateDisplay();
		PublicStaticNestedClass ps = new PublicStaticNestedClass();
		ps.display();
		PublicInnerClass pic = oc.new PublicInnerClass();
		pic.display();
		
	}
}
