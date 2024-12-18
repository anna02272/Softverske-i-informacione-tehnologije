package Zad04Fakultet.ListAndTableModels;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Zad04Fakultet.DataClasses.Student;

@SuppressWarnings({ "rawtypes", "serial" })
public class StudentListModel extends DefaultListModel{

	private ArrayList<Student> listaElemenata;
	
	public StudentListModel(ArrayList<Student> lista) {
		listaElemenata = new ArrayList<Student>();
		listaElemenata.addAll(lista);
	}
	
	public void setNewList(ArrayList<Student> lista){
		int grLE = listaElemenata.size();
		int grL = lista.size();
		listaElemenata = new ArrayList<Student>();
		listaElemenata.addAll(lista);
		
		if(grLE>grL){
			fireIntervalRemoved(this, grL, grLE-1);
			fireContentsChanged(this, 0, grL-1);
		}else if (grLE<grL){
			fireIntervalAdded(this, grLE, grL-1);
			fireContentsChanged(this, 0, grLE-1);
		}
		else {
			fireContentsChanged(this, 0, grL-1);
		}
	}
	
	@Override
	public Object getElementAt(int index) {
		return listaElemenata.get(index);
	}
	
	@Override
	public int indexOf(Object elem) {
		if(elem!=null && elem instanceof Student){
			Student st = (Student) elem;
			for (int i = 0; i < listaElemenata.size(); i++) {
				if(listaElemenata.get(i).getIndex().equals(st.getIndex()))
					return i;
			}
		}
		return -1;
	}
	
	@Override
	public int getSize() {
		return listaElemenata.size();
	}

	@Override
	public Object remove(int arg0) {
		Object ob = listaElemenata.remove(arg0);
		fireIntervalRemoved(this, arg0, arg0);
		return ob;
	}

	@Override
	public void addElement(Object element) {
		if(element instanceof Student){
			listaElemenata.add((Student)element);
			int size = listaElemenata.size();
			fireIntervalAdded(this, size-1, size-1);
		}
	}

	@Override
	public void add(int index, Object element) {
		if(element instanceof Student){
			listaElemenata.add(index, (Student)element);
			fireIntervalAdded(this, index, index);
		}
	}

	@Override
	public void setElementAt(Object element, int index) {
		if(element instanceof Student){
			listaElemenata.set(index, (Student)element);
			fireContentsChanged(this, index, index);
		}
	}

	@Override
	public Object set(int index, Object element) {
		if(element instanceof Student){
			listaElemenata.set(index, (Student)element);
			fireContentsChanged(this, index, index);
		}
		return element;
	}
	
	public ArrayList<Student> getListaElemenata() {
		return listaElemenata;
	}
}
