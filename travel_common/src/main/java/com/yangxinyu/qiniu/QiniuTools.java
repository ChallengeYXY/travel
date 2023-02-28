package com.yangxinyu.qiniu;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiniuTools {
    //封装上传参数
    private String accessKey = "gTV5LdykLXyG6nEvXnpSpIZee7ZxCY5nhqqxBqgZ";
    private String secretKey = "R0qN2LiHsGRKbIA-wThJZhRZbkKw78WEsP-_5ZZR";
    private String bucket = "mytravelspace2";


    //文件上传
    public void upload(String localFilePath,String key){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
//        String accessKey = "gTV5LdykLXyG6nEvXnpSpIZee7ZxCY5nhqqxBqgZ";
//        String secretKey = "R0qN2LiHsGRKbIA-wThJZhRZbkKw78WEsP-_5ZZR";
//        String bucket = "mytravelspace";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "/Users/yxy/Desktop/3.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    //文件删除
    public void delete(String key){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
//        String accessKey = "your access key";
//        String secretKey = "your secret key";
//        String bucket = "your bucket name";
//        String key = "your file key";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}
