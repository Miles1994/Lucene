package com.zyc.lucene.dao;

import com.zyc.lucene.entity.Product;
import com.zyc.lucene.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
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
        //String类型不分词，Text类型分词
        doc.add(new StringField("id", product.getId(), Field.Store.YES));
        doc.add(new TextField("name", product.getName(), Field.Store.YES));
        doc.add(new TextField("description", product.getDescription(), Field.Store.YES));
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
        int pageSize = 3;
        int pageNum = 1;
        try {
            List<Product> list = new ArrayList<>();
            IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();

            //多字段查询
            String[] fields = {"name", "description"};
            MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, fields, LuceneUtil.getAnalyzer());
            Query parse = multiFieldQueryParser.parse(keyWord);
            //设置高亮
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Scorer scorer = new QueryTermScorer(parse);
            Highlighter highlighter = new Highlighter(formatter, scorer);

            TopDocs search = indexSearcher.search(parse, pageNum * pageSize);
            ScoreDoc[] scoreDocs = search.scoreDocs;
            Integer min = Math.min(scoreDocs.length,(pageNum-1)*pageSize+pageSize);
            for (int i = (pageNum - 1) * pageSize; i < min; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                Document doc = indexSearcher.doc(scoreDoc.doc);
                Product product = LuceneUtil.getProFromDoc(doc);
                String description = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "description", doc.get("description"));
                String name = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), "name", doc.get("name"));
                //判断高亮
                if (description == null) {
                    product.setDescription(doc.get(("description")));
                } else {
                    product.setDescription(description);
                }
                if (name == null) {
                    product.setName(doc.get("name"));
                } else {
                    product.setName(name);
                }
                list.add(product);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
