package com;

import com.async.client.Client;
import com.async.service.ProductService;
import com.async.test.Task1;
import com.async.test.Task2;
import com.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@ExtendWith(ConsumeExtension.class)
public class ApplicationTest {

    @Autowired
    private Task1 task1;

    /* todo 同步测试 */
//    @Test
    public void test() throws InterruptedException {
        task1.task1();
        task1.task2();
    }

    /************************************************************半分割线******************************/

    @Autowired
    private Task2 task2;

    /* todo 异步测试 */
//    @Test
    public void test2() throws InterruptedException {
        Future<String> future1 = task2.task1();
        Future<String> future2 = task2.task2();
        /* 没有返回值则无法使用isDone()函数 */
        while (true) { /* 轮询/事件循环 */
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private Client client;

    /*
     * todo 异步请求
     * ①@Async依赖Spring，必须采用注入的方式才能异步
     * ②无论httpclient/asyncHttpClient均可异步（因为其所在方法为异步的）
     * */
//    @Test
    public void test3() throws IOException, ExecutionException, InterruptedException {
        Future<String> future1 = client.httpclient("http://bbs.wuyou.net");
        Future<String> future2 = client.asyncHttpClient("http://bbs.wuyou.net");
        while (true) {
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private ProductService productService;

    @Test
    public void test4() {
        Future<List<Product>> future1 = productService.selectAll();
        Future<List<Product>> future2 = productService.selectAll();
        while (true) {
            if (future1.isDone() && future2.isDone()) {
                break;
            }
        }
    }

}
