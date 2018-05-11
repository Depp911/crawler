package org.jayne.crawler.tool;

import org.jayne.crawler.service.SesameProxyService;
import org.jayne.crawler.tool.common.AbstractLocalTool;
import org.jayne.crawler.tool.common.Tool;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;

/**
 * Created by jayne on 2018/4/25.
 */
@Tool
public class GetProxyTestTool extends AbstractLocalTool {

    @Autowired
    SesameProxyService sesameProxyService;

    @Override
    public void usage(PrintWriter out) {
        out.println("run/tool.sh GetProxyTool");
    }

    @Override
    public String comment() {
        return "测试代理IP";
    }

    @Override
    public void exec(String[] args) throws Exception {
        sesameProxyService.getHttpProxy(1);
    }

    public static void main(String[] args){
        AbstractLocalTool tool = ToolMain.getTool("GetProxyTestTool");
        try {
            tool.exec(args);
        }catch (Exception e){}
        System.exit(0);
    }
}
