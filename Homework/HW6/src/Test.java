
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graphnode<String> A = new Graphnode<String>("A");
		Graphnode<String> B = new Graphnode<String>("B");
		Graphnode<String> C = new Graphnode<String>("C");
		Graphnode<String> D = new Graphnode<String>("D");
		A.getSuccessors().add(B);
		B.getSuccessors().add(C);
		C.getSuccessors().add(D);
		D.getSuccessors().add(C);
		C.getSuccessors().add(A);
		//A.getSuccessors().get(0).getSuccessors().add(A);
		System.out.println(A.hasSelfCycle(A));
	}

}
