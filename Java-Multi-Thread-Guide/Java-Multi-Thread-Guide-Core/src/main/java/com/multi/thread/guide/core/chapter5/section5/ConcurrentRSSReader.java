package com.multi.thread.guide.core.chapter5.section5;

import com.multi.thread.guide.core.Tools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @Description
 * @Author dongzonglei
 * @Date 2019/01/21 上午10:09
 */
public class ConcurrentRSSReader {

    public static void main(String[] args) throws Exception {
        final int argc = args.length;
        String url = argc > 0 ? args[0] : "http://lorem-rss.herokuapp.com/feed";
        InputStream in = loadRSS(url);
        Document document = parseXML(in);
        Element eleRSS = (Element) document.getFirstChild();
        Element eleChannel = (Element) eleRSS.getElementsByTagName("channel").item(0);
    }

    private static Document parseXML(InputStream in) throws Exception{
        return null;
    }

    private static InputStream loadRSS(String url) throws Exception {
        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream();
        Thread workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doDownload(url, pos);
                } catch (Exception e) {
                    Tools.silentClose(pos, in);
                    e.printStackTrace();
                }
            }
        }, "rss-loader");
        workThread.start();
        return in;
    }

    static void doDownload(String url, PipedOutputStream os) throws Exception {
        ReadableByteChannel readChannel = null;
        WritableByteChannel writeChannel = null;
        try {
            BufferedInputStream in = issueRequest(url);
            readChannel = Channels.newChannel(in);
            ByteBuffer buf = ByteBuffer.allocate(1024);
            writeChannel = Channels.newChannel(os);
            while (readChannel.read(buf) > 0) {
                buf.flip();
                writeChannel.write(buf);
                buf.clear();
            }
        } finally {
            Tools.silentClose(readChannel, writeChannel);
        }
    }

    static BufferedInputStream issueRequest(String url) throws Exception {
        return null;
    }
}
