package com;

import com.client.Client1;
import com.jdbc.JDBC1;
import com.test.Task1;
import com.test.Task2;
import javautil.test.ConsumeExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(ConsumeExtension.class)
public class ApplicationTest {

    @Autowired
    private Task1 task1;

    /*
     * todo 同步测试
     * ①task1()~task3()按顺序执行
     * */
//    @Test
//    @javautil.test.Consume(unit = "ms")
    public void test() throws InterruptedException {
        task1.task1();
        task1.task2();
        task1.task1();
    }

    /************************************************************半分割线******************************/

    @Autowired
    private Task2 task2;

    /*
     * todo 异步测试
     * ①直接用@Async标注task1()~task3()，主程序test2()会直接结束，不理会task1()~task3()是否执行完成
     * ②task1()~task3()需要返回值
     * */
//    @Test
//    @javautil.test.Consume(unit = "ms")
    public void test2() throws InterruptedException {
        Future<String> t1 = task2.task1();
        Future<String> t2 = task2.task2();
        Future<String> t3 = task2.task1();
        /* 没有返回值则无法使用isDone()函数 */
        while (true) { /* 轮询/事件循环 */
            if (t1.isDone() && t2.isDone() && t3.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private Client1 client1;

    /*
     * todo 异步请求
     * ①@Async依赖Spring，必须采用注入的方式才能异步
     * ②无论httpclient/asyncHttpClient均可异步（因为其所在方法为异步的）
     * */
//    @Test
//    @javautil.test.Consume(unit = "ms")
    public void test3() throws IOException, ExecutionException, InterruptedException {
        Future<String> t1 = client1.httpclient("http://bbs.wuyou.net");
        Future<String> t2 = client1.asyncHttpClient("http://bbs.wuyou.net");
        while (true) {
            if (t1.isDone() && t2.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private JDBC1 jdbc1;

    /* todo 异步数据库查询 */
    @Test
    @javautil.test.Consume(unit = "ms")
    public void test4() {
        long begin = System.currentTimeMillis();
        Future<String> t1 = jdbc1.queryForList("SELECT * FROM github");
        Future<String> t2 = jdbc1.queryForList("SELECT * FROM github");
        while (true) {
            if (t1.isDone() && t2.isDone()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
