package com;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.treeleafj.xdoc.XDoc;
import org.treeleafj.xdoc.spring.format.HtmlForamt;
import org.treeleafj.xdoc.spring.format.MarkdownFormat;
import org.treeleafj.xdoc.spring.framework.SpringWebFramework;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ApplicationTest {

    /* fixme 生成离线版xDoc HTML文档 */
//    @Test
    public void test() throws Exception {
        String userDir = System.getProperty("user.dir");
        FileOutputStream out = new FileOutputStream(new File(userDir, "api.html"));
        XDoc xDoc = new XDoc(userDir + "/src/main/java/org/treeleafj", new SpringWebFramework());
        xDoc.build(out, new HtmlForamt());
    }

    /* fixme 生成离线版xDoc Markdown文档 */
    @Test
    public void test2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String rootDir = System.getProperty("user.dir");
        XDoc xDoc = new XDoc(rootDir + "/src/main/java/org/treeleafj", new SpringWebFramework());
        xDoc.build(out, new MarkdownFormat());
        System.out.println(out.toString());
    }

}
