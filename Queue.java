package javaSimulation;

import java.util.LinkedList;

//taken from notes verbatim, might change later
class Queue<E>  extends LinkedList<E>
{
    @Override
	public void push(E item)
    {
        super.addLast(item);
    }

    @Override
	public E pop()
    {
        return super.removeFirst();
    }


}
