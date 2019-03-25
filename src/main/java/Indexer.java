import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;
import sun.misc.Version;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileTypeDetector;

public class Indexer {

    private IndexWriter writer;
    private IndexWriterConfig config;
    //创建Lucene index writer
    public Indexer(String indexDir) throws IOException{
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer6x();
        config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        writer = new IndexWriter(dir, config);

    }
    //关闭Index writer
    public void close() throws IOException{
        writer.close();
    }
    //根据dataDir下的文件建立索引
    public int index(String dataDir, FileFilter filter) throws Exception{

        File[] files = new File(dataDir).listFiles();
        for(File f:files){
            if(!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && filter.accept(f)){
                indexFile(f);
            }
        }
        return writer.numDocs();  //返回被索引的文档数
    }

    private static String readString2(File file) {
        StringBuffer str=new StringBuffer("");
        try {
            FileReader fr=new FileReader(file);
            int ch = 0;
            while((ch = fr.read())!=-1 ) {
                System.out.print((char)ch+" ");
            }
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("File reader出错");
        }
        return str.toString();
    }


    protected void indexFile(File f) throws IOException{
        FieldType contentsType = new FieldType();
        contentsType.setIndexOptions(IndexOptions.DOCS);
        contentsType.setStored(true);
//        contentsType.setTokenized(true);
//        contentsType.setStoreTermVectors(true);
//        contentsType.setStoreTermVectorPositions(true);
//        contentsType.setStoreTermVectorOffsets(true);
//        contentsType.setStoreTermVectorPayloads(true);
        FieldType nameType = new FieldType();
        nameType.setIndexOptions(IndexOptions.DOCS);
        nameType.setStored(true);
        Document doc = new Document();
        //String content = readString2(f);
        doc.add(new TextField("contents", new FileReader(f)));
        doc.add(new Field("filename", f.getName(),nameType));
        doc.add(new Field("fullpath", f.getCanonicalPath(), nameType));
        System.out.println("indexing"+f.getCanonicalPath());
        writer.addDocument(doc);
        writer.commit();
    }

    private static class TextFilter implements FileFilter{
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }

    public static void main(String[] args) {
        String indexDir = "indexDir";
        String dataDir = "/home/linjiaqin/doc/data";

        long start = System.currentTimeMillis();
        Indexer indexer = null;
        try {
             indexer = new Indexer(indexDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numIndexed = 0;
        try {
            numIndexed = indexer.index(dataDir, new TextFilter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("indexing:"+numIndexed);
        System.out.println("time use:"+(end-start));
    }
}
