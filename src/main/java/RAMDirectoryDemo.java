//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.core.SimpleAnalyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.store.RAMDirectory;
//
//import java.io.IOException;
//
//public class RAMDirectoryDemo {
//
//    public static void main(String[] args) throws IOException {
//        long startTime = System.currentTimeMillis();
//        System.out.println("*****************检索开始**********************");
//        // 创建一个内存目录对象，所以这里生成的索引不会放在磁盘中，而是在内存中。
//        RAMDirectory directory = new RAMDirectory();
//         /*
//         * 创建索引写入对象，该对象既可以把索引写入到磁盘中也可以写入到内存中。 参数说明：
//         * public IndexWriter(Directory d, Analyzer a, boolean create, MaxFieldLength mfl)
//         * directory:目录对象,也可以是FSDirectory 磁盘目录对象
//         * analyzer:分词器，分词器就是将检索的关键字分割成一组组词组， 它是lucene检索查询的一大特色之一， new SimpleAnalyzer()这个是lucene自带的最为简单的分词器； create: 是否新建，这里肯定要设为true；
//         * maxFieldLength:这个是分词器拆分最大长度，因为各种不同类型的分词器拆分的字符颗粒细化程度不一样，所以需要设置一个最长的拆分长度。IndexWriter.MaxFieldLength.UNLIMITED表示无限制；
//         */
//        Analyzer analyzer = new StandardAnalyzer();
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        IndexWriter writer = new IndexWriter(directory, config);
//        //IndexWriter writer = new IndexWriter(directory, new SimpleAnalyzer(), true, IndexWriter.MaxFieldLength.UNLIMITED);
//        // 创建Document 文档对象，在lucene中创建的索引可以看成数据库中的一张表，表中也可以有字段,往里面添加内容之后可以根据字段去匹配查询
//        // 下面创建的doc对象中添加了三个字段，分别为name,sex,dosomething,
//        Document doc = new Document();
//        /*
//        * 参数说明 public Field(String name, String value, Store store, Index index)
//        * name : 字段名称
//        * value : 字段的值 store :
//        *  Field.Store.YES:存储字段值（未分词前的字段值） Field.Store.NO:不存储,存储与索引没有关系
//        *  Field.Store.COMPRESS:压缩存储,用于长文本或二进制，但性能受损
//        * index : 建立索引的方式，是否建立分词等等
//        *  Field.Index.ANALYZED:分词建索引
//        *  Field.Index.ANALYZED_NO_NORMS:分词建索引，但是Field的值不像通常那样被保存，而是只取一个byte，这样节约存储空间
//        *  Field.Index.NOT_ANALYZED:不分词且索引 ,一旦指定为这种类型后将会被lucenn录入索引中，但不会被作为关键搜索，除非输入所有的关键字
//        *  Field.Index.NOT_ANALYZED_NO_NORMS:不分词建索引，Field的值去一个byte保存
//        */
//        doc.add(new Field("name", "Chenghui", Field.Store.YES, Field.Index.ANALYZED));
//        doc.add(new Field("sex", "男的", Field.Store.YES, Field.Index.NOT_ANALYZED));
//        doc.add(new Field("dosometing", "I am learning lucene ", Field.Store.YES, Field.Index.ANALYZED));
//        writer.addDocument(doc);
//        writer.close(); // 这里可以提前关闭，因为dictory 写入内存之后 与IndexWriter 没有任何关系了
//
//    }
//}
//
