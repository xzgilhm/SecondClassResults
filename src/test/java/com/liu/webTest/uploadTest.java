package com.liu.webTest;

import com.liu.Tester;
import com.liu.core.Result;
import com.liu.model.TUser;
import com.liu.model.TUserSubmit;
import com.liu.service.CustomService;
import com.liu.service.TUserService;
import com.liu.service.TUserSubmitService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.*;


public class uploadTest extends Tester {
    @Autowired
    private TUserSubmitService tUserSubmitService;


    @Autowired
    private TUserService tUserService;

    @Autowired
    private CustomService customService;

    /**
     * 获得指定文件的byte数组
     */

    private  byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 根据byte数组，生成文件
     */
    public void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Test
    @Rollback(false)
    public void add(){
        TUserSubmit tUserSubmit = new TUserSubmit();
        uploadTest ut = new uploadTest();
        byte[] b = ut.getBytes("C:/Users/73559/Pictures/StudyPicture/jstraining-master/packagejson.jpg");
//        tUserSubmit.setFile(b);
        tUserSubmit.setUserid(2);
        tUserSubmit.setRoleid(2);
        tUserSubmit.setModuleid("01");
        tUserSubmit.setTypeid("0101");
        tUserSubmit.setStandardid(2);
        tUserSubmit.setCreditid(2);
        tUserSubmit.setEvidenceid(2);
        Result r = new Result();
        r.setData(tUserSubmit);
        System.out.println(r.toString());
        tUserSubmitService.save(tUserSubmit);
    }


    @Test
    public void findSubmit(){
        TUserSubmit tUserSubmit = new TUserSubmit();
        tUserSubmit = tUserSubmitService.findById(3);
//        getFile(tUserSubmit.getFile(),"D:/","1.jpg");

    }




    @Test
    public void directory(){

        System.out.println(System.getProperty("user.dir"));
    }


}
