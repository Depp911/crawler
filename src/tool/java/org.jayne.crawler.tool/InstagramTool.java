package org.jayne.crawler.tool;

import org.jayne.crawler.Crawler;
import org.jayne.crawler.tool.common.AbstractLocalTool;
import org.jayne.crawler.tool.common.Tool;
import org.jayne.crawler.utils.ExecInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jayne on 2018/5/10.
 */
@Tool
public class InstagramTool extends AbstractLocalTool {

    @Autowired
    private Crawler crawler;

    @Override
    public void usage(PrintWriter out) {
        out.println("run/tool.sh InstagramTool");
    }

    @Override
    public String comment() {
        return "抓取指定url";
    }

    @Override
    public void exec(String[] args) throws Exception {
        List<String> urls = new ArrayList<String>();
        urls.add("https://www.instagram.com/exempelthebunny/");
        for (String url : urls) {
            ExecInfo execInfo = crawler.syncContent(url);
        }
    }

    public static void main(String[] args) {
        AbstractLocalTool tool = ToolMain.getTool("InstagramTool");
        try {
            tool.exec(args);
        }catch (Exception e){}
        System.exit(0);
    }
}
