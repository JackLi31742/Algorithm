package graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class MyGraph2 extends CGraph<Integer>{
	
	//邻接表 顶点 list
	ArrayList<Vertex> vertexs;
	//设定最多的顶点的数量
//	int vNum;
	public MyGraph2() {
		super();
		this.vertexs = new ArrayList<>();
//		this.vNum=3;
	}

	@Override
	public OpStatus addV(Integer value) {
		// TODO Auto-generated method stub
		Vertex vertex=new Vertex(value);
//		if (vertexs.size()<vNum) {
//			ArrayList<Vertex> list=new ArrayList<>();
//			list.add(vertex);
			this.vertexs.add(vertex);
			Collections.sort(vertexs,comparator2);
//		}else {
//			
//			//每次都添加到顶点比value大的出边表中，然后把这个列表排序，意味着value将会替换原来的顶点
//			boolean flag=false;
//			for (int i = 0; i < vertexs.size(); i++) {
//				if (vertexs.get(i).get(0).data>vertex.data) {
//					flag=true;
//					vertexs.get(i).add(vertex);
//					Collections.sort(vertexs.get(i), comparator2);
//					break;
//				}
//			}
//			if (!flag) {
//				vertexs.get(vertexs.size()-1).add(vertex);
//				Collections.sort(vertexs.get(vertexs.size()-1), comparator2);
//			}
//			
//			
//		}
		this.m_countV++;
		return OpStatus.OK;
	}

	@Override
	public OpStatus removeV(Integer value) {
		// TODO Auto-generated method stub
		for (int i = 0; i < vertexs.size(); i++) {
			Vertex v=vertexs.get(i);
			if (v.data==value) {
				this.vertexs.remove(v);
				
			}else {
				Edge edge=v.nextNode;
				if (edge.data==value) {
					v.nextNode=edge.next;
				}else {
					Edge p=edge;
					while(edge.next!=null) {
						if (edge.next.data==value) {
							p.next=edge.next;
						}
						edge=edge.next;
						p=p.next;
					}
				}
			}
		}
		
		
		this.m_countV--;
		return OpStatus.OK;
	}

//	//得到所有的边
//	public int getEdgesNum() {
//		for (int i = 0; i < vertexs.size(); i++) {
//			Vertex list=vertexs.get(i);
//			while(list.ne!=null) {
//				
//			}
//			this.m_edgeV+=list.size()-1;
//			
//		}
//		return m_edgeV;
//	}
	@Override
	public OpStatus addE(Integer from, Integer to, int w) {
		// TODO Auto-generated method stub
		Vertex fVertex=null;
		Vertex tVertex=null;
		for (int i = 0; i < vertexs.size(); i++) {
			Vertex v=vertexs.get(i);
//			for (int j = 0; j < list.size(); j++) {
				
				if (v.data==from) {
					fVertex=v;
				}
				
//				if (v.data==to) {
//					tVertex=v;
//				}
//			}
		}
		
		Edge tEdge=new Edge(to, null, w);
		Edge fEdge=fVertex.nextNode;
		if (fEdge==null) {
			fVertex.nextNode=tEdge;

		}else {
			Edge p=fEdge;
			while(fEdge.next!=null) {
				if (fEdge.next.data<tEdge.data) {
//					tEdge.next=fEdge;
//					p.next=tEdge;
					fEdge=fEdge.next;
					p=p.next;
				}
			}
		}

		flist.add(tVertex);
		Collections.sort(flist, comparator2);
//		this.m_edgeV=getEdgesNum()+1;
		return OpStatus.OK;
	}

	@Override
	public OpStatus removeE(Integer from, Integer to) {
		// TODO Auto-generated method stub
		ArrayList<Vertex> flist=null;
		Vertex tVertex=null;
		for (int i = 0; i < vertexs.size(); i++) {
			ArrayList<Vertex> list=vertexs.get(i);
			if (list.get(0).data==from) {
				flist=list;
			}
			if (list.get(0).data==to) {
				tVertex=list.get(0);
			}
		}
		if (tVertex!=null) {
			flist.remove(tVertex);
		}
		return OpStatus.OK;
	}

	@Override
	public void DFS() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DFS(Integer from) {
		// TODO Auto-generated method stub
		ArrayList<Vertex> flist=null;
		for (int i = 0; i < vertexs.size(); i++) {
			ArrayList<Vertex> list=vertexs.get(i);
			if (list.get(0).data==from) {
				flist=list;
				break;
			}
		}
		
	}

	@Override
	public void BFS() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BFS(Integer from) {
		// TODO Auto-generated method stub
		
	}

	//顶点
	class Vertex {
		//存数据，方便起见，也代表序号
		public Integer data;
		//下一个节点
		public Edge nextNode;
		public Vertex(Integer value) {
			this.data=value;
		}
	}
	
	class Edge{
		int data;

		Edge next;
		//权值
		int w;
		public Edge(int data, Edge next, int w) {
			super();
			this.data = data;
			this.next = next;
			this.w = w;
		}

		
		
	}
	//比较器
	Comparator<ArrayList<Vertex>> comparator1=new Comparator<ArrayList<Vertex>>() {

		@Override
		public int compare(ArrayList<Vertex> list1, ArrayList<Vertex> list2) {
			// TODO Auto-generated method stub
			return list1.get(0).data-list2.get(0).data;
		}
		
	};
	
	Comparator<Vertex> comparator2=new Comparator<Vertex>() {

		@Override
		public int compare(Vertex v1, Vertex v2) {
			// TODO Auto-generated method stub
			return v1.data-v2.data;
		}

		
	};
}

