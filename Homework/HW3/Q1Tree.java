import java.util.Iterator;

public class Q1Tree
{

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub
		Treenode<String> T1 = new Treenode<String>();
		T1.getChildren().add( new Treenode<String>() );
		T1.getChildren().add( new Treenode<String>() );
		//System.out.println(T1.getChildren().size());
		//T1.getChildren().add( new Treenode<String>() );
		//T1.getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 0 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 0 ).getChildren().add( new Treenode<String>() );
		/*
		
		Iterator<Treenode<String>> iter = T1.getChildren().iterator();
		while ( iter.hasNext() ) 
		{
			System.out.println(T1.isBinary(iter.next()));
			System.out.println('A');
		}
		*/
		//T1.getChildren().add( new Treenode<String>() );
		//T1.getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		//T1.getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		//T1.getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		
		T1.getChildren().get( 1 ).getChildren().get(0).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(0).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().add( new Treenode<String>() );
		//T1.getChildren().get( 1 ).getChildren().get(1).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		//T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 1 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 0 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 0 ).getChildren().add( new Treenode<String>() );
		T1.getChildren().get( 1 ).getChildren().get(1).getChildren().get( 0 ).getChildren().add( new Treenode<String>() );
		System.out.println(T1.isBinary(T1));
	}

}
