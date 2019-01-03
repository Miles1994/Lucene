package com.zyc.lucene.util;

import com.zyc.lucene.entity.Product;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Administrator
 * @Title: LuceneUtil
 * @ProjectName cmfz
 * @Date 2019-01-02--17:03
 */
public class LuceneUtil {
    private static final Version version = Version.LUCENE_44;
    private static Directory directory = null;
    private static IndexWriterConfig indexWriterConfig = null;
    private static Analyzer analyzer = null;

    static {
        try {
            directory = FSDirectory.open(new File("e:\\index"));
            analyzer = new IKAnalyzer(true);
            indexWriterConfig = new IndexWriterConfig(version, analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取indexWriter
     */
    public static IndexWriter getIndexWriter() {
        try {
            IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
            return indexWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取indexSearch对象
     */
    public static IndexSearcher getIndexSearcher() {
        try {
            DirectoryReader indexReader = DirectoryReader.open(FSDirectory.open(new File("e:\\index")));
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            return indexSearcher;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Version getVersion() {
        return version;
    }

    public static Analyzer getAnalyzer() {
        return analyzer;
    }

    /**
     * Doc转Product
     */
    public static Product getProFromDoc(Document document) {
        Product product = new Product();
        product.setId(document.get("id"));
        product.setName(document.get("name"));
        product.setDescription(document.get("description"));
        product.setUrl(document.get("url"));
        product.setStatus(document.get("status"));
        Date date = new Date(document.get("date"));
        product.setDate(date);
        product.setAddress(document.get("address"));
        return product;
    }
}
