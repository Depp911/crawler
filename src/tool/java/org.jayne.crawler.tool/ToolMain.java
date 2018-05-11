package org.jayne.crawler.tool;

import org.jayne.crawler.tool.common.AbstractLocalTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 基于Spring配置的本地工具启动器
 */
public class ToolMain {

    private static ApplicationContext ctx;

    private static final PrintWriter out = new PrintWriter(System.out, true);

    private static final PrintWriter err = new PrintWriter(System.err, true);

    public static void main(String[] args) throws Exception {
        args = init(args);
        if (0 == args.length) {
            usage();
            System.exit(0);
        }
        AbstractLocalTool tool = getTool(args[0]);
        if (null == tool) {
            out.println("指定的类不存在或者不是工具类");
            System.exit(1);
        }
        try {
            exec(tool, args);
        } catch (Exception e) {
            System.exit(1);
        }
        System.exit(0);
    }

    private static String[] init(String[] args) {
        String[] remainArgs = args;
        return remainArgs;
    }

    public static AbstractLocalTool getTool(String toolName) {
        Object object = getBean(toolName);
        if (!(object instanceof AbstractLocalTool)) {
            return null;
        }
        AbstractLocalTool tool = (AbstractLocalTool) object;
        tool.setPrintWriter(out, err);
        return tool;
    }

    public static Object getBean(String beanName) {
        if (null == ctx) {
            ctx = new ClassPathXmlApplicationContext("classpath:crawler.xml");
        }
        return ctx.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
    }

    public static <T> T getBean(Class<T> clazz) {
        if (null == ctx) {
            ctx = new ClassPathXmlApplicationContext("classpath:crawler.xml");
        }
        return ctx.getBean(clazz);
    }

    private static void exec(AbstractLocalTool tool, String[] args) {
        String[] remainArgs = new String[args.length - 1];
        System.arraycopy(args, 1, remainArgs, 0, remainArgs.length);
        out.println();
        try {
            tool.exec(remainArgs);
        } catch (Exception e) {
            out.println("There must be something wrong!");
            e.printStackTrace();
            tool.usage(out);
            throw new RuntimeException(e);
        }
    }

    private static void usage() throws IOException {
        Map<String, AbstractLocalTool> tools = ctx.getBeansOfType(AbstractLocalTool.class);
        out.println();
        out.println("欢迎使用平台脚本，当前共有脚本" + tools.size() + "个，用法如下：");
        out.println();
        int i = 0;
        for (Map.Entry<String, AbstractLocalTool> toolEntry : tools.entrySet()) {
            out.println(++i + ". " + toolEntry.getKey() + " " + toolEntry.getValue().comment());
            toolEntry.getValue().usage(out);
            out.println();
        }
        out.flush();
    }

}
