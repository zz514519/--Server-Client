package tcp2;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner stdinScanner = new Scanner(System.in);
        try (Socket socket = new Socket("127.0.0.1", 8191)) {
            OutputStream os = socket.getOutputStream();         // 返回此套接字的输出流
            OutputStreamWriter osWriter = new OutputStreamWriter(os,"UTF-8");//创建一个使用命名字符集的OutputStreamWriter
            PrintWriter printWriter = new PrintWriter(osWriter);        //将字符流转化为字节流

            InputStream is = socket.getInputStream();
            InputStreamReader isRead = new InputStreamReader(is,"UTF-8");
            Scanner scanner = new Scanner(isRead);

            //List<String> requestList = Arrays.asList("今天吃了么", "今天好冷啊", "今天好热啊", "回家吃去吧");
           // for (String request : requestList) {
            System.out.println("请输入请求->");
            while (stdinScanner.hasNextLine()){
                String request = stdinScanner.nextLine();

                printWriter.println(request);       //打印一个字符串，然后终止行。
                System.out.println("客户端请求   ->"+request);
                printWriter.flush();    //刷新流。

                String response = scanner.nextLine();
                System.out.println("<--服务器应答："+ response);

                System.out.println("请输入请求->");
            }
        }
    }
}