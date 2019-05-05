import java.util.List;
import java.util.ArrayList;

class Graphnode<T> {
	// *** fields ***
	private boolean visitMark;
	private T data;
	private List<Graphnode<T>> successors;

	// *** methods ***
	// user defined constructor
	public Graphnode(T data) {
		visitMark = false;
		this.data = data;
		successors = new ArrayList<Graphnode<T>>();
	}
	// ==================

	public boolean getVisitMark() {
		return visitMark;
	}

	public void setVisitMark(boolean mark) {
		visitMark = mark;
	}

	public List<Graphnode<T>> getSuccessors() {
		return this.successors;
	}

	public boolean hasSelfCycle( Graphnode<T> node )
	{
		// call helper method
		return hasSelfCycleAux( node, node );
	}

	private boolean hasSelfCycleAux( Graphnode<T> n, Graphnode<T> reference )
	{
		// set current node true
		n.setVisitMark( true );
		// loop all the children
		for ( Graphnode<T> m : n.getSuccessors() )
		{
			// if found child is reference node, return true
			if ( m == reference )
			{
				return true;
			}
			else
			{
				// if the child is not visited
				if ( !m.getVisitMark() )
				{
					// depth first search
					if ( hasSelfCycleAux( m, reference ) )
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	public void dfs(Graphnode<T> n) {
		n.setVisitMark(true);
		for (Graphnode<T> m : n.getSuccessors()) {
			if (!m.getVisitMark()) {
				dfs(m);
			}
		}
	}

	public String toString() {
		return "Node " + this.data;
	}

}