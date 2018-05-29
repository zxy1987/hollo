package controllers;

import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController2 extends Controller {

    public Result index() {
        //请求的webservice的url
        URL url = new URL("http://59.110.226.47/api/v1/apis");
        //创建http链接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求的方法类型
        httpURLConnection.setRequestMethod("GET");
        //设置请求的内容类型
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        //设置发送数据
        httpURLConnection.setDoOutput(true);
        //设置接受数据
        httpURLConnection.setDoInput(true);
        //发送数据,使用输出流
        OutputStream outputStream = httpURLConnection.getOutputStream();
        //发送的soap协议的数据
        String requestXmlString = requestXml("北京");
        String content = "user_id="+ URLEncoder.encode("13846", "gbk");
        //发送数据
        outputStream.write(content.getBytes());
        //接收数据
        InputStream inputStream = httpURLConnection.getInputStream();
        //定义字节数组
        byte[] b = new byte[1024];
        //定义一个输出流存储接收到的数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //开始接收数据
        int len = 0;
        while (true) {
            len = inputStream.read(b);
            if (len == -1) {
                //数据读完
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        //从输出流中获取读取到数据(服务端返回的)
        String response = byteArrayOutputStream.toString();
        System.out.println(response);
        return ok(index.render("Your new application is ready."));
    }

}
