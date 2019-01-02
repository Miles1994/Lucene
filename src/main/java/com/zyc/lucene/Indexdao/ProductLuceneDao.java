package com.zyc.lucene.Indexdao;

import com.zyc.lucene.entity.Product;
import com.zyc.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @Title: ProductLuceneDao
 * @ProjectName cmfz
 * @Date 2019-01-02--18:33
 */
public class ProductLuceneDao {
    /**
     * 添加
     */

    public void addIndex(Product product) throws IOException {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document doc = new Document();
        doc.add(new StringField("id", product.getId(), Field.Store.YES));
        doc.add(new TextField("name", product.getName(), Field.Store.YES));
        doc.add(new StringField("description", product.getDescription(), Field.Store.YES));
        doc.add(new StringField("url", product.getUrl(), Field.Store.YES));
        doc.add(new StringField("status", product.getStatus(), Field.Store.YES));
        doc.add(new StringField("date", product.getDate().toString(), Field.Store.YES));
        doc.add(new StringField("address", product.getAddress(), Field.Store.YES));
        indexWriter.addDocument(doc);
        indexWriter.close();
    }

    /**
     * 查询
     */
    public List<Product> searchIndex(String keyWord) {
        try {
            List<Product> list = new ArrayList<>();
            IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
            TermQuery termQuery = new TermQuery(new Term("description", keyWord));
            TopDocs search = indexSearcher.search(termQuery, 30);
            ScoreDoc[] scoreDocs = search.scoreDocs;
            for (int i = 0 ; i < scoreDocs.length ; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                Document doc = indexSearcher.doc(scoreDoc.doc);
                System.out.println("这是Dao");
                System.out.println(doc.get("id"));
                System.out.println(doc.get("name"));
                System.out.println(doc.get("description"));
                System.out.println(doc.get("date"));
                System.out.println(doc.get("url"));
                System.out.println(doc.get("address"));
                System.out.println(doc.get("status"));
                System.out.println("Doc"+doc);
                Product product = LuceneUtil.getProFromDoc(doc);

                System.out.println("转换之后的Product"+product);
                list.add(product);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
