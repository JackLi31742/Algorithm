package graph;
import java.util.Scanner;
import java.io.FileInputStream;

public class UF {
    private int[] id;
    private int count;

    public UF(int n) {
        if(n < 0)
            throw new IllegalArgumentException();
        count = n;
        id = new int[n];
        for(int i = 0; i < n; i++)
            id[i] = i;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if(p < 0 || p > id.length)
            throw new IndexOutOfBoundsException();
        while(p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot == qRoot)
            return;

        id[pRoot] = qRoot;

        count--;
    }

    public void printIds() {
        for(int i = 0; i < id.length; i++)
            System.out.print(id[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new FileInputStream(args[0]));
        int n = s.nextInt();
        UF uf = new UF(n);
        for(int i = 0; i < n; i++) {
            int p = s.nextInt();
            int q = s.nextInt();
            if(uf.connected(p, q))
                continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
