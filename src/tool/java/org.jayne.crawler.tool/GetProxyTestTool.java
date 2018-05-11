package org.jayne.crawler.tool;

import org.jayne.crawler.data.proxy.ProxyContent;
import org.jayne.crawler.service.SesameProxyService;
import org.jayne.crawler.tool.common.AbstractLocalTool;
import org.jayne.crawler.tool.common.Tool;
import org.jayne.crawler.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.util.List;

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
        List<ProxyContent> proxyContentList = sesameProxyService.getHttpProxy(1);
        if(proxyContentList != null && proxyContentList.size() > 0){
            for(ProxyContent proxyContent : proxyContentList){
                LogUtils.info("IP: " + proxyContent.getIp());
            }
        }else {
            LogUtils.info("未获取到有效IP");
        }
    }

    public static void main(String[] args){
        AbstractLocalTool tool = ToolMain.getTool("GetProxyTestTool");
        try {
            tool.exec(args);
        }catch (Exception e){}
        System.exit(0);
    }
}
