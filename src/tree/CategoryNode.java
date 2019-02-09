package tree;

import java.util.ArrayList;

public class CategoryNode{
	 public int value;
	 public ArrayList<CategoryNode> subCategoryNode;
	 public CategoryNode(){
		 subCategoryNode=new ArrayList<>();
	 }
	 public CategoryNode(int tenure){
		this.value=tenure;  
		this.subCategoryNode=new ArrayList<>();
	}
	@Override
	public String toString() {
		return "CategoryNode [value=" + value + ", subCategoryNode=" + subCategoryNode + "]";
	}
	 
	 
}
