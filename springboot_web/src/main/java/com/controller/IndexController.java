package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mapper.NodeMapper;
import com.mapper.type.TypeMetaMapper;
import com.model.Form;
import com.model.Node;
import com.model.type.Type;
import com.model.type.TypeMeta;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /************************************************************分割线************************************************************/

    @RequestMapping("/ajax")
    public ModelAndView ajax() {
        return new ModelAndView("/ajax");
    }

    @RequestMapping("/form")
    public Map<String, Object> form(Form form) {
        return new LinkedHashMap<>();
    }

    /* fixme 接收AJAX发送的List */
    @RequestMapping("/formList")
    public Map<String, Object> formList(String formListStr) {
        List<Form> formList = JSON.parseArray(formListStr, Form.class);
        return new LinkedHashMap<>();
    }

    @RequestMapping("/cache")
    public ModelAndView cache() {
        return new ModelAndView("/cache");
    }

    /************************************************************分割线************************************************************/

    @RequestMapping("/json")
    public ModelAndView json() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", 1000);
        map.put("list", Arrays.asList(1, 2, 3));

        ModelAndView view = new ModelAndView();
        view.addObject("jsonStr", JSON.toJSONString(map));
        view.setViewName("/json");
        return view;
    }

    /* 返回JSON */
    @RequestMapping("/responseJSON")
    public Map<String, Object> responseJSON() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", 1000);
        return map;
    }

    /* 接收JSON（客户端以application/json+payload方式发送） */
    @PostMapping(value = "/requestJSON")
    public Map<String, Object> requestJSON(@RequestBody JSONObject json) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", 1000);
        return map;
    }

    /************************************************************分割线************************************************************/

    @RequestMapping("/pdfjs")
    public ModelAndView pdfjs() {
        return new ModelAndView("/pdfjs");
    }

    /* 返回PDF文件字节流 */
    @RequestMapping("/displayPDF")
    public void displayPDF(HttpServletResponse response, Integer id) throws IOException {
//        /* 读取本地文件 */
//        System.out.println(id);
//        File file = new File("D:/新建文件夹（同步）/文档/Idea/idea使用教程2017-06-01.pdf");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        response.setContentType("multipart/form-data");
//        OutputStream outputStream = response.getOutputStream();
//        IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);

        /* 下载并读取文件字节流 */
        CloseableHttpClient client = HttpClients.createDefault();
        String uri = "http://127.0.0.1/download2";
        HttpGet get = new HttpGet(uri);
        HttpResponse httpResponse = client.execute(get);
        HttpEntity entity = httpResponse.getEntity();
        InputStream inputStream = entity.getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Integer length = 0;
        byte[] buffer = new byte[1024];
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        OutputStream outputStream = response.getOutputStream();
        IOUtils.write(byteArrayOutputStream.toByteArray(), outputStream);
    }

    /************************************************************分割线************************************************************/

    private static Integer count = 0;

    /* synchronized锁 */
    public synchronized Integer write() {
        count++;
        return count;
    }

    /* 测速 */
    @GetMapping("/speed")
    public Map<String, Object> speed() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String date = format.format(new Date());
        System.out.println(date + ":" + write());
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", 1000);
        return map;
    }

    /* 重置计数器 */
    @GetMapping("/reset")
    public ModelAndView reset() {
        count = 0;
        return new ModelAndView("redirect:/");
    }

    /************************************************************分割线************************************************************/

    @Autowired
    private TypeMetaMapper typeMetaMapper;

    /* 自定义资源类型 */
    @GetMapping("/type")
    public Map<String, Object> type() {
        Page page = new Page();
//        page.setCurrent(1);
//        page.setSize(1);
        TypeMeta typeMeta = TypeMeta.builder()
                .typeMetaId(1)
                .build();
        /* fixme 只能传实体类不能传Warpper */
        IPage<Type> typeIPage = typeMetaMapper.selectTypeList(page, typeMeta);
        List<Type> typeList = typeIPage.getRecords();
        Type type = Type.builder()
                .typeMetaId(typeList.get(0).getTypeMetaId())
                .typeMetaTitle(typeList.get(0).getTypeMetaTitle())
                .typeList(typeList)
                .build();

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("type", type);
        return map;
    }

    /* 自定义资源 */
    @GetMapping("/resource")
    public Map<String, Object> resource() {
        Map<String, Object> map = new LinkedHashMap<>();
        return map;
    }

    /************************************************************分割线************************************************************/

    List<Node> treeList = new ArrayList<>();

    @Autowired
    private NodeMapper nodeMapper;

    /* 树形结构 */
    @GetMapping("/tree")
    public Map<String, Object> tree() {
        /* fixme 查询单个节点 */
//        Node root = nodeMapper.selectOne(new QueryWrapper<Node>().lambda().eq(Node::getId, 1)); /* 根节点 */
        List<Node> flatList = nodeMapper.selectList(new QueryWrapper<Node>());
        recursive(flatList);
        treeList.removeIf(e -> e.getParentId() > 0);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("treeList", treeList);
        return map;
    }

    /* 递归扁平转树形结构 */
    private void recursive(List<Node> flatList) {
        for (Node node : flatList) {
            List<Node> childrenList = nodeMapper.selectList(new QueryWrapper<Node>().lambda().eq(Node::getParentId, node.getId()));
            if (childrenList.size() > 0) {
                recursive(childrenList);
            }
            node.setChildrenList(childrenList);
            treeList.add(node);
        }
    }

    /************************************************************分割线************************************************************/

    @GetMapping("/upload")
    public ModelAndView upload() {
        return new ModelAndView("/upload");
    }

    @PostMapping("/singleUpload")
    public Map<String, Object> singleUpload(
            Form form,
            @RequestParam("uploadFile") MultipartFile uploadFile) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (!uploadFile.isEmpty()) {
            try {
                String filename = uploadFile.getOriginalFilename();
//                uploadFile.transferTo(new File("C:/Users/Administrator/Desktop" + File.separator + filename));
                uploadFile.transferTo(new File("/home/fantasy/Desktop" + File.separator + filename));
                map.put("status", 1000);

                /* 在Spring中使用EasyExcel */
//                EasyExcel.read(uploadFile.getInputStream(), ExcelData.class, new ExcelListener(nodeMapper)).sheet().doRead();
            } catch (IOException e) {
                e.printStackTrace();
                map.put("status", 2000);
            }
        }
        return map;
    }

    @PostMapping("/batchUpload")
    public Map<String, Object> batchUpload(
            Form form,
            @RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (uploadFiles.length > 0) {
            try {
                String filename = "";
                for (MultipartFile uploadFile : uploadFiles) {
                    filename = uploadFile.getOriginalFilename();
                    uploadFile.transferTo(new File("C:/Users/Administrator/Desktop" + File.separator + filename));
                }
                map.put("status", 1000);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("status", 2000);
            }
        }
        return map;
    }

    @GetMapping("/download")
    public void download(
            HttpServletResponse response,
            @RequestParam(defaultValue = "表格.xlsx") String CN) {
        try {
            OutputStream outputStream = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(CN, "UTF-8"));
            XSSFWorkbook workbook = new XSSFWorkbook();
            workbook.createSheet("Sheet1");
            workbook.write(outputStream);

            /* 新建文本文件并下载 */
//            OutputStream outputStream = response.getOutputStream();
//            outputStream.write("".getBytes(StandardCharsets.UTF_8));
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test.txt", "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/download2")
    public ResponseEntity<byte[]> download2(@RequestParam(defaultValue = "idea使用教程2017-06-01.pdf") String CN) {
        try {
//            String filename = String.format("D:/新建文件夹（同步）/文档/Idea/%s", CN);
            String filename = String.format("/home/fantasy/Downloads/同步/文档/Idea/%s", CN);
            File file = new File(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(CN, "UTF-8"));
            Map map = new LinkedHashMap();
            map.put("name", "name123");
            headers.add("Content", JSONObject.toJSONString(map));
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* File/MultipartFile相互转换 */
    @GetMapping("/convert")
    public Map<String, Object> convert() throws IOException {
        File input = new File("C:/Users/Administrator/Desktop/test.jpg");
        MultipartFile multipartFile = new MockMultipartFile("test.jpg", new FileInputStream(input));

        File output = new File("C:/Users/Administrator/Desktop/test2.jpg");
        FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), output);

        Map<String, Object> map = new LinkedHashMap<>();
        return map;
    }

}
