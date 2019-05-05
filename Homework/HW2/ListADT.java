
public interface ListADT<E>
{
	void add(E item);
	void add(int pos, E item);
	boolean contains(E item);
	int size( );
	boolean isEmpty( );
	E get(int pos);
	E remove(int pos);

}
