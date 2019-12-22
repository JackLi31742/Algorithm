package graph;
/*
 * ͼ�ĳ�����
 * ���涨������δ��ڣ���ϵ��α�ʾ��
 * ֻ�涨Ҫ�����Щ����
 */
public abstract class CGraph<T> {
	protected int m_countV;
	protected int m_edgeV;
	
	protected CGraph(){
		m_countV = 0;
		m_edgeV = 0;
	}
	
	public abstract OpStatus addV(T value);
	public abstract OpStatus removeV(T value);
	
	public OpStatus updateV(T value){
		return OpStatus.OK;
	}
	
	public abstract OpStatus addE(T from, T to, int w);
	public abstract OpStatus removeE(T from, T to);
	
	public OpStatus updateE(T from, T to, int w){
		return OpStatus.OK;
	}	
	
	public abstract void DFS();
	public abstract void DFS(T from);
	public abstract void BFS();
	public abstract void BFS(T from);	
}
