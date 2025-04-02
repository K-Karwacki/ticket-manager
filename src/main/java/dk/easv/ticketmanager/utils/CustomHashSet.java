package dk.easv.ticketmanager.utils;

import dk.easv.ticketmanager.gui.models.UserModel;

import java.util.*;

public class CustomHashSet<T> implements Set<T>
{
  private final static Object PRESENT = new Object();
  private final HashMap<T,Object> map = new HashMap<>();

  @Override public int size()
  {
    return map.size();
  }

  @Override public boolean isEmpty()
  {
    return map.isEmpty();
  }

  @Override public boolean contains(Object element)
  {
    return map.containsKey(element);
  }

  @Override public Iterator<T> iterator()
  {
    return map.keySet().iterator();
  }

  @Override public Object[] toArray()
  {
    return new Object[0];
  }

  @Override public <T1> T1[] toArray(T1[] a)
  {
    return null;
  }

  @Override public boolean add(T element)
  {
    return map.put(element, PRESENT) == null;
  }

  @Override public boolean remove(Object o)
  {
    return false;
  }

  @Override public boolean containsAll(Collection<?> c)
  {
    return false;
  }

  @Override public boolean addAll(Collection<? extends T> c)
  {
    boolean cd = false;
    for(T element : c){
      if(add(element)){
        cd = true;
      }
    }
    return false;
  }

  @Override public boolean retainAll(Collection<?> c)
  {
    return false;
  }

  @Override public boolean removeAll(Collection<?> c)
  {
    return false;
  }

  @Override public void clear()
  {

  }
}
