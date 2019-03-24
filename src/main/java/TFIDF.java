import java.util.Arrays;
import java.util.List;

public class TFIDF {
    public double tf(List<String> doc, String term){
        double termFre = 0;
        for (String str : doc){
            if (str.equalsIgnoreCase(term)){
                termFre++;
            }
        }
        return termFre/doc.size();
    }

    public double df(List<List<String>> docs, String term){
        int n = 0;
        if (term != null && term != ""){
            for(List<String>doc : docs){
                for(String word : doc){
                    if(word.equalsIgnoreCase(term)){
                        n++;
                        break;
                    }
                }
            }
        }else{
            System.out.println("term 不能为空");
        }
        return n;
    }

    public double idf(List<List<String>> docs, String term){
        return Math.log(docs.size()/(double)df(docs,term)+1);
    }

    public double tfidf(List<String> doc, List<List<String>> docs, String term){
        return tf(doc, term) * idf(docs, term);
    }

    public static void main(String[] args) {
        List<String> doc1 = Arrays.asList("人工", "智能", "成为",
                "互联网", "大会", "焦点");
        List<String> doc2 = Arrays.asList("谷歌", "推出", "开源",
                "人工", "智能", "系统", "工具");
        List<String> doc3 = Arrays.asList("互联网", "的", "未来",
                "在", "人工", "智能");
        List<String> doc4 = Arrays.asList("谷歌", "开源", "机器",
                "学习", "工具");
        List<List<String>> documents = Arrays.asList(doc1, doc2,
                doc3,doc4);
        doc1.forEach(x-> System.out.println(x));

        TFIDF test = new TFIDF();
        System.out.println(test.tf(doc2,"谷歌"));
        System.out.println(test.idf(documents, "谷歌"));
        System.out.println(test.tfidf(doc2, documents, "谷歌"));
    }
}
