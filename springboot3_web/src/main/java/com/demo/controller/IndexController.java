package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.Form;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

    /************************************************************分割线************************************************************/

    @GetMapping("/ajax")
    public ModelAndView ajax() {
        return new ModelAndView("/ajax");
    }

    @PostMapping("/form")
    public Map<String, Object> form(Form form) {
        return new LinkedHashMap<>();
    }

    @GetMapping("/cache")
    public ModelAndView cache() {
        return new ModelAndView("/cache");
    }

    /************************************************************分割线************************************************************/

    @GetMapping("/json")
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
    @GetMapping("/responseJSON")
    public Map<String, Object> responseJSON() throws InterruptedException {
        Thread.sleep(30 * 1000);
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

    @GetMapping("/pdfjs")
    public ModelAndView pdfjs() {
        return new ModelAndView("/pdfjs");
    }

    /* 返回PDF文件字节流 */
    @GetMapping("/displayPDF")
    public void displayPDF(HttpServletResponse response, Integer id) throws IOException {
//        /* 读取本地文件 */
//        System.out.println(id);
//        File file = new File("C:/Users/Administrator/Desktop/test.pdf");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        response.setContentType("multipart/form-data");
//        OutputStream outputStream = response.getOutputStream();
//        IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);

        /* 下载并读取文件字节流 */
        InputStream inputStream = new URL("http://127.0.0.1/download2").openStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length;
        byte[] buffer = new byte[1024];
        while ((length = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }
        OutputStream outputStream = response.getOutputStream();
        IOUtils.write(byteArrayOutputStream.toByteArray(), outputStream);
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
                uploadFile.transferTo(new File("C:/Users/Administrator/Desktop" + File.separator + filename));
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
                String filename;
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
//            OutputStream outputStream = response.getOutputStream();
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(CN, "UTF-8"));
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            workbook.createSheet("Sheet1");
//            workbook.write(outputStream);

            /* 新建Zip文件并下载 */
            File file = new File("C:/Users/Administrator/Desktop/test.txt");
            InputStream inputStream = Files.newInputStream(file.toPath());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            int temp;
            while ((temp = inputStream.read()) != -1) {
                zipOutputStream.write(temp);
            }
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();
            response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode("test.zip", StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/download2")
    public ResponseEntity<byte[]> download2(@RequestParam(defaultValue = "test.pdf") String CN) {
        try {
            String filename = String.format("%s", CN);
            File file = new File(filename);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(CN, StandardCharsets.UTF_8));
            Map map = new LinkedHashMap();
            map.put("name", "name123");
            headers.add("Content", JSONObject.toJSONString(map));
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/download3")
    public ResponseEntity<byte[]> download3() {
        try {
            File file = new File("C:/Users/Administrator/Desktop/test.mp3");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode("test.mp3", StandardCharsets.UTF_8));
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
