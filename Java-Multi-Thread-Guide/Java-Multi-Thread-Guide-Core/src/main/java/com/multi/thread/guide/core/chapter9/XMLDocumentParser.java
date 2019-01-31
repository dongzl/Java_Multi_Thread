package com.multi.thread.guide.core.chapter9;

import com.multi.thread.guide.core.Tools;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author dongzonglei
 * @description
 * @date 2019-01-31 10:57
 */
public class XMLDocumentParser {

    public static ParsingTask newTask(InputStream in) {
        return new ParsingTask(in);
    }

    public static ParsingTask newTask(URL url) throws Exception {
        //return newTask(url, 30000, 30000);
        return null;
    }

    public abstract static class ResultHandler {
        abstract void onSuccess(Document document);

        void onError(Throwable e) {
            e.printStackTrace();
        }
    }

    public static class ParsingTask {

        private final InputStream in;

        private volatile Executor executor;

        private volatile ResultHandler resultHandler;

        public ParsingTask(InputStream in, Executor executor, ResultHandler resultHandler) {
            this.in = in;
            this.executor = executor;
            this.resultHandler = resultHandler;
        }

        public ParsingTask(InputStream in) {
            this(in, null, null);
        }

        public Future<Document> execute() throws Exception {
            FutureTask<Document> ft;

            final Callable<Document> task = new Callable<Document>() {
                @Override
                public Document call() throws Exception {
                    return doParse(in);
                }
            };
            final Executor theExecutor = executor;
            final boolean isAsyncParsing = (null != theExecutor);
            final ResultHandler rh;
            if (isAsyncParsing && null != (rh = resultHandler)) {
                ft = new FutureTask<Document>(task) {
                    @Override
                    protected void done() {
                        callableResultHandler(this, rh);
                    }
                };
            } else {
                ft = new FutureTask<>(task);
            }
            if (isAsyncParsing) {
                theExecutor.execute(ft);
            } else {
                ft.run();
            }
            return ft;
        }

        private void callableResultHandler(FutureTask<Document> ft, ResultHandler rh) {
            if (ft.isCancelled()) {
                System.out.println("");
                return;
            }
            try {
                Document doc = ft.get();
                rh.onSuccess(doc);
            } catch (Exception e) {
                rh.onError(e);
            }
        }

        static Document doParse(InputStream in) throws Exception {
            Document document = null;
            try {
                DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                document = db.parse(in);
            } finally {
                Tools.silentClose(in);
            }
            return document;
        }

        public ParsingTask setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public ParsingTask setResultHandler(ResultHandler resultHandler) {
            this.resultHandler = resultHandler;
            return this;
        }
    }

    public static void main(String args[]) throws Exception {
        Executor executor = null;
        URL url = null;
        XMLDocumentParser.newTask(url).setExecutor(executor).setResultHandler(new ResultHandler() {
            @Override
            void onSuccess(Document document) {
                //process(document);
            }
        });
    }
}
